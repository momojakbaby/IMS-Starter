CREATE SCHEMA IF NOT EXISTS `IMS`;

USE `IMS` ;

CREATE TABLE IF NOT EXISTS `IMS`.`customers` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(40) DEFAULT NULL,
    `surname` VARCHAR(40) DEFAULT NULL,
    `address` VARCHAR(40) DEFAULT NULL,
    `phone number` INT NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `IMS`.`items` (
	`item_name` char(30),
	`order_list` varchar(30) DEFAULT NULL,
	`date` float(10) DEFAULT NULL,
	PRIMARY KEY (`item_name`)
);

CREATE TABLE IF NOT EXISTS `IMS`.`orders` (
	`OrderID` int NOT NULL AUTO_INCREMENT,
	`CustomerID` int DEFAULT NULL,
	`order_list` int NOT NULL,
	`order_details` int DEFAULT NULL,
    `item_name` char(30),
	PRIMARY KEY (`OrderID`),
	FOREIGN KEY (`CustomerID`) REFERENCES customers(`id`),
	FOREIGN KEY (`item_name`) REFERENCES items(`item_name`)
	);
	
DESCRIBE TABLE orders;