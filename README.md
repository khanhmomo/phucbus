#Using IntelLij to run this project

#Install Apache Tomcat (Any version)

#Install JDK 21

#Install MySQL Community (Any version)

#INSTALL MYSQL CONNECTOR
1. Download mysql-connector-8.0.11.jar
2. Create <lib> folder in main/webapp
3. Copy mysql-connector-8.0.11.jar to /lib
   
#Create IntelLij Servlet Template

https://www.jetbrains.com/help/idea/creating-and-configuring-web-application-elements.html

#MYSQL config

use company;

create table users(id int primary key auto_increment, user_name varchar(50), user_pw varchar(50), user_email varchar(50));

desc users;
