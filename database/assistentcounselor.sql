-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 28, 2025 at 08:42 AM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `anxietyapplication`
--

-- --------------------------------------------------------

--
-- Table structure for table `assistentcounselor`
--

CREATE TABLE `assistentcounselor` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `phone_number` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `assistentcounselor`
--

INSERT INTO `assistentcounselor` (`id`, `name`, `email`, `phone_number`, `password`) VALUES
(1, 'assistent1', 'assistent1@gmail.com', '0774865126', '$2y$10$R3KKALO2hU34S84U/9ynb.DPbGJT8zs1gq06wyRDEKXn/Uw/IpHJW'),
(2, 'assistent2', 'assistent2@gmail.com', '0776434721', '$2y$10$4Y90Ol.rV7Rah/fMSmgSGOWa6UNr0O82crCZqcEpPDsEOSAOg0RPK');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `assistentcounselor`
--
ALTER TABLE `assistentcounselor`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `assistentcounselor`
--
ALTER TABLE `assistentcounselor`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
