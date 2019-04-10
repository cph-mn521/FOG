CREATE SCHEMA `fogcarport` DEFAULT CHARACTER SET utf8  DEFAULT COLLATE utf8_danish_ci;
USE `fogcarport`;

DROP TABLE IF EXISTS `employees`;
DROP TABLE IF EXISTS `customers`;
DROP TABLE IF EXISTS `components`;
DROP TABLE IF EXISTS `bills_of_material`;
DROP TABLE IF EXISTS `orders`;
DROP TABLE IF EXISTS `cases`;

CREATE TABLE `employees` (
	`employee_id` INT NOT NULL,
    `name` VARCHAR(45) NOT NULL,
    `email_address` VARCHAR(45) NOT NULL,
    `password` VARCHAR(45) NOT NULL,
    `phone_number` INT UNSIGNED NOT NULL,
	`rank` ENUM('storeworker','salesperson','admin','superadmin') NOT NULL,
    PRIMARY KEY (`employee_id`)
);  
CREATE INDEX `` ON ``(``);

CREATE TABLE `customers` (
    `customer_id` INT NOT NULL,
    `name` VARCHAR(45) NOT NULL,
    `email_address` VARCHAR(45) NOT NULL,
    `password` VARCHAR(45) NOT NULL,
    `phone_number` INT UNSIGNED NOT NULL,
    PRIMARY KEY (`customer_id`)
);  
CREATE INDEX `` ON ``(``);

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
CREATE INDEX `` ON ``(``);

CREATE TABLE `bills_of_material` (
    `bill_id` INT NOT NULL,
    `component_id` INT NOT NULL,
    `amount` INT DEFAULT 1,
    PRIMARY KEY (`bill_id`, `component_id`),
    
    CONSTRAINT ``
    FOREIGN KEY (``)
    REFERENCES `` (``)
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
    
    CONSTRAINT ``
    FOREIGN KEY (``)
    REFERENCES `` (``)
    ON DELETE CASCADE
);

CREATE TABLE `cases` (
    `case_id` INT NOT NULL,
    `order_id` INT NOT NULL,
    `customer_id` INT NOT NULL,
    `employee_id` INT NOT NULL,
    `order_status` ENUM ('open','closed') DEFAULT 'open',
    PRIMARY KEY (`case_id`),
    
    CONSTRAINT ``
    FOREIGN KEY (``)
    REFERENCES `` (``)
    ON DELETE CASCADE
);