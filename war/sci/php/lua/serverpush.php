<?php

/***************************************************
    Library to provide server push functionality
Version 1.0 - Written by Mark Wilton-Jones 16/7/2008
****************************************************

Please see http://www.howtocreate.co.uk/php/ for details
Please see http://www.howtocreate.co.uk/jslibs/termsOfUse.html for terms and conditions of use

PHP/server requirements:
  * Requires PHP 4.2.0+
  * Cannot be used on servers where PHP flush is disabled, such as those running mod_security, mod_gzip, suPHP, some win32 installs
  * PHP needs to be allowed to set max_execution_time (PHP must not be running in safe mode)
  * PHP needs to be allowed to disable compression, or compression must be disabled by default
  * Server push typically uses a child thread per connection - ensure your server allows enough to cope with all your visitors

PHP API:
void doServerPush( string: file_path, string: MIME_type, int: poll_time_in_ms )
  Serves the specified file, along with any updates, using server push.
  Does not return at all unless ignore_user_abort is forced.
void enableFakeServerPush()
  For use on the page where the pushed content is used
  Creates a JavaScript that can simulate server push in browsers that do not support it (specifically Internet Explorer).
  Reloads the file even if it has not changed, so uses more bandwidth than true server push.

JavaScript API:
void fakeServerPush( object: element, string: property, string: newsrc, int: poll_time_in_ms )
  Every poll, the specified property on the element is set to the newsrc, along with a random query string to prevent caching.
  Waits for the next file to load before waiting for the next poll interval.

Using server push is as simple as this:
  require('serverpush.php');
  doServerPush('some_image.jpg','image/jpeg',1000);
Every time the some_image.jpg file is changed on the filesystem, the update will be sent to the browser.
Recommended poll is 50+ with about 1000 (1 second) or more being normal - poll needs to be long enough to send the file to the
user, and will be affected by the user's network speed.
doServerPush does not attempt to sanitise file paths - the script that calls the doServerPush function must ensure that the file
path it is serving is safe for the user to see.

Using the script to simulate server push in browsers that do not support it, relies on the user having JavaScript enabled:
Somewhere in the document head, include this file then call the enableFakeServerPush function to create the JavaScript.
Then add an onerror event handler to the image tag being used to display the pushed content, which then calls fakeServerPush.
Poll time is not affected by network speed.

Example 1:
  <img src="push_image.php" onerror="fakeServerPush(this,'src','some_image.jpg',1000);" alt="foo">

Example 2 (better fallback - static image - if IE has JS disabled):
  <object src="push_image.php" onerror="fakeServerPush(document.getElementById('ani'),'src','some_image.jpg',1000);">
  <img src="some_image.jpg" id="ani" alt="foo">
  </object>
___________________________________________________________________________________________________________*/

function doServerPush($file,$type,$poll) {

	if( !file_exists($file) ) {
		//on first load, the file must exist, or it ends up sending a broken file
		header('Content-type: text/html',true,404);
	}

	@set_time_limit(0); //PHP must not terminate this script
	@ignore_user_abort(false); //do not ignore user aborts (may not be allowed, so status is checked in the loop)
	@ini_set( 'zlib.output_compression', 'off' );
	$poll *= 1000;

	//need a unique boundary
	//while it is possible to get a unique boundary for the current image data, it is possible that it will conflict
	//with future image data - can't help this, just have to live with it - might as well use a static string
	$separator = 'MTWJ_serverpush_boundary_';
	for( $i = 0, $randChars = Array('A','B'); $i < 20; $i++ ) {
		$separator .= $randChars[rand(0,1)];
	}

	header('Cache-Control: no-cache');
	header('Pragma: no-cache');
	//the header that makes server push work
	header("Content-Type: multipart/x-mixed-replace;boundary=$separator",true);
	$extrabreaks = '';

	do {
		//send one file starting with a multipart boundary
		$filelen = filesize($file);
		print "$extrabreaks--$separator\n";
		if( !$extrabreaks ) { $extrabreaks = "\n\n"; } //Safari needs exactly one blank line, no extra linebreaks on the first one
		print "Content-Type: $type\n";
		print "Content-Length: $filelen\n\n";
		readfile($file);
		//Safari needs 200+ bytes between headers - IE needs 256+ per flush, and this will also take care of that, when IE gets server push
		print str_pad('',200-$filelen); //whitespace here is ignored because of content-length
		$lastupdsate = filemtime($file);
		ob_flush();
		flush();
		$looptime = time();
		do {
			clearstatcache(); //need updated file info
			usleep($poll);
			if( time() - $looptime >= 10 ) {
				//every 10 seconds, force it to re-evaluate if the user has disconnected (connection_aborted does not know until this happens)
				//most use-cases will not need this protection because they will keep trying to send updates, but it is here just in case
				$looptime = time();
				print ' '; //whitespace is ignored at this specific point in the stream
				ob_flush();
				flush();
			}
			//poll the filesystem until the file changes, then send the update
			//the file may not always exist for the instant it is being replaced on the filesystem
			//nested loop and filemtime check inspired by http://web.they.org/software/php-push.php
		} while( !connection_aborted() && ( !file_exists($file) || ( $lastupdsate == filemtime($file) ) ) );
	} while( !connection_aborted() ); //if aborts are ignored, exit anyway to avoid endless threads
}

function enableFakeServerPush() {
	//in theory, it would be possible to reduce bandwidth by making this script use XMLHttpRequest HEAD requests to check if-modified-since
	//(fetching the last update date from JavaScript), but that has numerous flaws:
	// * it only works within the same domain, but most server push is done with images from other domains
	// * not all servers use if-modified-since correctly for dynamic content
	// * not all servers respond correctly to head requests containing if-modified-since
	// * it would rely on the user's computer clock being correct, and a disturbingly large number are not
	// * it over-complicates matters and adds more points of failure, when all that is needed is something that just works
	?>
<script type="text/javascript">
function fakeServerPush(anielem,property,newsrc,poll) {
	if( anielem.alreadyFakePushing ) { return; }
	anielem.alreadyFakePushing = true;
	anielem.onload = function () {
		setTimeout(function () {
			anielem[property] = newsrc + ( ( newsrc.indexOf('?') + 1 ) ? '&' : '?' ) + 'ignorerandom=' + (new Date()).getTime() + '' + Math.random();
		},poll);
	};
	anielem[property] = newsrc + ( ( newsrc.indexOf('?') + 1 ) ? '&' : '?' ) + 'ignorerandom=' + (new Date()).getTime() + '' + Math.random();
}
</script>
	<?php
}

?>