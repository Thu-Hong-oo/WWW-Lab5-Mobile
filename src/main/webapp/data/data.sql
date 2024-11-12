-- --------------------------------------------------------
-- Máy chủ:                      127.0.0.1
-- Server version:               11.3.2-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Phiên bản:           12.6.0.6765
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for mobiles
CREATE DATABASE IF NOT EXISTS `mobiles` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */;
USE `mobiles`;

-- Dumping structure for table mobiles.dienthoai
CREATE TABLE IF NOT EXISTS `dienthoai` (
  `maDienThoai` int(11) NOT NULL AUTO_INCREMENT,
  `maNCC` int(11) DEFAULT NULL,
  `namSanXuat` int(11) NOT NULL,
  `cauHinh` varchar(255) NOT NULL,
  `hinhAnh` varchar(255) DEFAULT NULL,
  `tenDienThoai` varchar(255) NOT NULL,
  PRIMARY KEY (`maDienThoai`),
  KEY `FKkn01e6hu1q7vthdyr8w3565rm` (`maNCC`),
  CONSTRAINT `FKkn01e6hu1q7vthdyr8w3565rm` FOREIGN KEY (`maNCC`) REFERENCES `nhacungcap` (`maNCC`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table mobiles.dienthoai: ~8 rows (approximately)
INSERT INTO `dienthoai` (`maDienThoai`, `maNCC`, `namSanXuat`, `cauHinh`, `hinhAnh`, `tenDienThoai`) VALUES
	(1, 2, 2022, '6GB RAM, 128GB Storage', '/dataImgs/xiaomi-redmi-a3-xanh-lá-thumb-600x600.jpg', 'Xiaomi 13'),
	(2, 2, 2000, '8GB RAM, 256GB Storage', '/dataImgs/vivo-y100-xanh-thumb-1-600x600.jpg', 'Xiaomi 12'),
	(3, 3, 2021, '8GB RAM, 128GB Storage', '/dataImgs/xiaomi-redmi-14c-blue-1-600x600.jpg', 'Samsung Galaxy S21'),
	(4, 4, 2021, '12GB RAM, 256GB Storage', '/dataImgs/vivo-y100-xanh-thumb-1-600x600.jpg', 'Oppo Reno 6'),
	(5, 4, 2021, '8GB RAM, 128GB Storage', '/dataImgs/xiaomi-redmi-14c-blue-1-600x600.jpg', 'Vivo V21'),
	(6, 1, 2024, 'Ram8G', '/dataImgs/xiaomi-redmi-a3-xanh-lá-thumb-600x600.jpg', 'Apple 16Test'),
	(7, 2, 2020, 'Ram 4G', '/dataImgs/iPhone-14-thumb-tim-1-600x600.jpg', 'Apple 16Test'),
	(8, 2, 2020, 'Ram12G', '/dataImgs/xiaomi-redmi-a3-xanh-lá-thumb-600x600.jpg', 'Apple 1444');

-- Dumping structure for table mobiles.nhacungcap
CREATE TABLE IF NOT EXISTS `nhacungcap` (
  `maNCC` int(11) NOT NULL AUTO_INCREMENT,
  `diaChi` varchar(255) DEFAULT NULL,
  `soDienThoai` varchar(255) DEFAULT NULL,
  `tenNCC` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`maNCC`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table mobiles.nhacungcap: ~4 rows (approximately)
INSERT INTO `nhacungcap` (`maNCC`, `diaChi`, `soDienThoai`, `tenNCC`) VALUES
	(1, 'Newyork', '345678902', 'apple'),
	(2, 'China', '345678902', 'Xiaomi'),
	(3, 'Korean', '345678902', 'SamSung'),
	(4, 'China', '345678902', 'Oppo');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
