-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Apr 21, 2017 at 10:23 PM
-- Server version: 10.1.19-MariaDB
-- PHP Version: 5.6.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `financedb`
--

-- --------------------------------------------------------

--
-- Table structure for table `accounts`
--

CREATE TABLE `accounts` (
  `AccountID` int(11) NOT NULL,
  `Password` varchar(60) NOT NULL,
  `Forename` varchar(30) DEFAULT '',
  `Surname` varchar(30) DEFAULT '',
  `Email` varchar(40) DEFAULT '',
  `Income` varchar(20) DEFAULT '',
  `TaxFree` varchar(20) DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `accounts`
--

INSERT INTO `accounts` (`AccountID`, `Password`, `Forename`, `Surname`, `Email`, `Income`, `TaxFree`) VALUES
(2525, '$2a$10$VBSuMODXI6p/iIrRz05.ae.LrCATq9TwUslxi/bnx.7WaPQmgP1J.', '', '', '', '', ''),
(4343, '$2a$10$hVwkAlGP1quVHWJdTYPlGerF.XZ1ZluBA.RbT7M6jz7tI.WPGsaOK', 'Alex', 'Test', 'test@gmail.com', '10000.0', ''),
(4444, '$2a$10$MRhYIAxc4C7CaWjKd.EWye5H5vYF35bhW1eOL/3/LDLwUu13hCMfW', '', '', '', '', ''),
(4545, '$2a$10$nhAyVU7F2QBWMFMqUamu5epEvlfOPDAb6LJM6nkYdb6zzMhtmz1tO', 'Alex', 'Morrison', 'asmorrison@hotmail.co.uk', '16000.0', '11500.0'),
(5555, '$2a$12$VLGhtf1zxkskOT3PEzU6ie4FqlWucMsRdP8lGronBkvev0BBrlmAa', 'Alex', 'Morrison', 'Test@gmail.com', '14000.0', '11500.0'),
(7777, '$2a$12$fngptem0NU64qLhrXCbileqisVehYF31tXkti9IVS0vbMNnHR6TEu', '', '', '', '', '');

-- --------------------------------------------------------

--
-- Table structure for table `expenses`
--

CREATE TABLE `expenses` (
  `ExpenseID` int(11) NOT NULL,
  `AccountID` int(11) NOT NULL,
  `Expense` varchar(30) NOT NULL,
  `Value` varchar(30) NOT NULL,
  `DateAdded` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `expenses`
--

INSERT INTO `expenses` (`ExpenseID`, `AccountID`, `Expense`, `Value`, `DateAdded`) VALUES
(1, 4545, 'Cinema', '23.73', '22-Apr-2017');

-- --------------------------------------------------------

--
-- Table structure for table `utilities`
--

CREATE TABLE `utilities` (
  `UtilityID` int(11) NOT NULL,
  `AccountID` int(11) NOT NULL,
  `Utility` varchar(30) NOT NULL,
  `Value` varchar(30) NOT NULL,
  `BillingCycle` varchar(30) NOT NULL,
  `DateAdded` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `utilities`
--

INSERT INTO `utilities` (`UtilityID`, `AccountID`, `Utility`, `Value`, `BillingCycle`, `DateAdded`) VALUES
(1, 4545, 'Gas', '75.76', '3 Month', '12-Apr-2017'),
(2, 4545, 'Water', '45.26', '1 Month', '09-Apr-2017'),
(3, 4545, 'Electric', '74.22', '4 Month', '12-Apr-2017'),
(4, 4646, 'Council Tax', '121.34', '1 Month', '12-Apr-2017');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `accounts`
--
ALTER TABLE `accounts`
  ADD PRIMARY KEY (`AccountID`);

--
-- Indexes for table `expenses`
--
ALTER TABLE `expenses`
  ADD PRIMARY KEY (`ExpenseID`);

--
-- Indexes for table `utilities`
--
ALTER TABLE `utilities`
  ADD PRIMARY KEY (`UtilityID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `expenses`
--
ALTER TABLE `expenses`
  MODIFY `ExpenseID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `utilities`
--
ALTER TABLE `utilities`
  MODIFY `UtilityID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
