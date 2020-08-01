-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               8.0.13 - MySQL Community Server - GPL
-- Server OS:                    Win64
-- HeidiSQL Version:             11.0.0.5919
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for employee
CREATE DATABASE IF NOT EXISTS `employee` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */;
USE `employee`;

-- Dumping structure for table employee.department
CREATE TABLE IF NOT EXISTS `department` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table employee.department: ~0 rows (approximately)
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` (`id`, `name`) VALUES
	(1, 'การเงิน'),
	(2, 'บัญชี'),
	(3, 'คอลเซ็นเตอร์'),
	(4, 'เทเลเซลล์'),
	(5, 'โปรแกรมเมอร์'),
	(6, 'บุคคล');
/*!40000 ALTER TABLE `department` ENABLE KEYS */;

-- Dumping structure for table employee.employee
CREATE TABLE IF NOT EXISTS `employee` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `district` varchar(50) DEFAULT NULL,
  `province` varchar(50) DEFAULT NULL,
  `sub_district` varchar(50) DEFAULT NULL,
  `zipcode` char(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `firstname` varchar(50) NOT NULL,
  `gender` varchar(10) NOT NULL,
  `lastname` varchar(50) NOT NULL,
  `phone_number` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `department_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKbejtwvg9bxus2mffsm3swj3u9` (`department_id`),
  CONSTRAINT `FKbejtwvg9bxus2mffsm3swj3u9` FOREIGN KEY (`department_id`) REFERENCES `department` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table employee.employee: ~0 rows (approximately)
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` (`id`, `address`, `district`, `province`, `sub_district`, `zipcode`, `age`, `firstname`, `gender`, `lastname`, `phone_number`, `department_id`) VALUES
	(1, '1/23 บ้านดอน', 'หนองไผ่', 'กรุงเทพ', 'หนองบอน', '12000', 30, 'ไอติม', 'MALE', 'ไผ่ทอง', '0999999999', 1),
	(2, '9/3', 'เจดีย์', 'เชียงใหม่', 'ข้าวปลอง', '39000', 23, 'น้ำมล', 'FEMALE', 'คนเดิม', '0988888888', 2),
	(3, '22', 'เชียงของ', 'เชียงราย', 'กุนเชียง', '39200', 50, 'คิดคิด', 'MALE', 'ซะระนัง', '0888888888', 3),
	(4, '2/99', 'คลองเตย', 'คลองสอง', 'คลองเดิม', '23444', 42, 'สองคลอง', 'MALE', 'คนละฝั่ง', '0122222222', 4),
	(5, '8/3', 'จตุจักร', 'กรุงเทพ', 'จตุจักร', '12000', 33, 'กับข้าว', 'MALE', 'ครับกับข้าว', '0412222222', 5),
	(6, '33', 'ทองหล่อ', 'กรุงเทพ', 'ทองสวย', '11000', 26, 'ทองหล่อ', 'FEMALE', 'ทองสวย', '0989999545', 6),
	(7, '55', 'สายไหม', 'กรุงเทพ', 'สายซิ', '22220', 53, 'สายไหม', 'FEMALE', 'สายซิ', '0878886566', 3),
	(8, '44/87', 'ปลาทอง', 'กรุงเทพ', 'ปลาคราฟ', '12000', 30, 'ไอติม', 'MALE', 'ไผ่เงิน', '0923334333', 4),
	(9, '66', 'สามเพ่ง', 'ชลบุรี', 'ทางแยก', '43225', 27, 'น้ำใส', 'FEMALE', 'คนเดิม', '0897665566', 2),
	(10, '52', 'ข้างทาง', 'ระยอง', 'เมือง', '33332', 46, 'ข้าวปุ้น', 'FEMALE', 'เสียงแหลม', '0989990099', 5),
	(11, '53', 'สายไหม', 'กรุงเทพ', 'สายซิ', '22220', 53, 'สายไหม', 'FEMALE', 'สายหรือเปล่า', '0989990989', 3);
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
