-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Mar 04, 2019 at 01:54 PM
-- Server version: 10.2.22-MariaDB-log
-- PHP Version: 7.2.15

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
(1, 2, 'املاک'),
(2, 0, 'وسایل نقلیه'),
(3, 0, 'لوازم الکترونیکی'),
(4, 0, 'مربوط به خانه'),
(5, 0, 'خدمات'),
(6, 0, 'وسایل شخصی'),
(7, 0, 'سرگرمی و فراغت'),
(8, 2, 'شال');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_itemwall_item`
--

CREATE TABLE `tbl_itemwall_item` (
  `item_id` int(50) NOT NULL,
  `item_topic` varchar(255) NOT NULL,
  `item_cat_id` varchar(255) NOT NULL,
  `item_location` varchar(255) NOT NULL,
  `item_price` int(11) DEFAULT NULL,
  `item_peresent` varchar(255) NOT NULL,
  `item_status` int(11) NOT NULL,
  `image_url1` varchar(255) DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  `image_url2` varchar(255) DEFAULT NULL,
  `reason` varchar(255) NOT NULL DEFAULT ' '
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tbl_itemwall_item`
--

INSERT INTO `tbl_itemwall_item` (`item_id`, `item_topic`, `item_cat_id`, `item_location`, `item_price`, `item_peresent`, `item_status`, `image_url1`, `user_id`, `image_url2`, `reason`) VALUES
(4, 'خونه', '4', 'تهران', 520000, 'خونه مونه', 0, 'images/41HuntingDeer-SS-Post.jpg', 1, 'images/42Screenshot_2019-02-21-16-37-59.png', ''),
(3, 'ات', '2', 'تت', 66, 'تتنن', 0, 'images/32Screenshot_2019-02-21-16-37-59.png', 1, 'images/32Screenshot_2019-02-21-16-37-59.png', ''),
(5, 'chetani', '3', 'jivhk', 520000, 'chertan', 1, 'images/51IMG_20190223_062300.jpg', 1, 'images/52IMG_20190223_062257.jpg', ''),
(6, 'ماشین 206', '2', 'تهران', 5200000, 'ماشین نو نو', 1, 'images/61org.mozilla.firefox.png', 2, 'images/62Screenshot_2017-07-03-23-44-23.png', ''),
(7, 'ماشین کهنه', '3', 'تهران', 5200000, 'نداریم', 1, 'images/71ig_backup_code.jpg', 2, 'images/72IMG_20180829_235927.jpg', ''),
(8, 'مهرداد', '1', 'تهران', 500000, 'خیلی خفنم', 0, 'images/81HuntingDeer-SS-Post.jpg', 8, 'images/82Picture_03.jpg', 'قیمت زیاد'),
(9, 'تست دسته', '3', 'تهران', 65888, 'نداریم', 1, 'images/91ig_backup_code.jpg', 8, 'images/92HuntingDeer-SS-Post.jpg', ''),
(10, 'تهرانی', '4', 'تهران', 530000, 'اثل', 1, 'images/101ig_backup_code.jpg', 8, 'images/102HuntingDeer-SS-Post.jpg', ' ');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_itemwall_users`
--

CREATE TABLE `tbl_itemwall_users` (
  `user_id` int(11) NOT NULL,
  `full_name` varchar(100) DEFAULT NULL,
  `username` varchar(40) DEFAULT NULL,
  `password` varchar(40) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  `code` int(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tbl_itemwall_users`
--

INSERT INTO `tbl_itemwall_users` (`user_id`, `full_name`, `username`, `password`, `email`, `token`, `code`) VALUES
(1, 'John Doe', '0919', '634a2b2795e574c078a02f89b70b8a56', 'dummy_user@gmail.com', 'PMZMaLSLTICmumnZosjVjFRE7WX5aAJi', NULL),
(2, 'Cpt Morgan', '09888', '', 'mg.user001@gmail.com', NULL, NULL),
(3, 'hhg', 'gfhh', '2735e393e9e5dd555592598edd30484f', 'hghg', NULL, NULL),
(4, 'hhg', '552', '2735e393e9e5dd555592598edd30484f', 'hghg', NULL, NULL),
(5, '22', '09199047266', NULL, NULL, NULL, 9498),
(8, 'مهرداد', '09199047265', '634a2b2795e574c078a02f89b70b8a56', 'تند', 'CgAQQAMWK0ykeg19FRcGMEYE1TYK0fEv', 9166),
(9, 'مهرداد خان', '09030605627', '81dc9bdb52d04dc20036dbd8313ed055', 'mehrdad.seyfi2@gmail.com', 'zN0BdV7lAYZ3SG2KI5gFjuBlS5PMrpa3', 7124);

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
(1, '634a2b2795e574c078a02f89b70b8a56', 'admin', 'QqCDjcE9RsN0x6ynxuBXFi9eJSoONhBq');

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
  MODIFY `cat_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `tbl_itemwall_item`
--
ALTER TABLE `tbl_itemwall_item`
  MODIFY `item_id` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `tbl_itemwall_users`
--
ALTER TABLE `tbl_itemwall_users`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `tbl_userweb`
--
ALTER TABLE `tbl_userweb`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
