-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 10, 2024 at 10:21 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `library_management_system`
--

-- --------------------------------------------------------

--
-- Table structure for table `book`
--

CREATE TABLE `book` (
  `isbn` varchar(20) NOT NULL,
  `book_name` varchar(255) NOT NULL,
  `edition` varchar(150) NOT NULL,
  `author` varchar(150) NOT NULL,
  `genre` varchar(50) NOT NULL,
  `total_copies` int(11) NOT NULL,
  `no_available_copies` int(11) NOT NULL,
  `issued_year` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `book`
--

INSERT INTO `book` (`isbn`, `book_name`, `edition`, `author`, `genre`, `total_copies`, `no_available_copies`, `issued_year`) VALUES
('121212', 'Harry Potter 1', '1st edition', 'J K Rowling', 'Fantasy', 3, 3, 1997),
('131313', 'Harry Potter 2', '2nd edition', 'J K Rowling', 'Fantasy', 2, 2, 1998),
('135467', 'Famous Five 1', '1st Edition', 'Enid Blyton', 'Mystery', 5, 5, 1942),
('141414', 'Harry Potter 3', '1st edition', 'J K Rowling', 'Fiction', 4, 4, 1999),
('161616', 'The Lord of the Rings', '1st edition', 'Jhon Ronald', 'Fiction', 3, 3, 1954),
('171717', 'Dune', '1st edition', 'Frank Herbert', 'Sceince Fiction', 2, 2, 1965),
('181818', 'The Hobbit', '1st edition', 'Jhon Ronald', 'Fantasy', 6, 6, 1937);

-- --------------------------------------------------------

--
-- Table structure for table `lend`
--

CREATE TABLE `lend` (
  `lend_id` varchar(50) NOT NULL,
  `isbn` varchar(20) NOT NULL,
  `patron_id` varchar(50) NOT NULL,
  `lended_date` date NOT NULL,
  `due_date` date NOT NULL,
  `fine` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `librarian`
--

CREATE TABLE `librarian` (
  `full_name` varchar(150) NOT NULL,
  `nic` varchar(100) NOT NULL,
  `address` varchar(255) NOT NULL,
  `tp_number` varchar(25) NOT NULL,
  `hourly_rate` double NOT NULL,
  `gender` varchar(50) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `privilege` varchar(25) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `librarian`
--

INSERT INTO `librarian` (`full_name`, `nic`, `address`, `tp_number`, `hourly_rate`, `gender`, `username`, `password`, `privilege`) VALUES
('Bawantha Jayamal', '124354235', '221/12/12, Galuoya mawatha, Galle', '112223334', 7.8, 'Male', 'bawa', 'bawa123', 'Admin'),
('Chathura Udayanga', '679698h', '721/14/1C, Mahalwarawa', '779720005', 8.5, 'Male', 'chathura', 'chathu', 'Admin'),
('Kasun Madusanka', '5674594v', '720/1/22, Shanthalokagama', '112375211', 7.5, 'Female', 'kasun', 'kassa', 'Staff');

-- --------------------------------------------------------

--
-- Table structure for table `patron`
--

CREATE TABLE `patron` (
  `patron_id` varchar(50) NOT NULL,
  `full_name` varchar(255) NOT NULL,
  `nic` varchar(25) NOT NULL,
  `birthday` date NOT NULL,
  `address` varchar(255) NOT NULL,
  `telephone` varchar(20) NOT NULL,
  `gender` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `patron`
--

INSERT INTO `patron` (`patron_id`, `full_name`, `nic`, `birthday`, `address`, `telephone`, `gender`) VALUES
('P001', 'Yasitha Madusha', '20007135', '2024-05-16', '214/1. Shanthalokagama', '07131058', 'Female'),
('P002', 'Kavinda Sandun', '668464', '2024-06-13', '6646416549', '4654687', 'Male'),
('P003', 'Dasun Sampath', '4654654', '2024-06-14', 'sdhghdgl3787t', '694654', 'Female'),
('P004', 'Maheema Sanjani', '25505462', '2011-06-17', '221/14/ godagama.', '011244575', 'Female');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `book`
--
ALTER TABLE `book`
  ADD PRIMARY KEY (`isbn`);

--
-- Indexes for table `lend`
--
ALTER TABLE `lend`
  ADD PRIMARY KEY (`lend_id`),
  ADD KEY `isbn` (`isbn`),
  ADD KEY `patronid` (`patron_id`);

--
-- Indexes for table `librarian`
--
ALTER TABLE `librarian`
  ADD PRIMARY KEY (`username`);

--
-- Indexes for table `patron`
--
ALTER TABLE `patron`
  ADD PRIMARY KEY (`patron_id`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `lend`
--
ALTER TABLE `lend`
  ADD CONSTRAINT `isbn` FOREIGN KEY (`isbn`) REFERENCES `book` (`isbn`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `patronid` FOREIGN KEY (`patron_id`) REFERENCES `patron` (`patron_id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
