## Studium Service
description

## Requirements

 - JDK 8+
 - Maven
 - MySQL

 ## Import the database structure
```
$ mysql -u root -p
$ CREATE DATABASE studium;
$ exit;
```
In the  _main_  directory there is the sql structure of the database in  _Studium_DB.sql_, so import it into your database with:

```
$ mysql -u root -p db_name < project/path/Studium_DB.sql
```
## Set token & port server
In _src/main/resources_ open and change file _application.properties_
```
server.port=7777
token.app=YOUR_TOKEN
```

## Maven Build 
In the main directory there is _pom.xml_ 
```
$ mvn package
```
## Run Service
```
$ java -jar target/studium-0.0.1-SNAPSHOT.jar
```
