CREATE DATABASE IF NOT EXISTS danco_dobrilko_10;

USE danco_dobrilko_10;


CREATE TABLE IF NOT EXISTS product (
   maker VARCHAR(10) NOT NULL,
   model VARCHAR(50) NOT NULL PRIMARY KEY,
   type VARCHAR(50) NOT NULL
);

CREATE TABLE iF NOT EXISTS laptop (
   code INT NOT NULL PRIMARY KEY,
   model VARCHAR(50) NOT NULL,
   speed SMALLINT NOT NULL,
   ram SMALLINT NOT NULL,
   hd REAL NOT NULL,
   price INT NULL,
   screen TINYINT NOT NULL,
   FOREIGN KEY (model) REFERENCES product(model)
);

CREATE TABLE IF NOT EXISTS pc (
   code INT NOT NULL PRIMARY KEY,
   model VARCHAR(50) NOT NULL,
   speed SMALLINT NOT NULL,
   ram SMALLINT NOT NULL,
   hd REAL NOT NULL,  
   cd VARCHAR(10) NOT NULL,
   price INT NULL,
   FOREIGN KEY (model) REFERENCES product(model)
);

CREATE TABLE IF NOT EXISTS printer(
   code INT NOT NULL PRIMARY KEY,
   model VARCHAR(50) NOT NULL,
   color CHAR(1) NOT NULL,
   type VARCHAR(10) NOT NULL,
   price INT NULL
);


