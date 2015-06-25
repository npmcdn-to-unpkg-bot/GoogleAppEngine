<!--
1. framesets changed to preview
https://bugs.eclipse.org/bugs/show_bug.cgi?id=329182
2. custom icons
http://www.interactivebynature.net/blog/wordpress/2011.04.04.jquery-mobile-custom-icons
http://glyphish.com/
-->
<html class="ui-mobile no-js">
 	<head>  
		<title>VZ iOS Demo</title>
		<!-- jquerymobile stuff (http://jquerymobile.com/demos/1.0a2/#docs/pages/docs-pages.html) -->
		<link rel="stylesheet" href="http://code.jquery.com/mobile/1.0a4/jquery.mobile-1.0a4.min.css" />
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.5.2/jquery.min.js"></script>
		<script src="http://code.jquery.com/mobile/1.0a4/jquery.mobile-1.0a4.min.js"></script>
		<script type="text/javascript" src="easyXDM/easyXDM.debug.js"></script>
		<style>
//source: http://forum.jquery.com/topic/grid-system-by-using-display-table-display-table-cell
/* display : table management grid */ 
.table, .table-2cols, .table-3cols, .table-4cols, .table-5cols{display:table; width: 100%; vertical-align: middle;}
.table-cell{display:table-cell; vertical-align: middle; padding: 3px;}
.table-2cols .table-cell, .table-2cols .table-cell1, .table-2cols .table-cell2{width: 50%;}
.table-3cols .table-cell, .table-3cols .table-cell1, .table-3cols .table-cell2, .table-3cols .table-cell3{width: 33.333%;}
.table-4cols .table-cell, .table-4cols .table-cell1, .table-4cols .table-cell2, .table-4cols .table-cell3, .table-4cols .table-cell4{width: 25%;}
.table-5cols .table-cell, .table-5cols .table-cell1, .table-5cols .table-cell2, .table-5cols .table-cell3, .table-5cols .table-cell1, .table-5cols .table-cell5{width: 20%;}

/* removing the extra padding */
.ui-content {
padding-top: 0px;  
padding-right: 0px;  
padding-bottom: 0px;  
padding-left: 0px; 
}		
			/* the screen lock toggle button */
			.ui-field-contain, .ui-body ui-br {
				height:10px;
			}
			.quadrant, .chart {
				height:50%;
			}
			.iconcon .ui-bar .ui-bar-a .ui-footer {
				position:absolute;bottom:10px;height:100%;		
			}
			.iconcon {
left:-15px;
width:105%;
margin:auto;
text-align:center;/* centers inline-blocks*/
border:1px solid #000; 
padding:10px;
position:absolute;bottom:10px;
			}
			.icon {
padding-left: 20px;
padding-right: 20px;
			}
			.list {
			opacity:0.3;
			}			
			.listitem {
padding-left: 20px;
padding-right: 20px;
			}
		</style>
		<script>
			function rptCallback1() {
			}
			
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
			$(document).bind('onHorizontalOrientation', function(){ $("title").html("Horizontal View");$(".chart").show();$(".list").hide(); });
			$(document).bind('onVerticalOrientation', function(){ $("title").html("Vertical View");$(".chart").hide();$(".list").show(); });
		</script>
	</head>
  	<body onload="$('ul').listview('refresh');">
		<!-- begin of main page 1 -->
		<div data-role="page" data-theme="a" data-dividertheme="d" data-counttheme="e" id="main1">
			<div data-role="header" data-nobackbtn="true" data-position="inline">
				<a href="" data-icon="gear" class="ui-btn-left">Options</a>
				<h1>Verizon Enterprise Dashboard</h1>
				<a href="#main2" data-icon="arrow-r" class="ui-btn-right">Next</a>
			</div>			

			<div data-role="content" data-theme="c">

		        <div class="ui-grid-a">
			        <div class="ui-block-a">
		    	        <a class="quadrant" data-theme="d" data-dividertheme="c" href="#report-main1" data-theme="e" data-role="button" data-transition="pop">Revenue Updates<br/>
							<img class="chart" border='0' alt='Google Chart' src='http://chart.apis.google.com/chart?chxt=x,y&chtt=Revenue&cht=p3&chxl=Nov,Dec,Jan,Feb,Mar,Apr,May,June&chs=300x200&chd=s:AHNbko8&chf=bg,s,' />
							<div class="list" data-role="header" data-position="fixed" data-nobackbtn="true">
							 <div class="table-3cols">
							 <div class="table-cell">Col1 ............</div>
							 <div class="table-cell">Col2 ............</div>
							 <div class="table-cell">Col3 ............</div>
							 </div>
							 <div class="table-3cols">
							 <div class="table-cell">Col1 ............</div>
							 <div class="table-cell">Col2 ............</div>
							 <div class="table-cell">Col3 ............</div>
							 </div>
							 <div class="table-3cols">
							 <div class="table-cell">Col1 ............</div>
							 <div class="table-cell">Col2 ............</div>
							 <div class="table-cell">Col3 ............</div>
							 </div>
							</div>
		    	        </a>
		       		</div>
		
			        <div class="ui-block-b">
			            <a class="quadrant" data-theme="d" data-dividertheme="c" href="#report-main3" data-theme="e" data-role="button" data-transition="pop">Sales Services<br/>
							<img class="chart" border='0' alt='Google Chart' src='http://chart.apis.google.com/chart?chxt=x,y&chtt=Revenue&cht=bvs&chxl=Nov,Dec,Jan,Feb,Mar,Apr,May,June&chs=300x200&chd=s:AHNbko8&chco=009999&chf=bg,s,' />
							<div class="list" data-role="header" data-position="fixed" data-nobackbtn="true">
							 <div class="table-3cols">
							 <div class="table-cell">Col1 ............</div>
							 <div class="table-cell">Col2 ............</div>
							 <div class="table-cell">Col3 ............</div>
							 </div>
							 <div class="table-3cols">
							 <div class="table-cell">Col1 ............</div>
							 <div class="table-cell">Col2 ............</div>
							 <div class="table-cell">Col3 ............</div>
							 </div>
							 <div class="table-3cols">
							 <div class="table-cell">Col1 ............</div>
							 <div class="table-cell">Col2 ............</div>
							 <div class="table-cell">Col3 ............</div>
							 </div>
							</div>
			            </a>
			        </div>
		        </div><!-- /grid-a -->
		
		        <div class="ui-grid-a">
			        <div class="ui-block-a">
		    	        <a class="quadrant" data-theme="d" data-dividertheme="c" href="#report-main4" data-theme="e" data-role="button" data-transition="pop">Service Management Updates<br/>
							<img class="chart" border='0' alt='Google Chart' src='http://chart.apis.google.com/chart?chxt=x,y&chtt=Revenue&cht=bvs&chxl=Nov,Dec,Jan,Feb,Mar,Apr,May,June&chs=300x200&chd=s:HLPYdgs,ATYgtw8&chco=FF3333,006600&chf=bg,s,' />
							<div class="list" data-role="header" data-position="fixed" data-nobackbtn="true">
							 <div class="table-3cols">
							 <div class="table-cell">Col1 ............</div>
							 <div class="table-cell">Col2 ............</div>
							 <div class="table-cell">Col3 ............</div>
							 </div>
							 <div class="table-3cols">
							 <div class="table-cell">Col1 ............</div>
							 <div class="table-cell">Col2 ............</div>
							 <div class="table-cell">Col3 ............</div>
							 </div>
							 <div class="table-3cols">
							 <div class="table-cell">Col1 ............</div>
							 <div class="table-cell">Col2 ............</div>
							 <div class="table-cell">Col3 ............</div>
							 </div>
							</div>
		    	        </a>
			        </div>
		
			        <div class="ui-block-b">
			            <a class="quadrant" data-theme="d" data-dividertheme="c" href="" data-theme="e" data-role="button" data-transition="pop">DLM Services<br/>
							<img class="chart" border='0' alt='Google Chart' src='http://chart.apis.google.com/chart?chxt=x,y&chtt=Revenue&cht=p&chxl=Nov,Dec,Jan,Feb,Mar,Apr,May,June&chs=300x200&chd=s:HLPYdgs,ATYgtw8&chco=FF3333,006600&chf=bg,s,' />
							<div class="list" data-role="header" data-position="fixed" data-nobackbtn="true">
							 <div class="table-3cols">
							 <div class="table-cell">Col1 ............</div>
							 <div class="table-cell">Col2 ............</div>
							 <div class="table-cell">Col3 ............</div>
							 </div>
							 <div class="table-3cols">
							 <div class="table-cell">Col1 ............</div>
							 <div class="table-cell">Col2 ............</div>
							 <div class="table-cell">Col3 ............</div>
							 </div>
							 <div class="table-3cols">
							 <div class="table-cell">Col1 ............</div>
							 <div class="table-cell">Col2 ............</div>
							 <div class="table-cell">Col3 ............</div>
							 </div>
							</div>
			            </a>
			        </div>
		
	        	</div><!-- /grid-a -->
	
		        <div class="ui-grid-a" style="height:0px">
			        <div class="ui-block-a">
			        </div>
		        </div><!-- /grid-a -->
		
				<p style="background:white; opacity:0.5; padding:5px; -moz-border-radius:.6em; -webkit-border-radius:.6em; border-radius:.6em;">The short URL is http://goo.gl/vmPDO (stable) and http://goo.gl/fYt4u (dev).  The application is in BETA stage, which means it is experimental and subject to changes or improvements as we learn more from our customers and users. [V1B102]</p>
				
				<div data-theme="a" class="iconcon ui-bar ui-bar-a ui-footer" data-position="fixed" data-role="footer">
					<img class="footer" border='0' alt='Footer' src='img/footer1.jpg' />
				</div>

			</div>

		</div> <!-- end of main page 1 -->


		<!-- begin of main page 2 -->
		<div data-role="page" data-theme="a" data-dividertheme="d" data-counttheme="e" id="main2">
			<div data-role="header" data-nobackbtn="true" data-position="inline">
				<a href="#main1" data-icon="arrow-l" class="ui-btn-left">Previous</a>
				<h1>Verizon Enterprise Dashboard</h1>
			</div>

			<div data-role="content" data-theme="c">

		        <div class="ui-grid-a">

			        <div class="ui-block-a">
			            <a id="q6" class="quadrant" data-theme="d" data-dividertheme="c" href="" data-theme="e" data-role="button" data-transition="pop">Finance and People<br/>Services<br/>
							<img class="chart" border='0' alt='Google Chart' src='http://chart.apis.google.com/chart?chxt=x,y&chtt=Revenue&cht=lc&chxl=Nov,Dec,Jan,Feb,Mar,Apr,May,June&chs=300x200&chd=s:HLPYdgs,ATYgtw8&chco=FF3333,006600&chf=bg,s,' />
							<div class="list" data-role="header" data-position="fixed" data-nobackbtn="true">
							 <div class="table-3cols">
							 <div class="table-cell">Col1 ............</div>
							 <div class="table-cell">Col2 ............</div>
							 <div class="table-cell">Col3 ............</div>
							 </div>
							 <div class="table-3cols">
							 <div class="table-cell">Col1 ............</div>
							 <div class="table-cell">Col2 ............</div>
							 <div class="table-cell">Col3 ............</div>
							 </div>
							 <div class="table-3cols">
							 <div class="table-cell">Col1 ............</div>
							 <div class="table-cell">Col2 ............</div>
							 <div class="table-cell">Col3 ............</div>
							 </div>
							</div>
			            </a>
			        </div>

		        </div>
	        </div>
		</div> <!-- end of main page 2 -->




		<div data-role="page" data-theme="a" data-dividertheme="d" data-counttheme="e" id="report-main1">
			<div data-role="header">
				<h1>Report</h1>
			</div>
			<div id="container1"></div>
			<script>
			new easyXDM.Socket({
			    remote: "/report/redirect.jsp?rpt=vz_revenue",
			    container: document.getElementById("container1"),
				props: {
			        style: {
			            width: "100%",
			            height: "100%",
			            border: "1px solid blue",
			        }
			    }
			});
			</script>



				<div data-theme="a" class="iconcon ui-bar ui-bar-a ui-footer" data-role="footer" role="contentinfo">
					<img class="footer" border='0' alt='Footer' src='img/footer1.jpg' />
				</div>
        	</div>
        </div>


		<div data-role="page" data-theme="a" data-dividertheme="d" data-counttheme="e" id="report-main3">
			<div data-role="header">
				<h1>Report</h1>
			</div>
			<div id="container3">
			</div>
			<script>
			new easyXDM.Socket({
    			remote: "/report/redirect.jsp?rpt=master",
			    container: document.getElementById("container3"),
				props: {
			        style: {
			            width: "100%",
			            height: "100%",
			            border: "1px solid blue",
			        }
			    }
			});
			</script>

			<div data-theme="a" class="iconcon ui-bar ui-bar-a ui-footer" data-role="footer" role="contentinfo">
				<img class="footer" border='0' alt='Footer' src='img/footer1.jpg' />
			</div>
        	</div>
		</div>

		<div data-role="page" data-theme="a" data-dividertheme="d" data-counttheme="e" id="report-main4">
			<div data-role="header">
				<h1>Report</h1>
			</div>
			<div id="container4">
			</div>
			<script>
			new easyXDM.Socket({
    			remote: "/report/redirect.jsp?rpt=SimpleChartAPI",
			    container: document.getElementById("container4"),
				props: {
			        style: {
			            width: "100%",
			            height: "100%",
			            border: "1px solid blue",
			        }
			    }
			});
			</script>

			<div data-theme="a" class="iconcon ui-bar ui-bar-a ui-footer" data-role="footer" role="contentinfo">
				<img class="footer" border='0' alt='Footer' src='img/footer1.jpg' />
        	</div>
		</div>


		<div data-role="page" data-theme="a" data-dividertheme="d" data-counttheme="e">
			<div data-role="header" data-nobackbtn="true" data-position="inline">
				<a href="" data-icon="gear" class="ui-btn-left">Options</a>
				<h1>Verizon Enterprise Dashboard</h1>
				<a href="" data-icon="arrow-r" class="ui-btn-right">More</a>
			</div>			
			<div data-role="content" data-theme="c">

        <div class="ui-grid-b">
	        <div class="ui-block-a">

	        <div class="ui-block-c">
	            <a id="q6" class="quadrant" data-theme="d" data-dividertheme="c" href="" data-theme="e" data-role="button" data-transition="pop">Finance and People<br/>Services<br/>
<img class="chart" border='0' alt='Google Chart' src='http://chart.apis.google.com/chart?chxt=x,y&chtt=Revenue&cht=lc&chxl=Nov,Dec,Jan,Feb,Mar,Apr,May,June&chs=300x200&chd=s:HLPYdgs,ATYgtw8&chco=FF3333,006600&chf=bg,s,' />
<div class="list" data-role="header" data-position="fixed" data-nobackbtn="true">
 <div class="table-3cols">
 <div class="table-cell">Col1 ............</div>
 <div class="table-cell">Col2 ............</div>
 <div class="table-cell">Col3 ............</div>
 </div>
 <div class="table-3cols">
 <div class="table-cell">Col1 ............</div>
 <div class="table-cell">Col2 ............</div>
 <div class="table-cell">Col3 ............</div>
 </div>
 <div class="table-3cols">
 <div class="table-cell">Col1 ............</div>
 <div class="table-cell">Col2 ............</div>
 <div class="table-cell">Col3 ............</div>
 </div>
</div>
	            </a>
	        </div>
        </div><!-- /grid-a -->

        <div class="ui-grid-a" style="height:0px">
	        <div class="ui-block-a">
	        </div>
        </div><!-- /grid-a -->

<p style="background:white; opacity:0.5; padding:5px; -moz-border-radius:.6em; -webkit-border-radius:.6em; border-radius:.6em;">The short URL is http://goo.gl/vmPDO (stable) and http://goo.gl/fYt4u (dev).  The application is in BETA stage, which means it is experimental and subject to changes or improvements as we learn more from our customers and users. [V1B102]</p>

<div data-theme="a" class="iconcon ui-bar ui-bar-a ui-footer" data-position="fixed" data-role="footer">
	<img class="footer" border='0' alt='Footer' src='img/footer1.jpg' />
</div>


			</div>
		</div>


        	</div>
		</div>

  	</body>  
</html>
