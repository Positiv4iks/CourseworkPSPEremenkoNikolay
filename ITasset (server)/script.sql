create database if not exists `amort`;

use `amort`;

create table if not exists `admins` (
    `id` int AUTO_INCREMENT PRIMARY KEY,
    `login` varchar(40) not null,
    `password` varchar(40) not null
    ) DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

create table if not exists `users` (
    `id` int AUTO_INCREMENT PRIMARY KEY,
    `login` varchar(40) not null,
    `password` varchar(40) not null
    ) DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

create table if not exists `assets` (
    `id` int AUTO_INCREMENT PRIMARY KEY,
    `name` varchar(30),
    `price` int not null,
    `termOfUse` int not null
    ) DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

create table if not exists `depreciation` (
    `id` int AUTO_INCREMENT PRIMARY KEY,
    `name` varchar(30),
    `yearPercent` double not null,
    `yearPrice` double not null,
    `monthPercent` double not null,
    `monthPrice` double not null,
    `payBack` double not null,
    `rentability` double not null,
    `incomeMoney` double not null
    ) DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

create table if not exists `employees` (
    `id` int AUTO_INCREMENT PRIMARY KEY,
    `name` varchar(30),
    `surname` varchar(30),
    `department` varchar(30)
    `idAssets` int not null
    ) DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


