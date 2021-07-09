-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Anamakine: localhost
-- Üretim Zamanı: 09 Tem 2021, 19:31:48
-- Sunucu sürümü: 8.0.18
-- PHP Sürümü: 7.3.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Veritabanı: `otomasyon`
--

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `members`
--

CREATE TABLE `members` (
  `memberID` int(11) NOT NULL,
  `user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_turkish_ci NOT NULL,
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_turkish_ci NOT NULL,
  `authority` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;

--
-- Tablo döküm verisi `members`
--

INSERT INTO `members` (`memberID`, `user_name`, `password`, `authority`) VALUES
(1, 'furkan', '202cb962ac59075b964b07152d234b70', 1),
(2, 'ahmet', '6512bd43d9caa6e02c990b0a82652dca', 1),
(3, 'mehmet', 'b4b147bc522828731f1a016bfa72c073', 1);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `sales`
--

CREATE TABLE `sales` (
  `salesID` int(11) NOT NULL,
  `user` varchar(50) CHARACTER SET utf8 COLLATE utf8_turkish_ci NOT NULL,
  `type` int(11) NOT NULL,
  `about_selling` varchar(200) CHARACTER SET utf8 COLLATE utf8_turkish_ci NOT NULL,
  `about_price` double NOT NULL,
  `about_date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;

--
-- Tablo döküm verisi `sales`
--

INSERT INTO `sales` (`salesID`, `user`, `type`, `about_selling`, `about_price`, `about_date`) VALUES
(2, 'furkan', 3, 'taksitli', 22, '2021-05-12'),
(3, 'ahmet', 3, 'peşin', 35, '2021-05-13'),
(4, 'ahmet', 3, 'peşin', 35, '2021-05-13'),
(5, 'ahmet', 3, 'peşin', 35, '2021-05-13'),
(6, 'ahmet', 2, 'taksitli', 30, '2021-05-14');

--
-- Dökümü yapılmış tablolar için indeksler
--

--
-- Tablo için indeksler `members`
--
ALTER TABLE `members`
  ADD PRIMARY KEY (`memberID`);

--
-- Tablo için indeksler `sales`
--
ALTER TABLE `sales`
  ADD PRIMARY KEY (`salesID`);

--
-- Dökümü yapılmış tablolar için AUTO_INCREMENT değeri
--

--
-- Tablo için AUTO_INCREMENT değeri `members`
--
ALTER TABLE `members`
  MODIFY `memberID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- Tablo için AUTO_INCREMENT değeri `sales`
--
ALTER TABLE `sales`
  MODIFY `salesID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=68;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
