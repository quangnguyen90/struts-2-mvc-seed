-- phpMyAdmin SQL Dump
-- version 4.2.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Oct 26, 2015 at 12:01 PM
-- Server version: 5.6.21
-- PHP Version: 5.6.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `training`
--

-- --------------------------------------------------------

--
-- Table structure for table `city`
--

CREATE TABLE IF NOT EXISTS `city` (
`cityId` tinyint(4) NOT NULL,
  `cityName` varchar(50) NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `city`
--

INSERT INTO `city` (`cityId`, `cityName`, `status`) VALUES
(1, 'HÀ NỘI', 1),
(2, 'HỒ CHÍ MINH', 1),
(3, 'HẢI PHÒNG', 1),
(4, 'ĐÀ NẴNG', 1),
(5, 'HÀ GIANG', 1),
(6, 'CAO BẰNG', 1),
(7, 'LAI CHÂU', 1),
(8, 'LÀO CAI', 1),
(9, 'TUYÊN QUANG', 1),
(10, 'LẠNG SƠN', 1),
(11, 'BẮC KẠN', 1),
(12, 'THÁI NGUYÊN', 1),
(13, 'YÊN BÁI', 1),
(14, 'SƠN LA', 1),
(15, 'PHÚ THỌ', 1),
(16, 'VĨNH PHÚC', 1),
(17, 'QUẢNG NINH', 1),
(18, 'BẮC GIANG', 1),
(19, 'BẮC NINH', 1),
(20, 'HẢI DƯƠNG', 1),
(21, 'HƯNG YÊN', 1),
(22, 'HOÀ BÌNH', 1),
(23, 'HÀ NAM', 1),
(24, 'NAM ĐỊNH', 1),
(25, 'THÁI BÌNH', 1),
(26, 'NINH BÌNH', 1),
(27, 'THANH HOÁ', 1),
(28, 'NGHỆ AN', 1),
(29, 'HÀ TĨNH', 1),
(30, 'QUẢNG BÌNH', 1),
(31, 'QUẢNG TRỊ', 1),
(32, 'THỪA THIÊN HUẾ', 1),
(33, 'QUẢNG NAM', 1),
(34, 'QUẢNG NGÃI', 1),
(35, 'KONTUM', 1),
(36, 'BÌNH ĐỊNH', 1),
(37, 'GIA LAI', 1),
(38, 'PHÚ YÊN', 1),
(39, 'ĐĂK LĂK', 1),
(40, 'KHÁNH HOÀ', 1),
(41, 'LÂM ĐỒNG', 1),
(42, 'BÌNH PHƯỚC', 1),
(43, 'BÌNH DƯƠNG', 1),
(44, 'NINH THUẬN', 1),
(45, 'TÂY NINH', 1),
(46, 'BÌNH THUẬN', 1),
(47, 'ĐỒNG NAI', 1),
(48, 'LONG AN', 1),
(49, 'ĐỒNG THÁP', 1),
(50, 'AN GIANG', 1),
(51, 'BÀ RỊA-VŨNG TÀU', 1),
(52, 'TIỀN GIANG', 1),
(53, 'KIÊN GIANG', 1),
(54, 'CẦN THƠ', 1),
(55, 'BẾN TRE', 1),
(56, 'VĨNH LONG', 1),
(57, 'TRÀ VINH', 1),
(58, 'SÓC TRĂNG', 1),
(59, 'BẠC LIÊU', 1),
(60, 'CÀ MAU', 1),
(61, 'ĐIỆN BIÊN', 1),
(62, 'DĂK NÔNG', 1),
(63, 'HẬU GIANG', 1);

-- --------------------------------------------------------

--
-- Table structure for table `district`
--

CREATE TABLE IF NOT EXISTS `district` (
  `districtId` smallint(6) NOT NULL,
  `districtName` varchar(25) NOT NULL,
  `cityId` tinyint(4) NOT NULL,
  `status` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `district`
--

INSERT INTO `district` (`districtId`, `districtName`, `cityId`, `status`) VALUES
(1, 'Quận Ba Đình', 1, 1),
(2, 'Quận Hoàn Kiếm', 1, 1),
(3, 'Quận Hai Bà Trưng', 1, 1),
(4, 'Quận Đống Đa', 1, 1),
(5, 'Quận Tây Hồ', 1, 1),
(6, 'Quận Cầu Giấy', 1, 1),
(7, 'Quận Thanh Xuân', 1, 1),
(8, 'Quận Hoàng Mai', 1, 1),
(9, 'Quận Long Biên', 1, 1),
(10, 'Huyện Từ Liêm', 1, 1),
(11, 'Huyện Thanh Trì', 1, 1),
(12, 'Huyện Gia Lâm', 1, 1),
(13, 'Huyện Đông Anh', 1, 1),
(14, 'Huyện Sóc Sơn', 1, 1),
(15, 'Thành phố Hà Đông', 1, 1),
(16, 'Thành phố Sơn Tây', 1, 1),
(17, 'Huyện Ba Vì', 1, 1),
(18, 'Huyện Phúc Thọ', 1, 1),
(19, 'Huyện Thạch Thất', 1, 1),
(20, 'Huyện Quốc Oai', 1, 1),
(21, 'Huyện Chương Mỹ', 1, 1),
(22, 'Huyện Đan Phượng', 1, 1),
(23, 'Huyện Hoài Đức', 1, 1),
(24, 'Huyện Thanh Oai', 1, 1),
(25, 'Huyện Mỹ Đức', 1, 1),
(26, 'Huyện Ứng Hòa', 1, 1),
(27, 'Huyện Thường Tín', 1, 1),
(28, 'Huyện Phú Xuyên', 1, 1),
(29, 'Huyện Mê Linh', 1, 1),
(30, 'Quận Một', 2, 1),
(31, 'Quận Hai', 2, 1),
(32, 'Quận Ba', 2, 1),
(33, 'Quận Bốn', 2, 1),
(34, 'Quận Năm', 2, 1),
(35, 'Quận Sáu', 2, 1),
(36, 'Quận Bảy', 2, 1),
(37, 'Quận Tám', 2, 1),
(38, 'Quận Chín', 2, 1),
(39, 'Quận Mười', 2, 1),
(40, 'Quận Mười một', 2, 1),
(41, 'Quận Mười hai', 2, 1),
(42, 'Quận Gò Vấp', 2, 1),
(43, 'Quận Tân Bình', 2, 1),
(44, 'Quận Tân Phú', 2, 1),
(45, 'Quận Bình Thạnh', 2, 1),
(46, 'Quận Phú Nhuận', 2, 1),
(47, 'Quận Thủ Đức', 2, 1),
(48, 'Quận Bình Tân', 2, 1),
(49, 'Huyện Bình Chánh', 2, 1),
(50, 'Huyện Củ Chi', 2, 1),
(51, 'Huyện Hóc Môn', 2, 1),
(52, 'Huyện Nhà Bè', 2, 1),
(53, 'Huyện Cần Giờ', 2, 1),
(55, 'Quận Hồng Bàng', 3, 1),
(56, 'Quận Lê Chân', 3, 1),
(57, 'Quận Ngô Quyền', 3, 1),
(58, 'Quận Kiến An', 3, 1),
(59, 'Quận Hải An', 3, 1),
(60, 'Quận Đồ Sơn', 3, 1),
(61, 'Huyện An Lão', 3, 1),
(62, 'Huyện Kiến Thụy', 3, 1),
(63, 'Huyện Thủy Nguyên', 3, 1),
(64, 'Huyện An Dương', 3, 1),
(65, 'Huyện Tiên Lãng', 3, 1),
(66, 'Huyện Vĩnh Bảo', 3, 1),
(67, 'Huyện Cát Hải', 3, 1),
(68, 'Huyện Bạch Long Vĩ', 3, 1),
(69, 'Quận Dương Kinh', 3, 1),
(70, 'Quận Hải Châu', 4, 1),
(71, 'Quận Thanh Khê', 4, 1),
(72, 'Quận Sơn Trà', 4, 1),
(73, 'Quận Ngũ Hành Sơn', 4, 1),
(74, 'Quận Liên Chiểu', 4, 1),
(75, 'Huyện Hoà Vang', 4, 1),
(76, 'Quận Cẩm Lệ', 4, 1),
(77, 'Thị xã Hà Giang', 5, 1),
(78, 'Huyện Đồng Văn', 5, 1),
(79, 'Huyện Mèo Vạc', 5, 1),
(80, 'Huyện Yên Minh', 5, 1),
(81, 'Huyện Quản Bạ', 5, 1),
(82, 'Huyện Vị Xuyên', 5, 1),
(83, 'Huyện Bắc Mê', 5, 1),
(84, 'Huyện Hoàng Su Phì', 5, 1),
(85, 'Huyện Xín Mần', 5, 1),
(86, 'Huyện Bắc Quang', 5, 1),
(87, 'Huyện Quang Bình', 5, 1),
(88, 'Thị xã Cao Bằng', 6, 1),
(89, 'Huyện Bảo Lạc', 6, 1),
(90, 'Huyện Thông Nông', 6, 1),
(91, 'Huyện Hà Quảng', 6, 1),
(92, 'Huyện Trà Lĩnh', 6, 1),
(93, 'Huyện Trùng Khánh', 6, 1),
(94, 'Huyện Nguyên Bình', 6, 1),
(95, 'Huyện Hoà An', 6, 1),
(96, 'Huyện Quảng Uyên', 6, 1),
(97, 'Huyện Thạch An', 6, 1),
(98, 'Huyện Hạ Lang', 6, 1),
(99, 'Huyện Bảo Lâm', 6, 1),
(100, 'Huyện Phục Hoà', 6, 1),
(101, 'Thị xã Lai Châu', 7, 1),
(102, 'Huyện Tam Đường', 7, 1),
(103, 'Huyện Phong Thổ', 7, 1),
(104, 'Huyện Sìn Hồ', 7, 1),
(105, 'Huyện Mường Tè', 7, 1),
(106, 'Huyện Than Uyên', 7, 1),
(107, 'Huyện Tân Uyên', 7, 1),
(108, 'Thành phố Lào Cai', 8, 1),
(109, 'Huyện Xi Ma Cai', 8, 1),
(110, 'Huyện Bát Xát', 8, 1),
(111, 'Huyện Bảo Thắng', 8, 1),
(112, 'Huyện Sa Pa', 8, 1),
(113, 'Huyện Văn Bàn', 8, 1),
(114, 'Huyện Bảo Yên', 8, 1),
(115, 'Huyện Bắc Hà', 8, 1),
(116, 'Huyện Mường Khương', 8, 1),
(117, 'Thị xã Tuyên Quang', 9, 1),
(118, 'Huyện Na Hang', 9, 1),
(119, 'Huyện Chiêm Hoá', 9, 1),
(120, 'Huyện Hàm Yên', 9, 1),
(121, 'Huyện Yên Sơn', 9, 1),
(122, 'Huyện Sơn Dương', 9, 1),
(123, 'Thành phố Lạng Sơn', 10, 1),
(124, 'Huyện Văn Lãng', 10, 1),
(125, 'Huyện Bắc Sơn', 10, 1),
(126, 'Huyện Lộc Bình', 10, 1),
(127, 'Huyện Chi Lăng', 10, 1),
(128, 'Huyện Tràng Định', 10, 1),
(129, 'Huyện Bình Gia', 10, 1),
(130, 'Huyện Văn Quan', 10, 1),
(131, 'Huyện Cao Lộc', 10, 1),
(132, 'Huyện Đình Lập', 10, 1),
(133, 'Huyện Hữu Lũng', 10, 1),
(134, 'Thị xã Bắc Kạn', 11, 1),
(135, 'Huyện Chợ Đồn', 11, 1),
(136, 'Huyện Bạch Thông', 11, 1),
(137, 'Huyện Na Rì', 11, 1),
(138, 'Huyện Ngân Sơn', 11, 1),
(139, 'Huyện Ba Bể', 11, 1),
(140, 'Huyện Chợ Mới', 11, 1),
(141, 'Huyện Pác Nặm', 11, 1),
(143, 'TP. Thái Nguyên', 12, 1),
(144, 'Thị xã Sông Công', 12, 1),
(145, 'Huyện Định Hoá', 12, 1),
(146, 'Huyện Phú Lương', 12, 1),
(147, 'Huyện Võ Nhai', 12, 1),
(148, 'Huyện Đại Từ', 12, 1),
(149, 'Huyện Đồng Hỷ', 12, 1),
(150, 'Huyện Phú Bình', 12, 1),
(151, 'Huyện Phổ Yên', 12, 1),
(152, 'Thành phố Yên Bái', 13, 1),
(153, 'Thị xã Nghĩa Lộ', 13, 1),
(154, 'Huyện Văn Yên', 13, 1),
(155, 'Huyện Yên Bình', 13, 1),
(156, 'Huyện Mù Cang Chải', 13, 1),
(157, 'Huyện Văn Chấn', 13, 1),
(158, 'Huyện Trấn Yên', 13, 1),
(159, 'Huyện Trạm Tấu', 13, 1),
(160, 'Huyện Lục Yên', 13, 1),
(161, 'Thị xã Sơn La', 14, 1),
(162, 'Huyện Quỳnh Nhai', 14, 1),
(163, 'Huyện Mường La', 14, 1),
(164, 'Huyện Thuận Châu', 14, 1),
(165, 'Huyện Bắc Yên', 14, 1),
(166, 'Huyện Phù Yên', 14, 1),
(167, 'Huyện Mai Sơn', 14, 1),
(168, 'Huyện Yên Châu', 14, 1),
(169, 'Huyện Sông Mã', 14, 1),
(170, 'Huyện Mộc Châu', 14, 1),
(171, 'Huyện Sốp Cộp', 14, 1),
(172, 'TP. Việt Trì', 15, 1),
(173, 'Thị xã Phú Thọ', 15, 1),
(174, 'Huyện Đoan Hùng', 15, 1),
(175, 'Huyện Thanh Ba', 15, 1),
(176, 'Huyện Hạ Hoà', 15, 1),
(177, 'Huyện Cẩm Khê', 15, 1),
(178, 'Huyện Yên Lập', 15, 1),
(179, 'Huyện Thanh Sơn', 15, 1),
(180, 'Huyện Phù Ninh', 15, 1),
(181, 'Huyện Lâm Thao', 15, 1),
(182, 'Huyện Tam Nông', 15, 1),
(183, 'Huyện Thanh Thủy', 15, 1),
(184, 'Huyện Tân Sơn', 15, 1),
(185, 'Huyện Sông Lô', 16, 1),
(186, 'Thành phố Vĩnh Yên', 16, 1),
(187, 'Huyện Tam Dương', 16, 1),
(188, 'Huyện Lập Thạch', 16, 1),
(189, 'Huyện Vĩnh Tường', 16, 1),
(190, 'Huyện Yên Lạc', 16, 1),
(191, 'Huyện Bình Xuyên', 16, 1),
(192, 'Thị xã Phúc Yên', 16, 1),
(193, 'Huyện Tam Đảo', 16, 1),
(194, 'TP. Hạ Long', 17, 1),
(195, 'Thị xã Cẩm Phả', 17, 1),
(196, 'Thị xã Uông Bí', 17, 1),
(197, 'Thị xã Móng Cái', 17, 1),
(198, 'Huyện Bình Liêu', 17, 1),
(199, 'Huyện Đầm Hà', 17, 1),
(200, 'Huyện Hải Hà', 17, 1),
(201, 'Huyện Tiên Yên', 17, 1),
(202, 'Huyện Ba Chẽ', 17, 1),
(203, 'Huyện Đông Triều', 17, 1),
(204, 'Huyện Yên Hưng', 17, 1),
(205, 'Huyện Hoành Bồ', 17, 1),
(206, 'Huyện Vân Đồn', 17, 1),
(207, 'Huyện Cô Tô', 17, 1),
(208, 'Thành phố Bắc Giang', 18, 1),
(209, 'Huyện Yên Thế', 18, 1),
(210, 'Huyện Lục Ngạn', 18, 1),
(211, 'Huyện Sơn Động', 18, 1),
(212, 'Huyện Lục Nam', 18, 1),
(213, 'Huyện Tân Yên', 18, 1),
(214, 'Huyện Hiệp Hoà', 18, 1),
(215, 'Huyện Lạng Giang', 18, 1),
(216, 'Huyện Việt Yên', 18, 1),
(217, 'Huyện Yên Dũng', 18, 1),
(219, 'Thành phố Bắc Ninh', 19, 1),
(220, 'Huyện Yên Phong', 19, 1),
(221, 'Huyện Quế Võ', 19, 1),
(222, 'Huyện Tiên Du', 19, 1),
(223, 'Huyện Từ Sơn', 19, 1),
(224, 'Huyện Thuận Thành', 19, 1),
(225, 'Huyện Gia Bình', 19, 1),
(226, 'Huyện Lương Tài', 19, 1),
(227, 'Thành phố Hải Dương', 20, 1),
(228, 'Huyện Chí Linh', 20, 1),
(229, 'Huyện Nam Sách', 20, 1),
(230, 'Huyện Kinh Môn', 20, 1),
(231, 'Huyện Gia Lộc', 20, 1),
(232, 'Huyện Tứ Kỳ', 20, 1),
(233, 'Huyện Thanh Miện', 20, 1),
(234, 'Huyện Ninh Giang', 20, 1),
(235, 'Huyện Cẩm Giàng', 20, 1),
(236, 'Huyện Thanh Hà', 20, 1),
(237, 'Huyện Kim Thành', 20, 1),
(238, 'Huyện Bình Giang', 20, 1),
(239, 'Thị xã Hưng Yên', 21, 1),
(240, 'Huyện Kim Động', 21, 1),
(241, 'Huyện Ân Thi', 21, 1),
(242, 'Huyện Khoái Châu', 21, 1),
(243, 'Huyện Yên Mỹ', 21, 1),
(244, 'Huyện Tiên Lữ', 21, 1),
(245, 'Huyện Phù Cừ', 21, 1),
(246, 'Huyện Mỹ Hào', 21, 1),
(247, 'Huyện Văn Lâm', 21, 1),
(248, 'Huyện Văn Giang', 21, 1),
(249, 'Thành phố Hoà Bình', 22, 1),
(250, 'Huyện Đà Bắc', 22, 1),
(251, 'Huyện Mai Châu', 22, 1),
(252, 'Huyện Tân Lạc', 22, 1),
(253, 'Huyện Lạc Sơn', 22, 1),
(254, 'Huyện Kỳ Sơn', 22, 1),
(255, 'Huyện Lương Sơn', 22, 1),
(256, 'Huyện Kim Bôi', 22, 1),
(257, 'Huyện Lạc Thuỷ', 22, 1),
(258, 'Huyện Yên Thuỷ', 22, 1),
(259, 'Huyện Cao Phong', 22, 1),
(260, 'Thành phố Phủ Lý', 23, 1),
(261, 'Huyện Duy Tiên', 23, 1),
(262, 'Huyện Kim Bảng', 23, 1),
(263, 'Huyện Lý Nhân', 23, 1),
(264, 'Huỵện Thanh Liêm', 23, 1),
(265, 'Huyện Bình Lục', 23, 1),
(266, 'TP. Nam Định', 24, 1),
(267, 'Huyện Mỹ Lộc', 24, 1),
(268, 'Huyện Xuân Trường', 24, 1),
(269, 'Huyện Giao Thủy', 24, 1),
(270, 'Huyện ý Yên', 24, 1),
(271, 'Huyện Vụ Bản', 24, 1),
(272, 'Huyện Nam Trực', 24, 1),
(273, 'Huyện Trực Ninh', 24, 1),
(274, 'Huyện Nghĩa Hưng', 24, 1),
(275, 'Huyện Hải Hậu', 24, 1),
(276, 'Thành phố Thái Bình', 25, 1),
(277, 'Huyện Quỳnh Phụ', 25, 1),
(278, 'Huyện Hưng Hà', 25, 1),
(279, 'Huyện Đông Hưng', 25, 1),
(280, 'Huyện Vũ Thư', 25, 1),
(281, 'Huyện Kiến Xương', 25, 1),
(282, 'Huyện Tiền Hải', 25, 1),
(283, 'Huyện Thái Thuỵ', 25, 1),
(284, 'Thành phố Ninh Bình', 26, 1),
(285, 'Thị xã Tam Điệp', 26, 1),
(286, 'Huyện Nho Quan', 26, 1),
(287, 'Huyện Gia Viễn', 26, 1),
(288, 'Huyện Hoa Lư', 26, 1),
(289, 'Huyện Yên Mô', 26, 1),
(290, 'Huyện Kim Sơn', 26, 1),
(291, 'Huyện Yên Khánh', 26, 1),
(292, 'TP.Thanh Hoá', 27, 1),
(293, 'Thị xã Bỉm Sơn', 27, 1),
(294, 'Thị xã Sầm Sơn', 27, 1),
(295, 'Huyện Quan Hoá', 27, 1),
(296, 'Huyện Quan Sơn', 27, 1),
(297, 'Huyện Mường Lát', 27, 1),
(298, 'Huyện Bá Thước', 27, 1),
(299, 'Huyện Thường Xuân', 27, 1),
(300, 'Huyện Như Xuân', 27, 1),
(301, 'Huyện Như Thanh', 27, 1),
(302, 'Huyện Lang Chánh', 27, 1),
(303, 'Huyện Ngọc Lặc', 27, 1),
(304, 'Huyện Thạch Thành', 27, 1),
(305, 'Huyện Cẩm Thủy', 27, 1),
(306, 'Huyện Thọ Xuân', 27, 1),
(307, 'Huyện Vĩnh Lộc', 27, 1),
(308, 'Huyện Thiệu Hoá', 27, 1),
(309, 'Huyện Triệu Sơn', 27, 1),
(310, 'Huyện Nông Cống', 27, 1),
(311, 'Huyện Đông Sơn', 27, 1),
(312, 'Huyện Hà Trung', 27, 1),
(313, 'Huyện Hoằng Hoá', 27, 1),
(314, 'Huyện Nga Sơn', 27, 1),
(315, 'Huyện Hậu Lộc', 27, 1),
(316, 'Huyện Quảng Xương', 27, 1),
(317, 'Huyện Tĩnh Gia', 27, 1),
(318, 'Huyện Yên Định', 27, 1),
(319, 'Thành phố Vinh', 28, 1),
(320, 'Thị xã Cửa Lò', 28, 1),
(321, 'Huyện Quỳ Châu', 28, 1),
(322, 'Huyện Quỳ Hợp', 28, 1),
(323, 'Huyện Nghĩa Đàn', 28, 1),
(324, 'Huyện Quỳnh Lưu', 28, 1),
(325, 'Huyện Kỳ Sơn', 28, 1),
(326, 'Huyện Tương Dương', 28, 1),
(327, 'Huyện Con Cuông', 28, 1),
(328, 'Huyện Tân Kỳ', 28, 1),
(329, 'Huyện Yên Thành', 28, 1),
(330, 'Huyện Diễn Châu', 28, 1),
(331, 'Huyện Anh Sơn', 28, 1),
(332, 'Huyện Đô Lương', 28, 1),
(333, 'Huyện Thanh Chương', 28, 1),
(334, 'Huyện Nghi Lộc', 28, 1),
(335, 'Huyện Nam Đàn', 28, 1),
(336, 'Huyện Hưng Nguyên', 28, 1),
(337, 'Huyện Quế Phong', 28, 1),
(338, 'Thành phố Hà Tĩnh', 29, 1),
(339, 'Thị xã Hồng Lĩnh', 29, 1),
(340, 'Huyện Hương Sơn', 29, 1),
(341, 'Huyện Đức Thọ', 29, 1),
(342, 'Huyện Nghi Xuân', 29, 1),
(343, 'Huyện Can Lộc', 29, 1),
(344, 'Huyện Hương Khê', 29, 1),
(345, 'Huyện Thạch Hà', 29, 1),
(346, 'Huyện Cẩm Xuyên', 29, 1),
(347, 'Huyện Kỳ Anh', 29, 1),
(348, 'Huyện Vũ Quang', 29, 1),
(349, 'Huyện Lộc Hà', 29, 1),
(350, 'Thành phố Đồng Hới', 30, 1),
(351, 'Huyện Tuyên Hoá', 30, 1),
(352, 'Huyện Minh Hoá', 30, 1),
(353, 'Huyện Quảng Trạch', 30, 1),
(354, 'Huyện Bố Trạch', 30, 1),
(355, 'Huyện Quảng Ninh', 30, 1),
(356, 'Huyện Lệ Thuỷ', 30, 1),
(357, 'Thị xã Đông Hà', 31, 1),
(358, 'Thị xã Quảng Trị', 31, 1),
(359, 'Huyện Vĩnh Linh', 31, 1),
(360, 'Huyện Gio Linh', 31, 1),
(361, 'Huyện Cam Lộ', 31, 1),
(362, 'Huyện Triệu Phong', 31, 1),
(363, 'Huyện Hải Lăng', 31, 1),
(364, 'Huyện Hướng Hoá', 31, 1),
(365, 'Huyện Đăk Rông', 31, 1),
(366, 'Huyện đảo Cồn cỏ', 31, 1),
(367, 'Thành phố Huế', 32, 1),
(368, 'Huyện Phong Điền', 32, 1),
(369, 'Huyện Hương Trà', 32, 1),
(370, 'Huyện Phú Vang', 32, 1),
(371, 'Huyện Hương Thuỷ', 32, 1),
(372, 'Huyện Nam Đông', 32, 1),
(373, 'Huyện A Lưới', 32, 1),
(375, 'Thành phố Tam Kỳ', 33, 1),
(376, 'Thị xã Hội An', 33, 1),
(377, 'Huyện Duy Xuyên', 33, 1),
(378, 'Huyện Điện Bàn', 33, 1),
(379, 'Huyện Đại Lộc', 33, 1),
(380, 'Huyện Quế Sơn', 33, 1),
(381, 'Huyện Hiệp Đức', 33, 1),
(382, 'Huyện Thăng Bình', 33, 1),
(383, 'Huyện Núi Thành', 33, 1),
(384, 'Huyện Tiên Phước', 33, 1),
(385, 'Huyện Bắc Trà My', 33, 1),
(386, 'Huyện Đông Giang', 33, 1),
(387, 'Huyện Nam Giang', 33, 1),
(388, 'Huyện Phước Sơn', 33, 1),
(389, 'Huyện Nam Trà My', 33, 1),
(390, 'Huyện Tây Giang', 33, 1),
(391, 'Huyện Phú Ninh', 33, 1),
(392, 'Huyện Nông Sơn', 33, 1),
(393, 'TP.Quảng Ngãi', 34, 1),
(394, 'Huyện Lý Sơn', 34, 1),
(395, 'Huyện Bình Sơn', 34, 1),
(396, 'Huyện Trà Bồng', 34, 1),
(397, 'Huyện Sơn Tịnh', 34, 1),
(398, 'Huyện Sơn Hà', 34, 1),
(399, 'Huyện Tư Nghĩa', 34, 1),
(400, 'Huyện Nghĩa Hành', 34, 1),
(401, 'Huyện Minh Long', 34, 1),
(402, 'Huyện Mộ Đức', 34, 1),
(403, 'Huyện Đức Phổ', 34, 1),
(404, 'Huyện Ba Tơ', 34, 1),
(405, 'Huyện Sơn Tây', 34, 1),
(406, 'Huyện Tây Trà', 34, 1),
(407, 'Thị xã KonTum', 35, 1),
(408, 'Huyện Đăk Glei', 35, 1),
(409, 'Huyện Ngọc Hồi', 35, 1),
(410, 'Huyện Đăk Tô', 35, 1),
(411, 'Huyện Sa Thầy', 35, 1),
(412, 'Huyện Kon Plong', 35, 1),
(413, 'Huyện Đăk Hà', 35, 1),
(414, 'Huyện Kon Rộy', 35, 1),
(415, 'Huyện Tu Mơ Rông', 35, 1),
(416, 'Thành phố Quy Nhơn', 36, 1),
(417, 'Huyện An Lão', 36, 1),
(418, 'Huyện Hoài Ân', 36, 1),
(419, 'Huyện Hoài Nhơn', 36, 1),
(420, 'Huyện Phù Mỹ', 36, 1),
(421, 'Huyện Phù Cát', 36, 1),
(422, 'Huyện Vĩnh Thạnh', 36, 1),
(423, 'Huyện Tây Sơn', 36, 1),
(424, 'Huyện Vân Canh', 36, 1),
(425, 'Huyện An Nhơn', 36, 1),
(426, 'Huyện Tuy Phước', 36, 1),
(427, 'Thành phố Pleiku', 37, 1),
(428, 'Huyện Chư Păh', 37, 1),
(429, 'Huyện Mang Yang', 37, 1),
(430, 'Huyện Kbang', 37, 1),
(431, 'Thị xã An Khê', 37, 1),
(432, 'Huyện Kông Chro', 37, 1),
(433, 'Huyện Đức Cơ', 37, 1),
(434, 'Huyện Chưprông', 37, 1),
(435, 'Huyện Chư Sê', 37, 1),
(436, 'Huyện Ayunpa', 37, 1),
(437, 'Huyện Krông Pa', 37, 1),
(438, 'Huyện Ia Grai', 37, 1),
(439, 'Huyện Đăk Đoa', 37, 1),
(440, 'Huyện Ia Pa', 37, 1),
(441, 'Huyện Đăk Pơ', 37, 1),
(442, 'Huyện Phú Thiện', 37, 1),
(443, 'Thị xã Tuy Hoà', 38, 1),
(444, 'Huyện Đồng Xuân', 38, 1),
(445, 'Huyện Sông Cầu', 38, 1),
(446, 'Huyện Tuy An', 38, 1),
(447, 'Huyện Sơn Hoà', 38, 1),
(448, 'Huyện Sông Hinh', 38, 1),
(449, 'Huyện Đông Hoà', 38, 1),
(450, 'Huyện Phú Hoà', 38, 1),
(451, 'Huyện Tây Hoà', 38, 1),
(452, 'TP.Buôn Ma Thuột', 39, 1),
(453, 'Huyện Ea H Leo', 39, 1),
(454, 'Huyện Krông Buk', 39, 1),
(455, 'Huyện Krông Năng', 39, 1),
(456, 'Huyện Ea Súp', 39, 1),
(457, 'Huyện Cư M gar', 39, 1),
(458, 'Huyện Krông Pắc', 39, 1),
(459, 'Huyện Ea Kar', 39, 1),
(460, 'Huyện M''Đrăk', 39, 1),
(461, 'Huyện Krông Ana', 39, 1),
(462, 'Huyện Krông Bông', 39, 1),
(463, 'Huyện Lăk', 39, 1),
(464, 'Huyện Buôn Đôn', 39, 1),
(465, 'Huyện Cư Kuin', 39, 1),
(466, 'Thành phố Nha Trang', 40, 1),
(467, 'Huyện Vạn Ninh', 40, 1),
(468, 'Huyện Ninh Hoà', 40, 1),
(469, 'Huyện Diên Khánh', 40, 1),
(470, 'Huyện Khánh Vĩnh', 40, 1),
(471, 'Thị xã Cam Ranh', 40, 1),
(472, 'Huyện Khánh Sơn', 40, 1),
(473, 'Huyện Trường Sa', 40, 1),
(474, 'Huyện Cam Lâm', 40, 1),
(475, 'Thành phố Đà Lạt', 41, 1),
(476, 'Thị xã. Bảo Lộc', 41, 1),
(477, 'Huyện Đức Trọng', 41, 1),
(478, 'Huyện Di Linh', 41, 1),
(479, 'Huyện Đơn Dương', 41, 1),
(480, 'Huyện Lạc Dương', 41, 1),
(481, 'Huyện Đạ Huoai', 41, 1),
(482, 'Huyện Đạ Tẻh', 41, 1),
(483, 'Huyện Cát Tiên', 41, 1),
(484, 'Huyện Lâm Hà', 41, 1),
(485, 'Huyện Bảo Lâm', 41, 1),
(486, 'Huyện Đam Rông', 41, 1),
(487, 'Thị xã Đồng Xoài', 42, 1),
(488, 'Huyện Đồng Phú', 42, 1),
(489, 'Huyện Chơn Thành', 42, 1),
(490, 'Huyện Bình Long', 42, 1),
(491, 'Huyện Lộc Ninh', 42, 1),
(492, 'Huyện Bù Đốp', 42, 1),
(493, 'Huyện Phước Long', 42, 1),
(494, 'Huyện Bù Đăng', 42, 1),
(495, 'Thị xã Thủ Dầu Một', 43, 1),
(496, 'Huyện Bến Cát', 43, 1),
(497, 'Huyện Tân Uyên', 43, 1),
(498, 'Huyện Thuận An', 43, 1),
(499, 'Huyện Dĩ An', 43, 1),
(500, 'Huyện Phú Giáo', 43, 1),
(501, 'Huyện Dầu Tiếng', 43, 1),
(502, 'TP.Phan Rang - Tháp Chàm', 44, 1),
(503, 'Huyện Ninh Sơn', 44, 1),
(504, 'Huyện Ninh Hải', 44, 1),
(505, 'Huyện Ninh Phước', 44, 1),
(506, 'Huyện Bác ái', 44, 1),
(507, 'Huyện Thuận Bắc', 44, 1),
(508, 'Thị xã Tây Ninh', 45, 1),
(509, 'Huyện Tân Biên', 45, 1),
(510, 'Huyện Tân Châu', 45, 1),
(511, 'Huyện Dương Minh Châu', 45, 1),
(512, 'Huyện Châu Thành', 45, 1),
(513, 'Huyện Hoà Thành', 45, 1),
(514, 'Huyện Bến Cầu', 45, 1),
(515, 'Huyện Gò Dầu', 45, 1),
(516, 'Huyện Trảng Bàng', 45, 1),
(517, 'Thành phố Phan Thiết', 46, 1),
(518, 'Huyện Tuy Phong', 46, 1),
(519, 'Huyện Bắc Bình', 46, 1),
(520, 'Huyện Hàm Thuận Bắc', 46, 1),
(521, 'Huyện Hàm Thuận Nam', 46, 1),
(522, 'Huyện Hàm Tân', 46, 1),
(523, 'Huyện Đức Linh', 46, 1),
(524, 'Huyện Tánh Linh', 46, 1),
(525, 'Huyện đảo Phú Quý', 46, 1),
(526, 'Thị xã LaGi', 46, 1),
(527, 'Thành phố Biên Hoà', 47, 1),
(528, 'Huyện Vĩnh Cửu', 47, 1),
(529, 'Huyện Tân Phú', 47, 1),
(530, 'Huyện Định Quán', 47, 1),
(531, 'Huyện Thống Nhất', 47, 1),
(532, 'Thị xã Long Khánh', 47, 1),
(533, 'Huyện Xuân Lộc', 47, 1),
(534, 'Huyện Long Thành', 47, 1),
(535, 'Huyện Nhơn Trạch', 47, 1),
(536, 'Huyện Trảng Bom', 47, 1),
(537, 'Huyện Cẩm Mỹ', 47, 1),
(538, 'Thị xã Tân An', 48, 1),
(539, 'Huyện Vĩnh Hưng', 48, 1),
(540, 'Huyện Mộc Hoá', 48, 1),
(541, 'Huyện Tân Thạnh', 48, 1),
(542, 'Huyện Thạnh Hoá', 48, 1),
(543, 'Huyện Đức Huệ', 48, 1),
(544, 'Huyện Đức Hoà', 48, 1),
(545, 'Huyện Bến Lức', 48, 1),
(546, 'Huyện Thủ Thừa', 48, 1),
(547, 'Huyện Châu Thành', 48, 1),
(548, 'Huyện Tân Trụ', 48, 1),
(549, 'Huyện Cần Đước', 48, 1),
(550, 'Huyện Cần Giuộc', 48, 1),
(551, 'Huyện Tân Hưng', 48, 1),
(552, 'Thành phố Cao Lãnh', 49, 1),
(553, 'Thị xã Sa Đéc', 49, 1),
(554, 'Huyện Tân Hồng', 49, 1),
(555, 'Huyện Hồng Ngự', 49, 1),
(556, 'Huyện Tam Nông', 49, 1),
(557, 'Huyện Thanh Bình', 49, 1),
(558, 'Huyện Cao Lãnh', 49, 1),
(559, 'Huyện Lấp Vò', 49, 1),
(560, 'Huyện Tháp Mười', 49, 1),
(561, 'Huyện Lai Vung', 49, 1),
(562, 'Huyện Châu Thành', 49, 1),
(563, 'TP.Long Xuyên', 50, 1),
(564, 'Thị xã Châu Đốc', 50, 1),
(565, 'Huyện An Phú', 50, 1),
(566, 'Huyện Tân Châu', 50, 1),
(567, 'Huyện Phú Tân', 50, 1),
(568, 'Huyện Tịnh Biên', 50, 1),
(569, 'Huyện Tri Tôn', 50, 1),
(570, 'Huyện Châu Phú', 50, 1),
(571, 'Huyện Chợ Mới', 50, 1),
(572, 'Huyện Châu Thành', 50, 1),
(573, 'Huyện Thoại Sơn', 50, 1),
(574, 'Thành phố Vũng Tàu', 51, 1),
(575, 'Thị xã Bà Rịa', 51, 1),
(576, 'Huyện Xuyên Mộc', 51, 1),
(577, 'Huyện Long Điền', 51, 1),
(578, 'Huyện Côn Đảo', 51, 1),
(579, 'Huyện Tân Thành', 51, 1),
(580, 'Huyện Châu Đức', 51, 1),
(581, 'Huyện Đất Đỏ', 51, 1),
(582, 'Thành phố Mỹ Tho', 52, 1),
(583, 'Thị xã Gò Công', 52, 1),
(584, 'Huyện Cái Bè', 52, 1),
(585, 'Huyện Cai Lậy', 52, 1),
(586, 'Huyện Châu Thành', 52, 1),
(587, 'Huyện Chợ Gạo', 52, 1),
(588, 'Huyện Gò Công Tây', 52, 1),
(589, 'Huyện Gò Công Đông', 52, 1),
(590, 'Huyện Tân Phước', 52, 1),
(591, 'Huyện Tân Phú Đông', 52, 1),
(592, 'Thành phố Rạch Giá', 53, 1),
(593, 'Thị xã Hà Tiên', 53, 1),
(594, 'Huyện Kiên Lương', 53, 1),
(595, 'Huyện Hòn Đất', 53, 1),
(596, 'Huyện Tân Hiệp', 53, 1),
(597, 'Huyện Châu Thành', 53, 1),
(598, 'Huyện Giồng Riềng', 53, 1),
(599, 'Huyện Gò Quao', 53, 1),
(600, 'Huyện An Biên', 53, 1),
(601, 'Huyện An Minh', 53, 1),
(602, 'Huyện Vĩnh Thuận', 53, 1),
(603, 'Huyện Phú Quốc', 53, 1),
(604, 'Huyện Kiên Hải', 53, 1),
(605, 'Huyện U minh Thượng', 53, 1),
(606, 'Quận Ninh Kiều', 54, 1),
(607, 'Quận Bình Thuỷ', 54, 1),
(608, 'Quận Cái Răng', 54, 1),
(609, 'Quận Ô Môn', 54, 1),
(610, 'Huyện Phong Điền', 54, 1),
(611, 'Huyện Cờ Đỏ', 54, 1),
(612, 'Huyện Vĩnh Thạnh', 54, 1),
(613, 'Huỵện Thốt Nốt', 54, 1),
(614, 'Thị xã Bến Tre', 55, 1),
(615, 'Huyện Châu Thành', 55, 1),
(616, 'Huyện Chợ Lách', 55, 1),
(617, 'Huyện Mỏ Cày', 55, 1),
(618, 'Huyện Giồng Trôm', 55, 1),
(619, 'Huyện Bình Đại', 55, 1),
(620, 'Huyện Ba Tri', 55, 1),
(621, 'Huyện Thạnh Phú', 55, 1),
(622, 'Thị xã Vĩnh Long', 56, 1),
(623, 'Huyện Long Hồ', 56, 1),
(624, 'Huyện Mang Thít', 56, 1),
(625, 'Huyện Bình Minh', 56, 1),
(626, 'Huyện Tam Bình', 56, 1),
(627, 'Huyện Trà Ôn', 56, 1),
(628, 'Huyện Vũng Liêm', 56, 1),
(629, 'Huyện Bình Tân', 56, 1),
(630, 'Thị xã Trà Vinh', 57, 1),
(631, 'Huyện Càng Long', 57, 1),
(632, 'Huyện Cầu Kè', 57, 1),
(633, 'Huyện Tiểu Cần', 57, 1),
(634, 'Huyện Châu Thành', 57, 1),
(635, 'Huyện Trà Cú', 57, 1),
(636, 'Huyện Cầu Ngang', 57, 1),
(637, 'Huyện Duyên Hải', 57, 1),
(638, 'Thành phố Sóc Trăng', 58, 1),
(639, 'Huyện Kế Sách', 58, 1),
(640, 'Huyện Mỹ Tú', 58, 1),
(641, 'Huyện Mỹ Xuyên', 58, 1),
(642, 'Huyện Thạnh Trị', 58, 1),
(643, 'Huyện Long Phú', 58, 1),
(644, 'Huyện Vĩnh Châu', 58, 1),
(645, 'Huyện Cù Lao Dung', 58, 1),
(646, 'Huyện Ngã Năm', 58, 1),
(647, 'Huyện Châu Thành', 58, 1),
(648, 'Thị xã Bạc Liêu', 59, 1),
(649, 'Huyện Vĩnh Lợi', 59, 1),
(650, 'Huyện Hồng Dân', 59, 1),
(651, 'Huyện Giá Rai', 59, 1),
(652, 'Huyện Phước Long', 59, 1),
(653, 'Huyện Đông Hải', 59, 1),
(654, 'Huyện Hoà Bình', 59, 1),
(655, 'Thành phố Cà Mau', 60, 1),
(656, 'Huyện Thới Bình', 60, 1),
(657, 'Huyện U Minh', 60, 1),
(658, 'Huyện Trần Văn Thời', 60, 1),
(659, 'Huyện Cái Nước', 60, 1),
(660, 'Huyện Đầm Dơi', 60, 1),
(661, 'Huyện Ngọc Hiển', 60, 1),
(662, 'Huyện Năm Căn', 60, 1),
(663, 'Huyện Phú Tân', 60, 1),
(664, 'TP. Điện Biên Phủ', 61, 1),
(665, 'Thị xã Mường Lay', 61, 1),
(666, 'Huyện Điện Biên', 61, 1),
(667, 'Huyện Tuần Giáo', 61, 1),
(668, 'Huyện Mường Chà', 61, 1),
(669, 'Huyện Tủa Chùa', 61, 1),
(670, 'Huyện Điện Biên Đông', 61, 1),
(671, 'Huyện Mường Nhé', 61, 1),
(672, 'Huyện Mường Ảng', 61, 1),
(673, 'Thị xã Gia Nghĩa', 62, 1),
(674, 'Huyện Dăk RLấp', 62, 1),
(675, 'Huyện Dăk Mil', 62, 1),
(676, 'Huyện Cư Jút', 62, 1),
(677, 'Huyện Dăk Song', 62, 1),
(678, 'Huyện Krông Nô', 62, 1),
(679, 'Huyện Dăk GLong', 62, 1),
(680, 'Huyện Tuy Đức', 62, 1),
(681, 'Thị xã Vị Thanh', 63, 1),
(682, 'Huyện Vị Thuỷ', 63, 1),
(683, 'Huyện Long Mỹ', 63, 1),
(684, 'Huyện Phụng Hiệp', 63, 1),
(685, 'Huyện Châu Thành', 63, 1),
(686, 'Huyện Châu Thành A', 63, 1),
(687, 'Thị xã Ngã Bảy', 63, 1);

-- --------------------------------------------------------

--
-- Table structure for table `emailconfig`
--

CREATE TABLE IF NOT EXISTS `emailconfig` (
  `ID` int(11) NOT NULL,
  `From` varchar(50) NOT NULL,
  `Password` varchar(50) NOT NULL,
  `Subject` varchar(100) NOT NULL,
  `Body` text NOT NULL,
  `SMTP_Host` varchar(50) NOT NULL,
  `SMTP_Port` varchar(50) NOT NULL,
  `SMTP_Auth` varchar(5) NOT NULL,
  `SMTP_Socket_Port` varchar(5) DEFAULT NULL,
  `SMTP_Socket_Class` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `emailconfig`
--

INSERT INTO `emailconfig` (`ID`, `From`, `Password`, `Subject`, `Body`, `SMTP_Host`, `SMTP_Port`, `SMTP_Auth`, `SMTP_Socket_Port`, `SMTP_Socket_Class`) VALUES
(1, 'nguyenphuquang90@gmail.com', 'Admin-123', 'CungDeal chào mừng bạn đã sử dụng dịch vụ của chúng tôi!', 'Welcome to Struts 2 MVC Seed (1)', 'smtp.gmail.com', '465', 'true', '465', 'javax.net.ssl.SSLSocketFactory'),
(2, 'nguyenphuquang90@gmail.com', 'Admin-123', 'Thông tin đặt deal thành công!', 'Welcome to Struts 2 MVC Seed (2)', 'smtp.gmail.com', '465', 'true', '465', 'javax.net.ssl.SSLSocketFactory'),
(3, 'nguyenphuquang90@gmail.com', 'Admin-123', 'Retreive password', 'Demo retreive password', 'smtp.gmail.com', '465', 'true', '465', 'javax.net.ssl.SSLSocketFactory'),
(4, 'nguyenphuquang90@gmail.com', 'not-available', 'Send mail to Android', 'Demo send to mail mobile app', 'smtp.gmail.com', '465', 'true', '465', 'javax.net.ssl.SSLSocketFactory');

-- --------------------------------------------------------

--
-- Table structure for table `news`
--

CREATE TABLE IF NOT EXISTS `news` (
`id` int(11) NOT NULL,
  `title` varchar(250) COLLATE utf8_unicode_ci NOT NULL,
  `brief` text COLLATE utf8_unicode_ci NOT NULL,
  `content` text COLLATE utf8_unicode_ci NOT NULL,
  `created` date NOT NULL,
  `updated` date NOT NULL,
  `status` tinyint(1) NOT NULL,
  `avatar` text COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `news`
--

INSERT INTO `news` (`id`, `title`, `brief`, `content`, `created`, `updated`, `status`, `avatar`) VALUES
(2, 'bài 1', 'qwerty', 'qwerty', '2015-10-18', '2015-10-18', 1, '/assets/img/news-img/bai_1.jpg'),
(9, 'Bai 3', 'qwerty', 'Something need update', '2015-10-19', '2015-10-19', 1, '/assets/img/news-img/Bai 3.jpeg'),
(10, 'bai 4', 'fasdfasd f d asdf asdf asdf ', 'asd fasdf asdf asdf asdf ads', '2015-10-19', '2015-10-19', 1, '/assets/img/news-img/scV50.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `username` varchar(50) NOT NULL,
  `password` varchar(45) NOT NULL,
  `fullname` text NOT NULL,
  `address` text NOT NULL,
  `email` varchar(100) NOT NULL,
  `tel` varchar(45) NOT NULL,
  `status` tinyint(1) NOT NULL,
  `userType` int(11) NOT NULL,
  `cityId` int(11) NOT NULL,
  `districtId` int(11) NOT NULL,
  `temporaryPassword` varchar(100) DEFAULT NULL,
  `gender` int(11) NOT NULL,
  `dob` date DEFAULT NULL,
  `avatar` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`username`, `password`, `fullname`, `address`, `email`, `tel`, `status`, `userType`, `cityId`, `districtId`, `temporaryPassword`, `gender`, `dob`, `avatar`) VALUES
('admins', 'e10adc3949ba59abbe56e057f20f883e', 'Master Admin', '2/3 Street', 'nguyenphuquang90@gmail.com', '0986240402', 1, 1, 7, 104, '', 1, '1990-10-01', '/assets/img/users-img//admins.jpeg'),
('phuquang', 'e10adc3949ba59abbe56e057f20f883e', 'phuquang', '1234567890', 'phuquang@gmail.com', '1234567890', 0, 2, 1, 5, NULL, 1, '2015-10-07', '/assets/img/users-img/phuquang.jpg'),
('quangnguyen', 'e10adc3949ba59abbe56e057f20f883e', 'quangnguyen', '1234567890', 'quangnguyen@gmail.com', '1234567890', 1, 2, 2, 36, NULL, 1, '2015-10-14', '/assets/img/users-img//quangnguyen.jpeg'),
('quangnguyen2', 'e10adc3949ba59abbe56e057f20f883e', 'nguyen phu quang 2', '1234567890', 'nguyenphuquang2@gmail.com', '0123456789', 1, 2, 4, 73, NULL, 1, '2015-10-08', '/assets/img/users-img/quangnguyen2.jpeg');

-- --------------------------------------------------------

--
-- Table structure for table `usertype`
--

CREATE TABLE IF NOT EXISTS `usertype` (
`id` int(11) NOT NULL,
  `userType` varchar(20) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `usertype`
--

INSERT INTO `usertype` (`id`, `userType`) VALUES
(1, 'Admin'),
(2, 'Client');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `city`
--
ALTER TABLE `city`
 ADD PRIMARY KEY (`cityId`);

--
-- Indexes for table `district`
--
ALTER TABLE `district`
 ADD PRIMARY KEY (`districtId`), ADD KEY `City ID` (`cityId`);

--
-- Indexes for table `emailconfig`
--
ALTER TABLE `emailconfig`
 ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `news`
--
ALTER TABLE `news`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
 ADD PRIMARY KEY (`username`);

--
-- Indexes for table `usertype`
--
ALTER TABLE `usertype`
 ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `city`
--
ALTER TABLE `city`
MODIFY `cityId` tinyint(4) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=64;
--
-- AUTO_INCREMENT for table `news`
--
ALTER TABLE `news`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT for table `usertype`
--
ALTER TABLE `usertype`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `district`
--
ALTER TABLE `district`
ADD CONSTRAINT `City ID` FOREIGN KEY (`cityId`) REFERENCES `city` (`cityId`);

DELIMITER $$
--
-- Events
--
CREATE DEFINER=`root`@`localhost` EVENT `Analyze_event` ON SCHEDULE EVERY '1 0' DAY_HOUR STARTS '2013-05-08 11:01:10' ON COMPLETION NOT PRESERVE ENABLE DO call SP_ANALYZE_DEAL()$$

CREATE DEFINER=`root`@`localhost` EVENT `Update_Deal_Status_event` ON SCHEDULE EVERY 1 SECOND STARTS '2013-05-06 13:26:13' ON COMPLETION NOT PRESERVE ENABLE DO call SP_UPDATE_DEAL_STATUS()$$

DELIMITER ;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
