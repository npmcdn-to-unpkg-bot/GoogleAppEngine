<!-- source: http://www.devx.com/webdev/Article/37004 -->
<?php
//    header("Content-Type: image/svg+xml");
	$randnum = rand(10, 50);
$svg = <<< HED
   <svg width="310" height="140" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink">
     <g style="stroke:black;fill:lightgreen" transform="translate(30,30)">
       <rect x="10" y="10" width="100" height="{$randnum}" style="stroke-width:4"/>
       <circle cx="170" cy="25" r="20" style="stroke-width:4"/>
       <line x1="265" y1="10" x2="200" y2="70" style="stroke-width:4"/>
       <text x="80" y="90" style="font:size: 8">Basic shapes</text>
     </g>
   </svg>
HED;
//echo $svg;
?>