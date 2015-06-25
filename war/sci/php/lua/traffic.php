<?php
$zip = ($_REQUEST['zip']);

$xml = simplexml_load_file('http://local.yahooapis.com/MapsService/V1/trafficData?appid=KA27.oTV34EWikLcN2UvFhjoMRzAnvAMqOv4omWkUXZCANI3XK66lXSiB862kbdZzOc-&zip='.$zip);

$myFile = "trafficresults.txt";
$fh = fopen($myFile, 'w') or die("can't open file");


$i=1;

foreach($xml->Result as $row){
	if($i<=2){
	$title  = wordwrap($row->Title."", 21, "\n")."\n";
	fwrite($fh, $title);
	}
	$i++;
}
fclose($fh);
?>
