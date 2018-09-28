# transactionservise

Technnology used
---------------------
Java 1.8
Spring Boot 2.0.2
Maven 3.5.4
JPA
MySql 


Pre Requisites
--------------
My Sql
Maven
Java

Setup Project
-----------------
Step 1
Create mysql database. Please find connection details as below. For further reference, you can find more details here.(/transactionservise/src/main/resources/application.properties)

spring.datasource.url=jdbc:mysql://localhost:3306/hoolah
spring.datasource.username=root
spring.datasource.password=root

Step 2
Create a table using following query. 

CREATE TABLE `hoolah`.`tranaction` (
  `tranaction_id` VARCHAR(100) NOT NULL,
  `tranaction_date` datetime,
  `amount` DOUBLE NOT NULL,
  `merchant` VARCHAR(100) NOT NULL,
  `trans_type` VARCHAR(100) NOT NULL,
  `related_transaction` VARCHAR(100) NULL,
  PRIMARY KEY (`tranaction_id`));
  
Step 3 



Step 3

Run following class in the project.(FYI You can import project to Eclipse IDE and run)
com.hoolah.springboot.Application

Step 4



