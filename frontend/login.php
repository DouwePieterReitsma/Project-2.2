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

function login($username, $password)
{
    $conn = DbConnection::getInstance()->getConnection();

    $hash = password_hash($password);

    



    $_SESSION["loggedin"] = true;
}