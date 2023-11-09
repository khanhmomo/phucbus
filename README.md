# PHUCBUS 2023
> A Java Servlet Web project

## Environment setup

- Prefer IDE: [IntelLIJ IDEA](https://www.jetbrains.com/idea/)
- Server: [Apache Tomcat 9.0.82](https://tomcat.apache.org/download-90.cgi)
- Java Development Kit: [JDK 21](https://www.oracle.com/java/technologies/downloads/)
- Database: [MySQL](https://dev.mysql.com/downloads/mysql/)
### INSTALL MYSQL CONNECTOR
1. Download [mysql-connector-8.0.11.jar](https://jar-download.com/artifacts/mysql/mysql-connector-java/8.0.11/source-code)
2. Create <lib> folder in main/webapp
   .
    └── main                    
        └──  webapp
              └──  lib
             
 
4. Copy mysql-connector-8.0.11.jar to /lib
5. Copy mysql-connector-8.0.11.jar to <apachetomcat_folder>/lib
   
#Create IntelLij Servlet Template

https://www.jetbrains.com/help/idea/creating-and-configuring-web-application-elements.html

#MYSQL config

use company;

create table users(id int primary key auto_increment, user_name varchar(50), user_pw varchar(50), user_email varchar(50));

desc users;

!Remember to change the port and mysql account when using mysql command in java source code!
