<?php
include('svg.php');
$svg = str_replace(array("\r", "\r\n", "\n"), ' ', $svg);
//$svg = 'test';
$svg = substr($svg, 0, 120);
$len = strlen($svg);
//print('size of svg '.$len)
header("Content-Type: text/plain");
//header("Content-Type: image/gif");
//header("Content-Type: image/svg+xml");
header("Content-Length: ".$len);
//header("Content-Length: ".100);
echo $svg;
?>