SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

CREATE SCHEMA IF NOT EXISTS `fogcarport` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_danish_ci;
USE `fogcarport` ;

DROP TABLE IF EXISTS `cases`;
DROP TABLE IF EXISTS `orders`;
DROP TABLE IF EXISTS `carport`;
DROP TABLE IF EXISTS `employees`;
DROP TABLE IF EXISTS `customers`;
DROP TABLE IF EXISTS `bills_of_material`;
DROP TABLE IF EXISTS `components`;



-- -----------------------------------------------------
-- Table `fogcarport`.`components`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fogcarport`.`components` (
  `component_id` INT(11) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(100) NULL DEFAULT NULL,
  `height` INT(11) UNSIGNED NOT NULL,
  `width` INT(11) UNSIGNED NOT NULL,
  `length` INT(11) UNSIGNED NOT NULL,
  `price` FLOAT UNSIGNED NOT NULL,
  PRIMARY KEY (`component_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fogcarport`.`bills_of_material`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fogcarport`.`bills_of_material` (
  `bill_id` INT(11) NOT NULL,
  `component_id` INT(11) NOT NULL,
  `amount` INT(11) NULL DEFAULT '1',
  PRIMARY KEY (`bill_id`, `component_id`),
  INDEX `components_fk` (`component_id` ASC),
  CONSTRAINT `components_fk`
    FOREIGN KEY (`component_id`)
    REFERENCES `fogcarport`.`components` (`component_id`)
    ON DELETE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fogcarport`.`customers`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fogcarport`.`customers` (
  `customer_id` INT(11) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `email_address` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `phone_number` INT(11) UNSIGNED NOT NULL,
  PRIMARY KEY (`customer_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fogcarport`.`employees`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fogcarport`.`employees` (
  `employee_id` INT(11) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `email_address` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `phone_number` INT(11) UNSIGNED NOT NULL,
  `rank` ENUM('storeworker', 'salesperson', 'admin', 'superadmin') NOT NULL,
  PRIMARY KEY (`employee_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fogcarport`.`roof_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fogcarport`.`roof_type` (
  `roof_type_id` INT(11) NOT NULL,
  `type` VARCHAR(45) NOT NULL,
  `cølær` VARCHAR(45) NOT NULL,
  `version` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`roof_type_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fogcarport`.`carport`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fogcarport`.`carport` (
  `carport_id` INT(11) NOT NULL,
  `roof_type_id` INT(11) NOT NULL,
  `carport_height` int(11) UNSIGNED NOT NULL,
  `carport_width` INT(11) UNSIGNED NOT NULL,
  `carport_length` INT(11) UNSIGNED NOT NULL,
  `has_shed` ENUM('yes', 'no') NOT NULL DEFAULT 'no',
  `shed_height` INT(11) UNSIGNED DEFAULT NULL,
  `shed_width` INT(11) UNSIGNED DEFAULT NULL,
  `shed_length` INT(11) UNSIGNED DEFAULT NULL,
  PRIMARY KEY (`carport_id`),
  INDEX `roof_type_fk` (`roof_type_id` ASC),
  CONSTRAINT `roof_type_fk`
    FOREIGN KEY (`roof_type_id`)
    REFERENCES `fogcarport`.`roof_type` (`roof_type_id`)
    ON DELETE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fogcarport`.`orders`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fogcarport`.`orders` (
  `order_id` INT(11) NOT NULL,
  `carport_id` INT(11) NOT NULL,
  `customer_id` INT(11) NOT NULL,
  `bill_id` INT(11) NOT NULL,
  `customer_address` VARCHAR(60) NOT NULL,
  `order_receive_date` DATE NOT NULL,
  `order_status` ENUM('pending', 'sent') NULL DEFAULT 'pending',
  `order_send_date` DATE NULL DEFAULT NULL,
  PRIMARY KEY (`order_id`),
  INDEX `customers_fk` (`customer_id` ASC),
  INDEX `bills_fk` (`bill_id` ASC),
  INDEX `carport_fk` (`carport_id` ASC),
  CONSTRAINT `carport_fk`
    FOREIGN KEY (`carport_id`)
    REFERENCES `fogcarport`.`carport` (`carport_id`)
    ON DELETE CASCADE,
  CONSTRAINT `bills_fk`
    FOREIGN KEY (`bill_id`)
    REFERENCES `fogcarport`.`bills_of_material` (`bill_id`)
    ON DELETE CASCADE,
  CONSTRAINT `customers_fk`
    FOREIGN KEY (`customer_id`)
    REFERENCES `fogcarport`.`customers` (`customer_id`)
    ON DELETE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fogcarport`.`cases`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fogcarport`.`cases` (
  `case_id` INT(11) NOT NULL,
  `order_id` INT(11) NOT NULL,
  `customer_id` INT(11) NOT NULL,
  `employee_id` INT(11) NOT NULL,
  `order_status` ENUM('open', 'closed') NULL DEFAULT 'open',
  PRIMARY KEY (`case_id`, `order_id`),
  INDEX `orders_fk` (`order_id` ASC),
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
  CONSTRAINT `orders_fk`
    FOREIGN KEY (`order_id`)
    REFERENCES `fogcarport`.`orders` (`order_id`)
    ON DELETE CASCADE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
