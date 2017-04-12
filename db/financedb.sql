-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Apr 12, 2017 at 05:41 PM
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
  `Password` varchar(31) NOT NULL,
  `Forename` varchar(30) NOT NULL,
  `Surname` varchar(30) NOT NULL,
  `Email` varchar(40) NOT NULL,
  `Income` varchar(9) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `accounts`
--

INSERT INTO `accounts` (`AccountID`, `Password`, `Forename`, `Surname`, `Email`, `Income`) VALUES
(4545, 'aaa', 'Alex', 'Morrison', 'asmorrison@hotmail.co.uk', '10000.0'),
(4646, 'aaa', 'Alex', 'Test', 'test@gmail.com', '');

-- --------------------------------------------------------

--
-- Table structure for table `expenses`
--

CREATE TABLE `expenses` (
  `ExpenseID` int(11) NOT NULL,
  `AccountID` int(11) NOT NULL,
  `Expense` varchar(30) DEFAULT NULL,
  `Value` varchar(30) DEFAULT NULL,
  `DateAdded` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `utilities`
--

CREATE TABLE `utilities` (
  `UtilityID` int(11) NOT NULL,
  `AccountID` int(11) NOT NULL,
  `Utility` varchar(30) DEFAULT NULL,
  `Value` varchar(30) DEFAULT NULL,
  `BillingCycle` varchar(30) DEFAULT NULL,
  `DateAdded` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `utilities`
--

INSERT INTO `utilities` (`UtilityID`, `AccountID`, `Utility`, `Value`, `BillingCycle`, `DateAdded`) VALUES
(1, 4545, 'Gas', '75.76', '3 Month', '12-Apr-2017'),
(2, 4545, 'Water', '74.36', '3 Month', '12-Apr-2017'),
(3, 4545, 'Water+', '1000', '4 Month', '12-Apr-2017'),
(4, 4545, 'Water+', '1000', '4 Month', '12-Apr-2017'),
(5, 4545, 'Water+', '1000', '4 Month', '12-Apr-2017');

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
  ADD PRIMARY KEY (`ExpenseID`),
  ADD KEY `AccountID` (`AccountID`);

--
-- Indexes for table `utilities`
--
ALTER TABLE `utilities`
  ADD PRIMARY KEY (`UtilityID`),
  ADD KEY `AccountID` (`AccountID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `expenses`
--
ALTER TABLE `expenses`
  MODIFY `ExpenseID` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `utilities`
--
ALTER TABLE `utilities`
  MODIFY `UtilityID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `expenses`
--
ALTER TABLE `expenses`
  ADD CONSTRAINT `FK_Expenses_Accounts` FOREIGN KEY (`AccountID`) REFERENCES `accounts` (`AccountID`);

--
-- Constraints for table `utilities`
--
ALTER TABLE `utilities`
  ADD CONSTRAINT `FK_Utility_Accounts` FOREIGN KEY (`AccountID`) REFERENCES `accounts` (`AccountID`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
