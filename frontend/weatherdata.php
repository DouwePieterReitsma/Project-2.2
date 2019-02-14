<?php

require "database.php";

header("Access-Control-Allow-Origin: *");
header("Content-Type: application/json");

class WeatherDataParser
{
    private $csv = null;

    public function __construct()
    {

    }

    public function parse($filename)
    {
        $rows = array_map("str_getcsv", file($filename));
        $header = array_shift($rows);

        $csv = array();

        foreach($rows as $row)
        {
            $this->csv[] = array_combine($header, $row);
        }
    }

    private function getStationInfo($stationNumber)
    {
        if($stationNumber == null || !is_integer($stationNumber))
        {
            return null;
        }

        $conn = DbConnection::getInstance()->getConnection();

        $query = "SELECT * FROM stations WHERE stn = :station";

        $stmt = $conn->prepare($query);
        $stmt->bindParam(":station", $stationNumber);
        $stmt->execute();
        
        $result = $stmt->fetch(PDO::FETCH_ASSOC);

        return $result;
    }

    public function getGeoJSON()
    {
        if($this->csv == null || empty($this->csv))
        {
            return null;
        }

        $result = (object)array(
            "type" => "FeatureCollection",
            "features" => array()
        );

        foreach($this->csv as $row)
        {
            $stationInfo = $this->getStationInfo((int)$row["STN"]);

            if($stationInfo == null)
            {
                continue;
            }

            $feature = array(
                "type" => "Feature",
                "properties" => array(
                    "station" => $stationInfo["stn"],
                    "name" => $stationInfo["name"],
                    "country" => $stationInfo["country"],
                    "elevation" => $stationInfo["elevation"],
                    "datetime" => new DateTime($row["DATE"] . " " . $row["TIME"]),
                    "temperature" => (float)$row["TEMP"],
                    "dewpoint" => (float)$row["DEWP"],
                    "atmosphericPressureStationLevel" => (float)$row["STP"],
                    "atmosphericPressureSeaLevel" => (float)$row["SLP"],
                    "visibility" => (float)$row["VISIB"],
                    "windSpeed" => (float)$row["WDSP"],
                    "precipitate" => (float)$row["PRCP"],
                    "snowfall" => (float)$row["SNDP"],
                    "frshtt" => $row["FRSHTT"],
                    "cloudCoverage" => (float)$row["CLDC"],
                    "windDirection" => (int)$row["WNDDIR"]
                ),
                "geometry" => array(
                    "type" => "Point",
                    "coordinates" => [(float)$stationInfo["longitude"], (float)$stationInfo["latitude"]]
                )
            );

            $result->features[] = $feature;
        }

        return json_encode($result);
    }
}

$parser = new WeatherDataParser();

$config = include("config.php");

$files = glob($config->directory . "*.csv");

foreach($files as $filename)
{
    $parser->parse($filename);
}

echo $parser->getGeoJSON();