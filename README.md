# dataCompare

![](https://gitee.com/ZhuGeZiFang/data-compare/badge/star.svg)
![](https://gitee.com/ZhuGeZiFang/data-compare/badge/fork.svg?theme=gvp)
![](https://img.shields.io/github/stars/zhugezifang/dataCompare.svg?logo=GitHub)
![](https://img.shields.io/github/forks/zhugezifang/dataCompare.svg?logo=GitHub)
![](https://img.shields.io/github/watchers/zhugezifang/dataCompare.svg?logo=GitHub)
![](https://img.shields.io/github/license/zhugezifang/dataCompare.svg)
![](https://img.shields.io/github/v/release/zhugezifang/dataCompare?label=latest&style=flat-square)

[![EN doc](https://img.shields.io/badge/document-English-blue.svg)](README.md)
[![CN doc](https://img.shields.io/badge/文档-中文版-blue.svg)](README-CN.md)


#### Introduction
dataCompare is a database comparison platform: support Hive table data comparison, MySQL、Doris data comparison, realize automatic configuration for data comparison, avoid frequent SQL writing for processing, and consider supporting CK and so on

![image](https://user-images.githubusercontent.com/28300167/207563534-e4df0c95-846b-4cf3-be68-37b91bd05f0b.png)

#### Features
(1)Low-code configuration can realize the comparison of data tables without cumbersome sql development

(2)The following functions are currently supported: magnitude comparison, consistency comparison, difference case discovery, MySQL、Hive and Doris haved already supported

(3)Subsequent plan support: unfamiliar table pointer detection, including: enumeration value detection, range value detection, primary key id hash detection


#### Software Architecture

![image](https://user-images.githubusercontent.com/28300167/207563635-373a656f-6794-4927-a094-6605f868b708.png)


Technology stack:

End：Spring boot + Mybatis

DataBase:MySQL

Parsing Engine：Antrl

Big Data：Hive、Spark


#### System flowchart

![输入图片说明](image1.png)
![输入图片说明](image2.png)
![输入图片说明](image3.png)
![输入图片说明](image4.png)

![img_1.png](img_1.png)

![img.png](img.png)


#### Demonstration of system functionality
Home
![image](https://user-images.githubusercontent.com/28300167/207257662-273fc531-c21e-437a-9d20-f15a533b58bd.png)

DbConfig

mysql config

![image](https://user-images.githubusercontent.com/28300167/207256310-8c6d0be4-90c2-4a71-a49b-c54d3537a7bf.png)

hive config

![image](https://user-images.githubusercontent.com/28300167/207497891-8dc317f6-06f3-4d53-96d5-400586e0a488.png)


job config
![image](https://user-images.githubusercontent.com/28300167/207256145-7ce5eaa2-7030-4c2c-91d9-3e566162e91e.png)

Comparison results are displayed
![image](https://user-images.githubusercontent.com/28300167/208607718-0767ff93-223a-408d-a586-7d509f278197.png)

![image](https://user-images.githubusercontent.com/28300167/207259977-fd2258a8-fce1-4a3b-85a3-2b6213e3b0a7.png)

![3fd83de9c582347f7f88cc82f438db4](https://user-images.githubusercontent.com/28300167/208607767-94cffce0-30f3-45ec-a280-978964e153bb.png)


#### The system running environment

java jdk8

mysql 5.7.36

Runing config

(1)Run the SQL files in the SQL directory in the database, create database and tables

(2)download the release jar(https://github.com/zhugezifang/dataCompare/releases) or build jar using the source code of the project

(3)edit database config information of application.yml

(4)run java -jar -Dspring.config.location=application.yml dataCompare.jar (application.yml and jar must in the same directory)

(5)visit http://127.0.0.1/ (UserName:admin PassWord:admin123)

Environment installation configuration

(1)If you want to implement Hive data comparison configuration, you need to install the Hive environment first(The installation documentation refers to the docker quick installation of the Hive environment：https://blog.csdn.net/ifenggege/article/details/107860477)

(2)After installation, when creating a new data source connection, select Hive at the address jdbc:hive2://ip:10000


#### Technological Communication
![image](https://user-images.githubusercontent.com/28300167/207255900-152d6834-9602-4ada-91ca-ad9906d89bf8.png)


#### Thanks
Thanks ruoyi Provides front-end services
