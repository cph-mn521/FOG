CREATE SCHEMA IF NOT EXISTS `fogcarport` DEFAULT CHARACTER SET UTF8MB4;
USE `fogcarport`;

DROP TABLE IF EXISTS `cases`;
DROP TABLE IF EXISTS `orders`;
DROP TABLE IF EXISTS `bills_of_material`;
DROP TABLE IF EXISTS `components`;
DROP TABLE IF EXISTS `customers`;
DROP TABLE IF EXISTS `employees`;


CREATE TABLE `employees` (
	`employee_id` INT NOT NULL,
    `name` VARCHAR(45) NOT NULL,
    `email_address` VARCHAR(45) NOT NULL,
    `password` VARCHAR(45) NOT NULL,
    `phone_number` INT UNSIGNED NOT NULL,
	`rank` ENUM('storeworker','salesperson','admin','superadmin') NOT NULL,
    PRIMARY KEY (`employee_id`)
);

CREATE TABLE `customers` (
    `customer_id` INT NOT NULL,
    `name` VARCHAR(45) NOT NULL,
    `email_address` VARCHAR(45) NOT NULL,
    `password` VARCHAR(45) NOT NULL,
    `phone_number` INT UNSIGNED NOT NULL,
    PRIMARY KEY (`customer_id`)
);

CREATE TABLE `components` (
	`component_id` INT NOT NULL,
	`name` VARCHAR(45) NOT NULL,
    `description` VARCHAR(100) DEFAULT NULL,
    `height` INT UNSIGNED NOT NULL,
    `width` INT UNSIGNED NOT NULL,
    `length` INT UNSIGNED NOT NULL,
    `price` FLOAT UNSIGNED NOT NULL,
	PRIMARY KEY (`component_id`)
);

CREATE TABLE `bills_of_material` (
    `bill_id` INT NOT NULL,
    `component_id` INT NOT NULL,
    `amount` INT DEFAULT 1,
    PRIMARY KEY (`bill_id`, `component_id`),
    
    CONSTRAINT `components_fk`
    FOREIGN KEY (`component_id`)
    REFERENCES `components` (`component_id`)
    ON DELETE CASCADE
);

CREATE TABLE `orders` (
    `order_id` INT NOT NULL,
    `customer_id` INT NOT NULL,
    `bill_id` INT NOT NULL,
    `customer_address` VARCHAR(60) NOT NULL,
    `order_receive_date` DATE NOT NULL,
    `order_status` ENUM ('pending','sent') DEFAULT 'pending',
    `order_send_date` DATE DEFAULT NULL,
    PRIMARY KEY (`order_id`),
    
    CONSTRAINT `customers_fk`
    FOREIGN KEY (`customer_id`)
    REFERENCES `customers` (`customer_id`)
    ON DELETE CASCADE,
    
    CONSTRAINT `bills_fk`
    FOREIGN KEY (`bill_id`)
    REFERENCES `bills_of_material` (`bill_id`)
    ON DELETE CASCADE
);

CREATE TABLE `cases` (
    `case_id` INT NOT NULL,
    `order_id` INT NOT NULL,
    `customer_id` INT NOT NULL,
    `employee_id` INT NOT NULL,
    `order_status` ENUM ('open','closed') DEFAULT 'open',
    PRIMARY KEY (`case_id`),
    
    CONSTRAINT `orders_fk`
    FOREIGN KEY (`order_id`)
    REFERENCES `orders` (`order_id`)
    ON DELETE CASCADE,
    
    CONSTRAINT `customers_fk2`
    FOREIGN KEY (`customer_id`)
    REFERENCES `customers` (`customer_id`)
    ON DELETE CASCADE,
    
    CONSTRAINT `employees_fk`
    FOREIGN KEY (`employee_id`)
    REFERENCES `employees` (`employee_id`)
    ON DELETE CASCADE
);