SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

CREATE SCHEMA IF NOT EXISTS `fogcarport_TEST` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_danish_ci;
CREATE SCHEMA IF NOT EXISTS `fogcarport` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_danish_ci;
USE `fogcarport`;

DROP TABLE IF EXISTS `cases`;
DROP TABLE IF EXISTS `orders`;
DROP TABLE IF EXISTS `bills_of_materials`;
DROP TABLE IF EXISTS `carports`;
DROP TABLE IF EXISTS `roof_types`;
DROP TABLE IF EXISTS `employees`;
DROP TABLE IF EXISTS `customers`;
DROP TABLE IF EXISTS `components`;


-- -----------------------------------------------------
-- Table `fogcarport`.`components`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fogcarport`.`components` (
  `component_id` INT(11) NOT NULL AUTO_INCREMENT,
  `description` VARCHAR(45) NOT NULL,				#Name
  `help_text` VARCHAR(500) NULL DEFAULT NULL,		#Description
  `length` INT(11) UNSIGNED NOT NULL,				#all in mm
  `width` INT(11) UNSIGNED NOT NULL,
  `height` INT(11) UNSIGNED NOT NULL,
  `price` FLOAT UNSIGNED NOT NULL,
  PRIMARY KEY (`component_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fogcarport`.`customers`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fogcarport`.`customers` (
  `customer_id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) DEFAULT NULL,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `phone_number` VARCHAR(45) DEFAULT NULL,
  PRIMARY KEY (`customer_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fogcarport`.`employees`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fogcarport`.`employees` (
  `employee_id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) DEFAULT NULL,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `phone_number` VARCHAR(45) DEFAULT NULL,
  `rank` ENUM('storeworker', 'salesperson', 'admin', 'superadmin') NOT NULL,
  PRIMARY KEY (`employee_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fogcarport`.`roof_types`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fogcarport`.`roof_types` (
  `roof_type_id` INT(11) NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(45) NOT NULL,
  `color` VARCHAR(45) NOT NULL,
  `slant` INT(11) UNSIGNED NOT NULL,
  `version` VARCHAR(10) DEFAULT NULL,
  PRIMARY KEY (`roof_type_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fogcarport`.`carports`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fogcarport`.`carports` (			#all in mm
  `order_id` INT(11) NOT NULL,
  `roof_type_id` INT(11) NOT NULL,
  `length` INT(11) UNSIGNED NOT NULL,
  `width` INT(11) UNSIGNED NOT NULL,
  `height` int(11) UNSIGNED NOT NULL,
  `shed_length` INT(11) UNSIGNED DEFAULT NULL,
  `shed_width` INT(11) UNSIGNED DEFAULT NULL,
  `shed_height` INT(11) UNSIGNED DEFAULT NULL,
  PRIMARY KEY (`order_id`),
  INDEX `orders_fk` (`order_id` ASC),
  INDEX `roof_types_fk` (`roof_type_id` ASC),
  CONSTRAINT `roof_types_fk`
    FOREIGN KEY (`roof_type_id`)
    REFERENCES `fogcarport`.`roof_types` (`roof_type_id`)
    ON DELETE CASCADE,
  CONSTRAINT `orders_fk`
    FOREIGN KEY (`order_id`)
    REFERENCES `fogcarport`.`orders` (`order_id`)
    ON DELETE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fogcarport`.`bills_of_materials`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fogcarport`.`bills_of_materials` (
  `order_id` INT(11) NOT NULL,
  `component_id` INT(11) NOT NULL,
  `amount` INT(11) DEFAULT '1',
 # `amount_type` VARCHAR(45) DEFAULT 'stk.',
  PRIMARY KEY (`order_id`, `component_id`),
  INDEX `components_fk` (`component_id` ASC),
  INDEX `carports_fk` (`order_id` ASC),
  CONSTRAINT `carports_fk`
    FOREIGN KEY (`order_id`)
    REFERENCES `fogcarport`.`carports` (`order_id`)
    ON DELETE CASCADE,
  CONSTRAINT `components_fk`
    FOREIGN KEY (`component_id`)
    REFERENCES `fogcarport`.`components` (`component_id`)
    ON DELETE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fogcarport`.`orders`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fogcarport`.`orders` (
  `order_id` INT(11) NOT NULL AUTO_INCREMENT,
  `customer_id` INT(11) NOT NULL,
  `customer_address` VARCHAR(60) NOT NULL,
  `order_receive_date` DATE NOT NULL,
  `order_status` ENUM('pending', 'sent') NULL DEFAULT 'pending',
  `order_send_date` DATE NULL DEFAULT NULL,
  `total_price` FLOAT DEFAULT 0,
  PRIMARY KEY (`order_id`),
  INDEX `customers_fk` (`customer_id` ASC),
  CONSTRAINT `customers_fk`
    FOREIGN KEY (`customer_id`)
    REFERENCES `fogcarport`.`customers` (`customer_id`)
    ON DELETE CASCADE)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `fogcarport`.`cases`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fogcarport`.`cases` (
  `case_id` INT(11) NOT NULL AUTO_INCREMENT,
  `order_id` INT(11) NOT NULL,
  `customer_id` INT(11) NOT NULL,
  `employee_id` INT(11) NOT NULL,
  `case_status` ENUM('open', 'closed') NULL DEFAULT 'open',
  PRIMARY KEY (`case_id`, `order_id`),
  INDEX `orders_fk2` (`order_id` ASC),
  INDEX `customers_fk2` (`customer_id` ASC),
  INDEX `employees_fk` (`employee_id` ASC),
  CONSTRAINT `customers_fk2`
    FOREIGN KEY (`customer_id`)
    REFERENCES `fogcarport`.`customers` (`customer_id`)
    ON DELETE CASCADE,
  CONSTRAINT `employees_fk`
    FOREIGN KEY (`employee_id`)
    REFERENCES `fogcarport`.`employees` (`employee_id`)
    ON DELETE CASCADE,
  CONSTRAINT `orders_fk2`
    FOREIGN KEY (`order_id`)
    REFERENCES `fogcarport`.`orders` (`order_id`)
    ON DELETE CASCADE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
