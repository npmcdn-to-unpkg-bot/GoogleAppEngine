$_REQUEST<?php
$addr=stripslashes($_REQUEST['addr']);
$xml = simplexml_load_file('http://www.google.com/ig/api?weather='.$addr);
$information = $xml->xpath("/xml_api_reply/weather/forecast_information/text()");
$current = $xml->xpath("/xml_api_reply/weather/current_conditions/text()");
$forecast_list = $xml->xpath("/xml_api_reply/weather/forecast_conditions/text()");
?>
<html>
    <head>
        <title>Google Weather API</title>
    </head>
    <body>
        <h1><?php print $information[0]->city['data'];
	?></h1>
        <h2>Today's weather</h2>
        <div class="weather">
            <img src="<?php print 'http://www.google.com'.strval($current[0]->icon['data'])?>" alt="weather"?>
            <span class="condition">
            <?php print $current[0]->temp_f['data'] ?>&deg; F,
            <?php print $current[0]->condition['data'] ?>
            </span>
        </div>
<?php
$week= Array();
$low= Array();
$high= Array();
$condition=Array();

?>
        <h2>Forecast</h2>
        <?php foreach ($forecast_list as $forecast) : ?>
        <div class="weather">
            <img src="<?php print 'http://www.google.com'.$forecast->icon['data']?>" alt="weather"?>
            <div><?php print $forecast->day_of_week['data']; ?></div>
            <span class="condition">
	            <?php print $forecast->low['data'] ?>&deg; F - <?php print $forecast->high['data'] ?>&deg; F,
	            <?php print $forecast->condition['data'] ?>

				<?php
				$week[]=$forecast->day_of_week['data'];
				$low[]=$forecast->low['data'];
				$high[]=$forecast->high['data'];
				$condition[]=$forecast->condition['data'];
				?>
            </span>
        </div>
        <?php endforeach ?>
    </body>
</html>

<?php
$content="City: ".$information[0]->city['data']."\n"."Temp F:".$current[0]->temp_f['data']."\n"."Weather: ".$current[0]->condition['data']."\n"."Week:".$week[0]."\n"."Low:".$low[0]."\n"."High:".$high[0];
//."\n"."Condition=".$condition[0];

$f = fopen("weather.txt", "wb");
fwrite($f,$content,strlen($content));
fclose($f);

?>