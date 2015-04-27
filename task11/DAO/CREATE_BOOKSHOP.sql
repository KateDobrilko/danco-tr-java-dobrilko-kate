
CREATE SCHEMA IF NOT EXISTS `Bookshop1` DEFAULT CHARACTER SET utf8;


USE `Bookshop1`;


CREATE TABLE IF NOT EXISTS `book` (
   `id` INT NOT NULL AUTO_INCREMENT ,
   `name` VARCHAR(50) NOT NULL ,
   `author` VARCHAR(50) NOT NULL ,
   `unclaimed` TINYINT(1) NOT NULL ,
   `ordered` TINYINT(1) NOT NULL ,
   `dateOfPublication` DATE NOT NULL ,
   `dateOfAddition` DATE NULL ,
   `price` REAL NOT NULL ,   
   `orderId` INT NOT NULL, 
    PRIMARY KEY (`id`),
    FOREIGHN KEY (orderId) REFERENCES order(id)
  
);

CREATE TABLE IF NOT EXISTS `order` (
   `id` INT NOT NULL AUTO_INCREMENT ,
   `dateOfExecution` DATE NULL ,
   `status` TINYINT(1) NOT NULL ,
   `sum` REAL NOT NULL,
   PRIMARY KEY (`id`) 
   
);

CREATE TABLE IF NOT EXISTS `reply` (
   `id` INT NOT NULL AUTO_INCREMENT ,
   `numberOfRequests` INT NOT NULL ,
   `executed` TINYINT(1)  NOT NULL ,
   `bookId` INT NOT NULL ,
   PRIMARY KEY (`id`),
   FOREIGHN KEY (bookId) REFERENCES book(id)
   
);
