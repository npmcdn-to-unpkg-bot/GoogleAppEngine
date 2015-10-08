<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>  
<%@ taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page" %>  
<!DOCTYPE html>
<html>
 	<head>  
		<title>  
			Mobile: <decorator:title />
		</title>
		<decorator:head />
		<!-- jquerymobile stuff (http://jquerymobile.com/demos/1.0a2/#docs/pages/docs-pages.html) -->
		<!-- Android friendly page scaling http://ofps.oreilly.com/titles/9781449383268/ch02_id35816688.html#ch02_id35932627 -->
<!--
		<meta name="viewport" content="user-scalable=no, width=device-width" />
-->
		<!-- jquerymobile stuff (http://jquerymobile.com/demos/1.0a2/#docs/pages/docs-pages.html) -->
<link rel="stylesheet"  href="http://code.jquery.com/mobile/1.0.1/jquery.mobile-1.0.1.min.css" />
<script src="http://code.jquery.com/jquery-1.6.1.min.js"></script>
<script src="http://code.jquery.com/mobile/1.0.1/jquery.mobile-1.0.1.min.js"></script>
<script>
//http://forum.jquery.com/topic/alpha-4-orientation-change-css-don-t-work-in-android-fixed
$(function() {
    onChangeOrientationResize = function() {
        $('body').removeClass('portrait landscape').addClass(window.orientation ? 'landscape' : 'portrait');
    };            
    if ("onorientationchange" in window === false) { 
          window.addEventListener("resize", onChangeOrientationResize, false);
          onChangeOrientationResize();
    }
});
//http://forum.jquery.com/topic/horizontal-and-vertical-orientation-event
$(function() {
    var setOrientation = function() {
        if (window.orientation === undefined) { // desktop
            return;
        }
        
        if (Math.abs(window.orientation) == 90) { // horizontal
            $(document).triggerHandler('onHorizontalOrientation'); 
        }
        else { // vertical
            $(document).triggerHandler('onVerticalOrientation'); // meu evento
        }
    };
    var orientationEvent = "onorientationchange" in window ? "orientationchange" : "resize";
    window.addEventListener(orientationEvent, setOrientation, false);
    setOrientation();
});
$(document).bind('onHorizontalOrientation', function(){ $("title").html("Vertical View"); });
$(document).bind('onVerticalOrientation', function(){ $("title").html("Horizontal View"); });

<!-- http://api.jquery.com/ready/ -->
$(document).ready(function () {
	//$("title").text("The DOM is now loaded and can be manipulated.");
	 var deviceAgent = navigator.userAgent.toLowerCase();
	 var agentID = deviceAgent.match(/(iphone|ipod|ipad|android)/);
	 if(agentID != null) {
		 if(agentID.indexOf("iphone")>=0){
			$("title").text("iPhone");
		 }
		 if(agentID.indexOf("ipod")>=0){
			$("title").text("iPod");
		 }
		 if(agentID.indexOf("ipad")>=0){
			$("title").text("iPad");
		 }
		 if(agentID.indexOf("android")>=0){
			$("title").text("Android");
		 }
	 } else {
			$("title").text("PC/Unsupported Device");
	 }
});
</script>
	<!--
	Remember to add data-roles header, content and footer in each page that uses this template !!!
	Otherwise you will get "Uncaught TypeError: Cannot call method '_trigger' of undefined" error.
	Source: http://forum.jquery.com/topic/at-startup-uncaught-typeerror-cannot-call-method-trigger-of-undefined
	-->
	</head>
  	<body>
    	<decorator:body />
  	</body>  
</html>