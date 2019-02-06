<?php

require "database.php";

header("Access-Control-Allow-Origin: *");
header("Content-Type: application/json");

function csvtojson($filename)
{
    $rows = array_map("str_getcsv", file($filename));
    $header = array_shift($rows);

    $csv = array();

    foreach($rows as $row)
    {
        $csv[] = array_combine($header, $row);
    }

    $json = json_encode($csv);

    return $json;
}


// todo
$json = csvtojson("21170.csv");

print_r($json);