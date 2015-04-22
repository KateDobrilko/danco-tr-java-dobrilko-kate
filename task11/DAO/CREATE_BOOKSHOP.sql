
CREATE SCHEMA IF NOT EXISTS `Bookshop` DEFAULT CHARACTER SET utf8;


USE `Bookshop`;


CREATE TABLE IF NOT EXISTS `book` (
   `id` INT NOT NULL AUTO_INCREMENT ,
   `name` VARCHAR(50) NOT NULL ,
   `author` VARCHAR(50) NOT NULL ,
   `unclaimed` TINYINT(1) NOT NULL ,
   `ordered` TINYINT(1) NOT NULL ,
   `dateOfPublication` DATE NOT NULL ,
   `dateOfAddition` DATE NULL ,
   `price` REAL NOT NULL ,
    PRIMARY KEY (`id`) 
  
);

CREATE TABLE IF NOT EXISTS `order` (
   `id` INT NOT NULL AUTO_INCREMENT ,
   `bookId` INT NOT NULL ,
   `dateOfExecution` DATE NULL ,
   `status` TINYINT(1) NOT NULL ,
   PRIMARY KEY (`id`) 
   
);

CREATE TABLE IF NOT EXISTS `reply` (
   `id` INT NOT NULL AUTO_INCREMENT ,
   `numberOfRequests` INT NOT NULL ,
   `executed` TINYINT(1)  NOT NULL ,
   `bookId` INT NOT NULL ,
   PRIMARY KEY (`id`) 
);
