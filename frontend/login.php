<?php

session_start();

$username = array_key_exists('username', $_POST) ? trim($_POST['username']) : null;
$password = array_key_exists('password', $_POST) ? trim($_POST['password']) : null;

if(empty($username) || empty($password))
{
    $_SESSION['message'] = "Invalid credentials.";
    exit;
}