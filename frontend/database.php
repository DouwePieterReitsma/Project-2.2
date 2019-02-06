<?php

class DbConnection
{
    private static $instance = NULL;
    private $connection;

    private function __construct()
    {
        $config = include("config.php");

        $this->connection = new PDO("mysql:host=$config->host;dbname=$config->dbname", $config->username, $config->password);
    }

    public static function getInstance()
    {
        if(!self::$instance)
        {
            self::$instance = new DbConnection();
        }

        return self::$instance;
    }

    public function getConnection()
    {
        return $this->connection;
    }
}