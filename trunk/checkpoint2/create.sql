CREATE SCHEMA IF NOT EXISTS `danco` DEFAULT CHARACTER SET utf8 ;
USE `danco` ;


CREATE TABLE IF NOT EXISTS `danco`.`Employee` (
  `f_name` VARCHAR(45) NOT NULL,
  `l_name` VARCHAR(45) NOT NULL,
  `id` INT NOT NULL AUTO_INCREMENT,
  `b_date` DATE NOT NULL,
  `gender` TINYINT(1) NOT NULL,
  `title`  VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;
