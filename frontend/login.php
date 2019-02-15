<?php

require "database.php";

session_start();

if(isset($_SESSION["loggedin"]) && $_SESSION["loggedin"] == true)
{
    header("Location: index.php");
    exit;
}

function login($username, $password)
{
    $conn = DbConnection::getInstance()->getConnection();

    $query = "SELECT password FROM users WHERE username = :username";

    $stmt = $conn->prepare($query);
    $stmt->bindParam(":username", $username);
    $stmt->execute();

    if ($stmt->rowCount() == 0)
    {
        return false;
    }

    $result = $stmt->fetch(PDO::FETCH_ASSOC);

    $hash = $result["password"];

    return password_verify($password, $hash);
}

$username = array_key_exists('username', $_POST) ? trim($_POST['username']) : null;
$password = array_key_exists('password', $_POST) ? trim($_POST['password']) : null;

if(!empty($username) || !empty($password))
{
    if(login($username, $password))
    {
        $_SESSION["loggedin"] = true;

        header("Location: index.php");
        exit;
    }
    else
    {
        $_SESSION["loggedin"] = false;

        header("Location: loginpage.php");

        exit;
    }
}