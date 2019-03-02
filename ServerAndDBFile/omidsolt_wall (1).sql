-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 02, 2019 at 02:01 PM
-- Server version: 10.1.30-MariaDB
-- PHP Version: 7.2.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `omidsolt_wall`
--

-- --------------------------------------------------------

--
-- Table structure for table `tbl_itemwall_cat`
--

CREATE TABLE `tbl_itemwall_cat` (
  `cat_id` int(11) NOT NULL,
  `cat_father` int(11) NOT NULL,
  `cat_name` varchar(255) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tbl_itemwall_cat`
--

INSERT INTO `tbl_itemwall_cat` (`cat_id`, `cat_father`, `cat_name`) VALUES
(1, 3, 'املاک'),
(2, 3, 'وسایل نقلیه'),
(3, 3, 'لوازم الکترونیکی'),
(4, 3, 'مربوط به خانه'),
(5, 3, 'خدمات'),
(6, 3, 'وسایل شخصی'),
(7, 3, 'سرگرمی و فراغت');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_itemwall_item`
--

CREATE TABLE `tbl_itemwall_item` (
  `item_id` int(50) NOT NULL,
  `item_topic` varchar(255) NOT NULL,
  `item_cat_id` int(50) NOT NULL,
  `item_location` varchar(255) NOT NULL,
  `item_price` int(11) DEFAULT NULL,
  `item_peresent` varchar(255) NOT NULL,
  `item_status` int(11) NOT NULL,
  `image_url1` varchar(255) DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  `image_url2` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tbl_itemwall_item`
--

INSERT INTO `tbl_itemwall_item` (`item_id`, `item_topic`, `item_cat_id`, `item_location`, `item_price`, `item_peresent`, `item_status`, `image_url1`, `user_id`, `image_url2`) VALUES
(3, 'ات', 6, 'تت', 66, 'تتنن', 1, 'images/31HuntingDeer-SS-Post.jpg', 1, 'images/32Screenshot_2019-02-21-16-37-59.png');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_itemwall_users`
--

CREATE TABLE `tbl_itemwall_users` (
  `user_id` int(11) NOT NULL,
  `full_name` varchar(100) NOT NULL,
  `username` varchar(40) NOT NULL,
  `password` varchar(40) NOT NULL,
  `email` varchar(100) NOT NULL,
  `token` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tbl_itemwall_users`
--

INSERT INTO `tbl_itemwall_users` (`user_id`, `full_name`, `username`, `password`, `email`, `token`) VALUES
(1, 'John Doe', '02155', '634a2b2795e574c078a02f89b70b8a56', 'dummy_user@gmail.com', 'es23x8q20J1jTf5EnXoKLZrtRyWGHF1Y'),
(2, 'Cpt Morgan', '', '', 'mg.user001@gmail.com', NULL),
(3, 'hhg', 'gfhh', '2735e393e9e5dd555592598edd30484f', 'hghg', NULL),
(4, 'hhg', '09199047265', '2735e393e9e5dd555592598edd30484f', 'hghg', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_userweb`
--

CREATE TABLE `tbl_userweb` (
  `user_id` int(11) NOT NULL,
  `password` varchar(255) COLLATE utf32_persian_ci NOT NULL,
  `username` varchar(255) COLLATE utf32_persian_ci NOT NULL,
  `token` varchar(255) COLLATE utf32_persian_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf32 COLLATE=utf32_persian_ci;

--
-- Dumping data for table `tbl_userweb`
--

INSERT INTO `tbl_userweb` (`user_id`, `password`, `username`, `token`) VALUES
(1, '634a2b2795e574c078a02f89b70b8a56', 'admin', 's1zef6uklhkzR1GzaGEEzpIqc8tif6NZ');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tbl_itemwall_cat`
--
ALTER TABLE `tbl_itemwall_cat`
  ADD PRIMARY KEY (`cat_id`);

--
-- Indexes for table `tbl_itemwall_item`
--
ALTER TABLE `tbl_itemwall_item`
  ADD PRIMARY KEY (`item_id`);

--
-- Indexes for table `tbl_itemwall_users`
--
ALTER TABLE `tbl_itemwall_users`
  ADD PRIMARY KEY (`user_id`);

--
-- Indexes for table `tbl_userweb`
--
ALTER TABLE `tbl_userweb`
  ADD PRIMARY KEY (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tbl_itemwall_cat`
--
ALTER TABLE `tbl_itemwall_cat`
  MODIFY `cat_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `tbl_itemwall_item`
--
ALTER TABLE `tbl_itemwall_item`
  MODIFY `item_id` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `tbl_itemwall_users`
--
ALTER TABLE `tbl_itemwall_users`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `tbl_userweb`
--
ALTER TABLE `tbl_userweb`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
