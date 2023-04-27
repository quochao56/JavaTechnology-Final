<?php
$servername = "mysql-server";
$username = "haolq";
$password = "haolq123";
$dbname = "QLSV";
$conn = mysqli_connect($servername, $username, $password, $dbname);
if (!$conn) {
    die("Connection failed: " . mysqli_connect_error());
}
?>