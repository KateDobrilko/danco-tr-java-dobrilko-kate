
CREATE SCHEMA IF NOT EXISTS `BookDanco` DEFAULT CHARACTER SET utf8 ;
USE `BookDanco` ;


CREATE TABLE IF NOT EXISTS `BookDanco`.`Book` (
  `name` VARCHAR(100) NOT NULL,
  `author` VARCHAR(45) NOT NULL,
  `price` DOUBLE NOT NULL,
  `numberOfExemplars` INT ZEROFILL NOT NULL,
  `id` INT NOT NULL AUTO_INCREMENT,
  `publicationDate` DATE NOT NULL,
  `arrivalDate` DATE NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `BookDanco`.`Order` (
  `executionDate` DATE NULL DEFAULT NULL,
  `executed` TINYINT(1) NOT NULL,
  `id` INT NOT NULL AUTO_INCREMENT,
  `sum` DOUBLE NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `BookDanco`.`Reply` (
  `number` INT NOT NULL,
  `executed` TINYINT(1) NOT NULL,
  `id` INT NOT NULL AUTO_INCREMENT,
  `idBook` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `idBook_idx` (`idBook` ASC),
  CONSTRAINT `idBook`
    FOREIGN KEY (`idBook`)
    REFERENCES `BookDanco`.`Book` (`id`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `BookDanco`.`Order_To_Book` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `idO` INT NOT NULL,
  `idB` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `idB_idx` (`idB` ASC),
  INDEX `idO_idx` (`idO` ASC),
  CONSTRAINT `idB`
    FOREIGN KEY (`idB`)
    REFERENCES `BookDanco`.`Book` (`id`),
  CONSTRAINT `idO`
    FOREIGN KEY (`idO`)
    REFERENCES `BookDanco`.`Order` (`id`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;


START TRANSACTION;
USE `BookDanco`;
INSERT INTO `BookDanco`.`Book` (`name`, `author`, `price`, `numberOfExemplars`, `id`, `publicationDate`, `arrivalDate`) VALUES ('Harry Potter and the Philosopdher\'s Stone', 'J.K.Rowling', 15.25, 2, 1, '1997-06-28', '2015-02-01');
INSERT INTO `BookDanco`.`Book` (`name`, `author`, `price`, `numberOfExemplars`, `id`, `publicationDate`, `arrivalDate`) VALUES ('American Psycho', 'B.E.Ellis', 2.5, 3, 2, '1998-02-03', '2014-05-02');
INSERT INTO `BookDanco`.`Book` (`name`, `author`, `price`, `numberOfExemplars`, `id`, `publicationDate`, `arrivalDate`) VALUES ('Animal Farm', 'George Orwell', 13.2, 0, 3, '2002-03-04', NULL);

COMMIT;



START TRANSACTION;
USE `BookDanco`;
INSERT INTO `BookDanco`.`Order` (`executionDate`, `executed`, `id`, `sum`) VALUES (NULL, 0, 1, 0);
INSERT INTO `BookDanco`.`Order` (`executionDate`, `executed`, `id`, `sum`) VALUES (NULL, 0, 2, 0);
INSERT INTO `BookDanco`.`Order` (`executionDate`, `executed`, `id`, `sum`) VALUES ('2015-03-11', 1, 3, 28.45);

COMMIT;


START TRANSACTION;
USE `BookDanco`;
INSERT INTO `BookDanco`.`Reply` (`number`, `executed`, `id`, `idBook`) VALUES (3, 1, 1, 1);
INSERT INTO `BookDanco`.`Reply` (`number`, `executed`, `id`, `idBook`) VALUES (5, 0, 2, 3);

COMMIT;



START TRANSACTION;
USE `BookDanco`;
INSERT INTO `BookDanco`.`Order_To_Book` (`id`, `idO`, `idB`) VALUES (1, 3, 1);
INSERT INTO `BookDanco`.`Order_To_Book` (`id`, `idO`, `idB`) VALUES (2, 3, 3);

COMMIT;

