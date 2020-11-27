drop schema test;
CREATE SCHEMA IF NOT EXISTS `test`;
USE `test` ;
CREATE TABLE IF NOT EXISTS `test`.`customer` (
    `customer_id` INT(11) NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(40) NULL DEFAULT NULL,
    `last_name` VARCHAR(40) NULL DEFAULT NULL,
    PRIMARY KEY (`customer_id`)
);

CREATE TABLE IF NOT EXISTS `test`.`product` (
    `item_id` INT(11) NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(40) NULL DEFAULT NULL,
    `model` VARCHAR(40) NULL DEFAULT NULL,
    `stock` INT(11) NULL DEFAULT NULL,
    `price` INT(11) NULL DEFAULT NULL,
    PRIMARY KEY (`item_id`)
);

CREATE TABLE IF NOT EXISTS `test`.`orders` (
    `order_id` INT(11) NOT NULL AUTO_INCREMENT,
    `customer_id` INT(11) NULL DEFAULT NULL,
    `order_date` VARCHAR(40) NULL DEFAULT NULL,
    `total` INT(11) NULL DEFAULT '0',
    
    PRIMARY KEY (`order_id`)
);

CREATE TABLE IF NOT EXISTS `test`.`orders_items` (
    `order_id` INT(11) NULL DEFAULT NULL,
    `item_id` INT(11) NULL DEFAULT NULL,
    `quantity` INT(11) NULL DEFAULT NULL,
    `total` INT(11) NULL DEFAULT NULL,
    
    PRIMARY KEY (`order_id`)
);

