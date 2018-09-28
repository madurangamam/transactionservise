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

Notes
------
***When the system initialized, data will be inserted in to database *****

If you have to run separate data set, you have to clear the existing data set and run the application again.

If you need to change the date values, you can do it in following class.
com.hoolah.dao.TransactionDaoImpl

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

You can find csv file in following location.  
/transactionservise/src/main/resources/transactionRecords.csv

Step 4

Run following class in the project.(FYI You can import project to Eclipse IDE and run)
com.hoolah.springboot.Application

How to run the application
----------------------------
After running Application class, run the following url in browser.  
http://localhost:8080/getrecords{Merchant Name}

Example:-
http://localhost:8080/getrecords/Kwik-E-Mart




