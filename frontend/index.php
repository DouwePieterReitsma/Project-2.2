<?php

session_start();

$_SESSION["title"] = "Home";

?>

<html>
<head>
    <title><?php echo $_SESSION['title']; ?></title>
</head>
<body>
    
</body>
</html>