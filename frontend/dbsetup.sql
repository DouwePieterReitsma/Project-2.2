CREATE DATABASE IF NOT EXISTS `weatherapp`;

USE `weatherapp`;

CREATE TABLE IF NOT EXISTS `users` (
    `id` int NOT NULL AUTO_INCREMENT,
    `username` varchar(100) NOT NULL,
    `password` char(64) NOT NULL CHARACTER SET ascii,
    `salt` char(10) NOT NULL CHARACTER SET ascii,
    PRIMARY KEY(`id`)
);

