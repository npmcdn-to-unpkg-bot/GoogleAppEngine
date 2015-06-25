<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>  
<html>
	<head>  
  		<title>  
   			<decorator:title default="General" />
  		</title>
		<!-- http://www.smoothdivscroll.com/ -->  		
		<!-- CSS -->
		<link rel="stylesheet" type="text/css" href="/css/ypgstyle.css" />
		<!-- the CSS for Smooth Div Scroll -->
		<link rel="Stylesheet" type="text/css" href="/css/smoothDivScroll.css" />
	
		<!-- jQuery library - I get it from Google API's -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.5.2/jquery.min.js" type="text/javascript"></script>
		<!-- jQuery UI widget factory -->
		<!-- You can download it as a part of jQuery UI Core
		 http://jqueryui.com/download -->
		<script src="/js/jquery.ui.widget.js" type="text/javascript"></script>
		
		<!-- Smooth Div Scroll 1.1 - minified for faster loading-->
		<script src="/js/jquery.smoothDivScroll-1.1-min.js" type="text/javascript"></script>

<!-- jQuery accordian http://www.marghoobsuleman.com/jQuery-common-accordion -->
<link rel="stylesheet" type="text/css" href="/css/accordion.css" />
<script language="javascript" type="text/javascript" src="/js/jquery.msAccordion.js"></script>
  		
	
		<!-- Styles for my specific scrolling content -->
		<style type="text/css">
			#makeMeScrollable1, #makeMeScrollable2
			{
				width:100%;
				height: 65px;
				position: relative;
			}
			
			#makeMeScrollable1 div.scrollableArea img, #makeMeScrollable2 div.scrollableArea img
			{
				width: 100px;
				position: relative;
				float: left;
				margin: 0;
				padding: 0;
			}
		</style>
	
  		<decorator:head />
	  		
		<script type="text/javascript">
			//*** smoothdivscroll ***
			// Initialize the plugin with no custom options
			$(window).load(function() {
				$("div#makeMeScrollable1").smoothDivScroll({ 
					autoScroll: "onstart",
					autoScrollDirection: "endlessloopright",
					autoScrollStep: 1,
					autoScrollInterval: 15,
					startAtElementId: "startAtMe",
					visibleHotSpots: "always"
				});
				$("div#makeMeScrollable2").smoothDivScroll({ 
					autoScroll: "onstart",
					autoScrollDirection: "endlessloopleft",
					autoScrollStep: 1,
					autoScrollInterval: 15,
					startAtElementId: "startAtMe",
					visibleHotSpots: "always"
				});
			});
			//*** accordian ***
			$(document).ready(function() {
					$("#accordionGiftLelo").msAccordion();
//					$("#accordionGiftLelo").msAccordion({defaultid:3, autodelay:4});
				}
			)
		</script>
		<script type="text/javascript">
			function resizeImg1(img, scroll, banner) {
			    img.height = img.naturalHeight*3;
			    img.width = img.naturalWidth*3;
			    if(scroll) {
			        img.height = img.naturalHeight;
			        img.width = img.naturalWidth;
					$("#imagepopup").attr("src","");
					$("#imagepopup").attr("style", "display:none;position:absolute;top:200;left:300;z-index:30");
					$('#'+banner).smoothDivScroll("startAutoScroll");
				} else {
					$("#imagepopup").attr("src",img.src);
			    	$('#'+banner).smoothDivScroll("stopAutoScroll");
					$("#imagepopup").attr("style", "display:inline;position:absolute;top:200;left:300;z-index:30");
				}
			}
		</script>
 	</head>
<body id="ypgbody" style="overflow-x: hidden;">

<div id="example">
	<jsp:include page="/gu/header.jsp"></jsp:include>

  <div id="ex_navbar">
	<div id="makeMeScrollable1">
		<div class="scrollWrapper">
			<div class="scrollableArea">
				<img onmouseover="resizeImg1(this, false, 'makeMeScrollable1');" onmouseout="resizeImg1(this, true, 'makeMeScrollable1');" src="http://www.smoothdivscroll.com/images/demo/golf.jpg" alt="Demo image" id="startAtMe" />
				<img onmouseover="resizeImg1(this, false, 'makeMeScrollable1');" onmouseout="resizeImg1(this, true, 'makeMeScrollable1');" src="http://www.smoothdivscroll.com/images/demo/field.jpg" alt="Demo image" />
				<img onmouseover="resizeImg1(this, false, 'makeMeScrollable1');" onmouseout="resizeImg1(this, true, 'makeMeScrollable1');" src="http://www.smoothdivscroll.com/images/demo/gnome.jpg" alt="Demo image" />
				<img onmouseover="resizeImg1(this, false, 'makeMeScrollable1');" onmouseout="resizeImg1(this, true, 'makeMeScrollable1');" src="http://www.smoothdivscroll.com/images/demo/pencils.jpg" alt="Demo image" />
				<img onmouseover="resizeImg1(this, false, 'makeMeScrollable1');" onmouseout="resizeImg1(this, true, 'makeMeScrollable1');" src="http://www.smoothdivscroll.com/images/demo/river.jpg" alt="Demo image" />
				<img onmouseover="resizeImg1(this, false, 'makeMeScrollable1');" onmouseout="resizeImg1(this, true, 'makeMeScrollable1');" src="http://www.smoothdivscroll.com/images/demo/train.jpg" alt="Demo image" />
				<img onmouseover="resizeImg1(this, false, 'makeMeScrollable1');" onmouseout="resizeImg1(this, true, 'makeMeScrollable1');" src="http://www.smoothdivscroll.com/images/demo/leaf.jpg" alt="Demo image" />
				<img onmouseover="resizeImg1(this, false, 'makeMeScrollable1');" onmouseout="resizeImg1(this, true, 'makeMeScrollable1');" src="http://www.smoothdivscroll.com/images/demo/dog.jpg" alt="Demo image" />
				<img onmouseover="resizeImg1(this, false, 'makeMeScrollable1');" onmouseout="resizeImg1(this, true, 'makeMeScrollable1');" src="http://www.smoothdivscroll.com/images/demo/field.jpg" alt="Demo image" />
				<img onmouseover="resizeImg1(this, false, 'makeMeScrollable1');" onmouseout="resizeImg1(this, true, 'makeMeScrollable1');" src="http://www.smoothdivscroll.com/images/demo/gnome.jpg" alt="Demo image" />
				<img onmouseover="resizeImg1(this, false, 'makeMeScrollable1');" onmouseout="resizeImg1(this, true, 'makeMeScrollable1');" src="http://www.smoothdivscroll.com/images/demo/pencils.jpg" alt="Demo image" />
				<img onmouseover="resizeImg1(this, false, 'makeMeScrollable1');" onmouseout="resizeImg1(this, true, 'makeMeScrollable1');" src="http://www.smoothdivscroll.com/images/demo/river.jpg" alt="Demo image" />
				<img onmouseover="resizeImg1(this, false, 'makeMeScrollable1');" onmouseout="resizeImg1(this, true, 'makeMeScrollable1');" src="http://www.smoothdivscroll.com/images/demo/train.jpg" alt="Demo image" />
				<img onmouseover="resizeImg1(this, false, 'makeMeScrollable1');" onmouseout="resizeImg1(this, true, 'makeMeScrollable1');" src="http://www.smoothdivscroll.com/images/demo/leaf.jpg" alt="Demo image" />
				<img onmouseover="resizeImg1(this, false, 'makeMeScrollable1');" onmouseout="resizeImg1(this, true, 'makeMeScrollable1');" src="http://www.smoothdivscroll.com/images/demo/dog.jpg" alt="Demo image" />
				<img onmouseover="resizeImg1(this, false, 'makeMeScrollable1');" onmouseout="resizeImg1(this, true, 'makeMeScrollable1');" src="http://www.smoothdivscroll.com/images/demo/field.jpg" alt="Demo image" />
				<img onmouseover="resizeImg1(this, false, 'makeMeScrollable1');" onmouseout="resizeImg1(this, true, 'makeMeScrollable1');" src="http://www.smoothdivscroll.com/images/demo/gnome.jpg" alt="Demo image" />
				<img onmouseover="resizeImg1(this, false, 'makeMeScrollable1');" onmouseout="resizeImg1(this, true, 'makeMeScrollable1');" src="http://www.smoothdivscroll.com/images/demo/pencils.jpg" alt="Demo image" />
				<img onmouseover="resizeImg1(this, false, 'makeMeScrollable1');" onmouseout="resizeImg1(this, true, 'makeMeScrollable1');" src="http://www.smoothdivscroll.com/images/demo/river.jpg" alt="Demo image" />
				<img onmouseover="resizeImg1(this, false, 'makeMeScrollable1');" onmouseout="resizeImg1(this, true, 'makeMeScrollable1');" src="http://www.smoothdivscroll.com/images/demo/train.jpg" alt="Demo image" />
				<img onmouseover="resizeImg1(this, false, 'makeMeScrollable1');" onmouseout="resizeImg1(this, true, 'makeMeScrollable1');" src="http://www.smoothdivscroll.com/images/demo/leaf.jpg" alt="Demo image" />
				<img onmouseover="resizeImg1(this, false, 'makeMeScrollable1');" onmouseout="resizeImg1(this, true, 'makeMeScrollable1');" src="http://www.smoothdivscroll.com/images/demo/dog.jpg" alt="Demo image" />

			</div>
		</div>
	</div>
  </div>
  <div id="ex_navbar">
	<div id="makeMeScrollable2">
		<div class="scrollWrapper">
			<div class="scrollableArea">
				<img onmouseover="resizeImg1(this, false, 'makeMeScrollable2');" onmouseout="resizeImg1(this, true, 'makeMeScrollable2');" src="http://www.smoothdivscroll.com/images/demo/golf.jpg" alt="Demo image" id="startAtMe" />
				<img onmouseover="resizeImg1(this, false, 'makeMeScrollable2');" onmouseout="resizeImg1(this, true, 'makeMeScrollable2');" src="http://www.smoothdivscroll.com/images/demo/field.jpg" alt="Demo image" />
				<img onmouseover="resizeImg1(this, false, 'makeMeScrollable2');" onmouseout="resizeImg1(this, true, 'makeMeScrollable2');" src="http://www.smoothdivscroll.com/images/demo/gnome.jpg" alt="Demo image" />
				<img onmouseover="resizeImg1(this, false, 'makeMeScrollable2');" onmouseout="resizeImg1(this, true, 'makeMeScrollable2');" src="http://www.smoothdivscroll.com/images/demo/pencils.jpg" alt="Demo image" />
				<img onmouseover="resizeImg1(this, false, 'makeMeScrollable2');" onmouseout="resizeImg1(this, true, 'makeMeScrollable2');" src="http://www.smoothdivscroll.com/images/demo/river.jpg" alt="Demo image" />
				<img onmouseover="resizeImg1(this, false, 'makeMeScrollable2');" onmouseout="resizeImg1(this, true, 'makeMeScrollable2');" src="http://www.smoothdivscroll.com/images/demo/train.jpg" alt="Demo image" />
				<img onmouseover="resizeImg1(this, false, 'makeMeScrollable2');" onmouseout="resizeImg1(this, true, 'makeMeScrollable2');" src="http://www.smoothdivscroll.com/images/demo/leaf.jpg" alt="Demo image" />
				<img onmouseover="resizeImg1(this, false, 'makeMeScrollable2');" onmouseout="resizeImg1(this, true, 'makeMeScrollable2');" src="http://www.smoothdivscroll.com/images/demo/dog.jpg" alt="Demo image" />
				<img onmouseover="resizeImg1(this, false, 'makeMeScrollable2');" onmouseout="resizeImg1(this, true, 'makeMeScrollable2');" src="http://www.smoothdivscroll.com/images/demo/field.jpg" alt="Demo image" />
				<img onmouseover="resizeImg1(this, false, 'makeMeScrollable2');" onmouseout="resizeImg1(this, true, 'makeMeScrollable2');" src="http://www.smoothdivscroll.com/images/demo/gnome.jpg" alt="Demo image" />
				<img onmouseover="resizeImg1(this, false, 'makeMeScrollable2');" onmouseout="resizeImg1(this, true, 'makeMeScrollable2');" src="http://www.smoothdivscroll.com/images/demo/pencils.jpg" alt="Demo image" />
				<img onmouseover="resizeImg1(this, false, 'makeMeScrollable2');" onmouseout="resizeImg1(this, true, 'makeMeScrollable2');" src="http://www.smoothdivscroll.com/images/demo/river.jpg" alt="Demo image" />
				<img onmouseover="resizeImg1(this, false, 'makeMeScrollable2');" onmouseout="resizeImg1(this, true, 'makeMeScrollable2');" src="http://www.smoothdivscroll.com/images/demo/train.jpg" alt="Demo image" />
				<img onmouseover="resizeImg1(this, false, 'makeMeScrollable2');" onmouseout="resizeImg1(this, true, 'makeMeScrollable2');" src="http://www.smoothdivscroll.com/images/demo/leaf.jpg" alt="Demo image" />
				<img onmouseover="resizeImg1(this, false, 'makeMeScrollable2');" onmouseout="resizeImg1(this, true, 'makeMeScrollable2');" src="http://www.smoothdivscroll.com/images/demo/dog.jpg" alt="Demo image" />
				<img onmouseover="resizeImg1(this, false, 'makeMeScrollable2');" onmouseout="resizeImg1(this, true, 'makeMeScrollable2');" src="http://www.smoothdivscroll.com/images/demo/field.jpg" alt="Demo image" />
				<img onmouseover="resizeImg1(this, false, 'makeMeScrollable2');" onmouseout="resizeImg1(this, true, 'makeMeScrollable2');" src="http://www.smoothdivscroll.com/images/demo/gnome.jpg" alt="Demo image" />
				<img onmouseover="resizeImg1(this, false, 'makeMeScrollable2');" onmouseout="resizeImg1(this, true, 'makeMeScrollable2');" src="http://www.smoothdivscroll.com/images/demo/pencils.jpg" alt="Demo image" />
				<img onmouseover="resizeImg1(this, false, 'makeMeScrollable2');" onmouseout="resizeImg1(this, true, 'makeMeScrollable2');" src="http://www.smoothdivscroll.com/images/demo/river.jpg" alt="Demo image" />
				<img onmouseover="resizeImg1(this, false, 'makeMeScrollable2');" onmouseout="resizeImg1(this, true, 'makeMeScrollable2');" src="http://www.smoothdivscroll.com/images/demo/train.jpg" alt="Demo image" />
				<img onmouseover="resizeImg1(this, false, 'makeMeScrollable2');" onmouseout="resizeImg1(this, true, 'makeMeScrollable2');" src="http://www.smoothdivscroll.com/images/demo/leaf.jpg" alt="Demo image" />
				<img onmouseover="resizeImg1(this, false, 'makeMeScrollable2');" onmouseout="resizeImg1(this, true, 'makeMeScrollable2');" src="http://www.smoothdivscroll.com/images/demo/dog.jpg" alt="Demo image" />
			</div>
		</div>
	</div>
  </div>

  <div id="ex_middle">

<div id="accordionGiftLelo" style="width:98%">
  <div class="set">
    <div class="title"><img src="/images/ELEPHANT_LUCK_1.jpg" width="29" height="100%" /></div>
    <div class="content">
		<jsp:include page="/gu/left.jsp"></jsp:include>
	    <div id="ex_main_smaller">
			<decorator:body/>
	    </div>
    </div>
  </div>
  <div class="set">
    <div class="title"><img src="/images/SPRING_EMBROIDERY_1.jpg" width="29" height="100%" /></div>
    <div class="content">
	    <div id="ex_main">
			<decorator:body/>
	    </div>
    </div>
  </div>
</div>

	<div>
		<img id="imagepopup" src="" style="display:none"/>
	</div>

  </div>

    <!-- make the middle region's background color expand -->
    <div class="clear">
		<jsp:include page="/gu/footer.jsp"></jsp:include>
    </div>
</div>
</body>
</html>