CREATE DATABASE IF NOT EXISTS danco_library_dobrilko_10;

USE danco_library_dobrilko_10;


CREATE TABLE IF NOT EXISTS order (
   id INT NOT NULL PRIMARY KEY,
   numberOfBooks INT NOT NULL,
   dateOfExecution TIMESTAMP NULL,
   status BOOL NOT NULL,
   order_book_id INT NOT NULL,
   FOREIGN KEY (order_book_id) REFERENCES order_book(id)
   
);

CREATE TABLE iF NOT EXISTS book (
    id INT NOT NULL PRIMARY KEY,
    name VARCHAR(30) NOT NULL,
    author VARCHAR(30) NOT NULL,
    unclaimed BOOL NOT NULL,
    ordered BOOL NOT NULL,  
    numberOfExemplars INT NOT NULL,
    dateOfPublication TIMESTAMP NOT NULL,
    dateOfAddition TIMESTAMP NOT NULL
    
);

CREATE TABLE IF NOT EXISTS order_book(
   id INT NOT NULL PRIMARY KEY,
   order_id INT NOT NULL,
   book_id INT NOT NULL,
   FOREIGN KEY (order_id) REFERENCES order(id),
   FOREIGN KEY (book_id) REFERENCES book(id)  
);

CREATE TABLE IF NOT EXISTS reply (
   id INT NOT NULL PRIMARY KEY,
   numberOfRequests INT NOT NULL,
   executed BOOL NOT NULL,
   book_id INT NOT NULL,
   FOREIGN KEY (book_id) REFERENCES book(id)
   
);

