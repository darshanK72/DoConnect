-- phpMyAdmin SQL Dump
-- version 5.1.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 30, 2022 at 02:58 PM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 8.1.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `doconnect_database`
--

-- --------------------------------------------------------

--
-- Table structure for table `answers`
--

CREATE TABLE `answers` (
  `answer_id` bigint(20) NOT NULL,
  `answer` varchar(255) DEFAULT NULL,
  `is_approved` bit(1) NOT NULL,
  `question_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `answers`
--

INSERT INTO `answers` (`answer_id`, `answer`, `is_approved`, `question_id`, `user_id`) VALUES
(1, 'Engineering is the application of science and maths to solve problems. While scientists and inventors come up with innovations, it is engineers who apply these discoveries to the real world.', b'1', 2, 1),
(2, 'Inheritance in Java is a mechanism in which one object acquires all the properties and behaviors of a parent object. It is an important part of OOPs (Object Oriented programming system). Types: single, multilevel and hierarchical.', b'1', 1, 3),
(3, 'The application of science to practical uses such as the design of structures, machines, and systems. Engineering has many specialities such as civil engineering, chemical engineering, and mechanical engineering.', b'1', 2, 3),
(4, 'Engineering involves the application of the principles of science and mathematics to solve real world problems and to innovate new products and processes across a wide range of industries and applications.', b'1', 2, 3),
(5, 'IT personnel should be able to explain highly technical ideas in terms that are relatable for people outside the information technology industry. Your response can show how you can present ideas to people who work with you.', b'1', 3, 1),
(6, 'The biggest mistake people do is to divide the CTC by 12 and think that it\'s their in-hand salary which is not completely true.', b'1', 5, 1);

-- --------------------------------------------------------

--
-- Table structure for table `questions`
--

CREATE TABLE `questions` (
  `question_id` bigint(20) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `is_approved` bit(1) NOT NULL,
  `question` varchar(255) DEFAULT NULL,
  `topic` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `questions`
--

INSERT INTO `questions` (`question_id`, `description`, `is_approved`, `question`, `topic`, `user_id`) VALUES
(1, 'How inheritance works internally', b'1', 'What is Inheritance & types ', 'Programming Languages', 2),
(2, 'Are Engineers are necessary for nation building, and Why?', b'1', 'What is engineering', 'Engineering', 2),
(3, 'Why IT companies are hiring more peoples.', b'1', 'Explain an IT concept in simple terms?', 'Information Technology', 2),
(4, 'Managing business is very important task, what utilities will help me for that.', b'1', 'What online resources and utilities do you use to help you manage your business?', 'Business', 3),
(5, 'One of my friend got 6 LPA package, what will be his in hand salary', b'1', 'If one gets a \'package\' of 6 LPA, does it mean that the in hand salary is 50k PM?', 'Business', 3);

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

CREATE TABLE `role` (
  `role_id` bigint(20) NOT NULL,
  `role` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`role_id`, `role`) VALUES
(1, 'ROLE_ADMIN'),
(2, 'ROLE_USER');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `user_id` bigint(20) NOT NULL,
  `email` varchar(50) DEFAULT NULL,
  `first_name` varchar(15) DEFAULT NULL,
  `is_enabled` bit(1) NOT NULL,
  `last_name` varchar(15) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(10) DEFAULT NULL,
  `username` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user_id`, `email`, `first_name`, `is_enabled`, `last_name`, `password`, `phone`, `username`) VALUES
(1, 'darshankhairnar72@gmail.com', 'Darshan', b'0', 'Khairnar', '$2a$10$6LdYtncMG5feuGWE6Y6CM.9vB3b0Lzp8mW61TirGXilyW4O91NIii', '9834444657', 'darshan'),
(2, 'sriram@gmail.com', 'Sriram', b'0', 'Peddinti', '$2a$10$nyCp3Pf4L8cNPIsduAftUebQjznTxpkSwG05npJLi4SH/01bl95gq', '1234567891', 'sriram'),
(3, 'rahul@gmail.com', 'Rahul', b'0', 'Pande', '$2a$10$Nh0VCNc4/L0NFHEJt6GybORvZ9tDPvD7hirSNhOHvSDk/PyAqcoNu', '9898747456', 'rahul');

-- --------------------------------------------------------

--
-- Table structure for table `user_roles`
--

CREATE TABLE `user_roles` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user_roles`
--

INSERT INTO `user_roles` (`user_id`, `role_id`) VALUES
(1, 1),
(1, 2),
(2, 2),
(3, 2);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `answers`
--
ALTER TABLE `answers`
  ADD PRIMARY KEY (`answer_id`),
  ADD KEY `FK3erw1a3t0r78st8ty27x6v3g1` (`question_id`),
  ADD KEY `FK5bp3d5loftq2vjn683ephn75a` (`user_id`);

--
-- Indexes for table `questions`
--
ALTER TABLE `questions`
  ADD PRIMARY KEY (`question_id`),
  ADD KEY `FKjoo8hp6d3gfwctr68dl2iaemj` (`user_id`);

--
-- Indexes for table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`role_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`),
  ADD UNIQUE KEY `UKr43af9ap4edm43mmtq01oddj6` (`username`),
  ADD UNIQUE KEY `UK6dotkott2kjsp8vw4d0m25fb7` (`email`);

--
-- Indexes for table `user_roles`
--
ALTER TABLE `user_roles`
  ADD PRIMARY KEY (`user_id`,`role_id`),
  ADD KEY `FKrhfovtciq1l558cw6udg0h0d3` (`role_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `answers`
--
ALTER TABLE `answers`
  MODIFY `answer_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `questions`
--
ALTER TABLE `questions`
  MODIFY `question_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `role`
--
ALTER TABLE `role`
  MODIFY `role_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `user_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `answers`
--
ALTER TABLE `answers`
  ADD CONSTRAINT `FK3erw1a3t0r78st8ty27x6v3g1` FOREIGN KEY (`question_id`) REFERENCES `questions` (`question_id`),
  ADD CONSTRAINT `FK5bp3d5loftq2vjn683ephn75a` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`);

--
-- Constraints for table `questions`
--
ALTER TABLE `questions`
  ADD CONSTRAINT `FKjoo8hp6d3gfwctr68dl2iaemj` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`);

--
-- Constraints for table `user_roles`
--
ALTER TABLE `user_roles`
  ADD CONSTRAINT `FKhfh9dx7w3ubf1co1vdev94g3f` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
  ADD CONSTRAINT `FKrhfovtciq1l558cw6udg0h0d3` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
