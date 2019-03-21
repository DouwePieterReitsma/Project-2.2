<?php

require "database.php";

session_start();

if(isset($_SESSION["loggedin"]) && $_SESSION["loggedin"] == true)
{
    header("Location: index.php");
    exit;
}

function usernameIsAvailable($username)
{
    $conn = DbConnection::getInstance()->getConnection();

    $sql = "SELECT * FROM users WHERE username = :username";

    $stmt = $conn->prepare($sql);
    $stmt->bindParam(':username', $username);
    $stmt->execute();

    $result = $stmt->rowCount() == 0;

    return $result;
}

function register($username, $password)
{
    $conn = DbConnection::getInstance()->getConnection();

    $password = password_hash($password, PASSWORD_DEFAULT);

    $sql = "INSERT INTO `users` (`username`, `password`) VALUES (:username, :password)";

    $stmt = $conn->prepare($sql);
    $stmt->bindParam(":username", $username);
    $stmt->bindParam(":password", $password);
    $stmt->execute();
}

$username = array_key_exists('username', $_POST) ? trim($_POST['username']) : null;
$password = array_key_exists('password', $_POST) ? trim($_POST['password']) : null;

$allowedHosts = "hanze.nl";

if(!empty($username) || !empty($password))
{
    $available = usernameIsAvailable($username);


    if($available)
    {
        register($username, $password);

        $_SESSION['loggedin'] = true;

        header("Location: index.php");

        exit;
    }
    else
    {
        header("Location: loginpage.php");

        $_SESSION['loggedin'] = false;

        exit;
    }
}