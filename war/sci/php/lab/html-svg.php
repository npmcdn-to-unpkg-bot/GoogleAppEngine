<?php
include('svg.php');
$len = sizeof($svg);
header("Content-Length: ".$len);
echo $svg;
?>