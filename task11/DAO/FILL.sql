INSERT INTO `Bookshop`.`book` (`id`, `name`,`author`,`unclaimed`,`ordered`,`dateOfPublication`,`dateOfAddition`,`price`,`orderId`) 
VALUES ('1','Harry Potter and the Philosopher’s Stone','J.K. Rowling','0','0','1991-08-14','2012-07-12','15.25', NULL);
INSERT INTO `Bookshop`.`book` (`id`, `name`,`author`,`unclaimed`,`ordered`,`dateOfPublication`,`dateOfAddition`,`price`,`orderId`) 
VALUES ('2','American Psycho','B.E.Ellis','0','0','1991-08-14','2012-07-12','15.25',NULL);

INSERT INTO `Bookshop`.`order` (`id`, `sum`,`dateOfExecution`,`status`) 
VALUES ('1','0',NULL,'0');


INSERT INTO `Bookshop`.`reply` (`bookId`, `numberOfRequests`,`executed`,`id`)
VALUES ('1','3','1','1');
