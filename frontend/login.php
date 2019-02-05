<?php

require "database.php";

session_start();

if(isset($_SESSION["loggedin"]) && $_SESSION["loggedin"] == true)
{
    header("Location: index.php");
    exit;
}

$username = array_key_exists('username', $_POST) ? trim($_POST['username']) : null;
$password = array_key_exists('password', $_POST) ? trim($_POST['password']) : null;

function getSaltForUsername($username)
{
    $conn = DbConnection::getInstance()->getConnection();

    $query = "SELECT salt FROM users WHERE username = :username";

    $stmt = $conn->prepare($query);
    $stmt->bindValue(":username", $username);

    $stmt->execute();

    //todo
}

function login()
{
    $conn = DbConnection::getInstance()->getConnection();
}