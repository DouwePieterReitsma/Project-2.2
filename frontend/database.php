<?php

class DbConnection
{
    private static $instance = NULL;
    private $connection;

    private $host = "localhost";
    private $username = "";
    private $password = "";
    private $name = "";

    private function __construct()
    {
        $this->connection = new PDO("mysql:host={$this->host};dbname={$this->name}", $this->username, $this->password);
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