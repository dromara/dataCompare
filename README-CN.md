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

#### 介绍
dataCompare 是一个数据库比对工具：支持hive表数据比对，mysql、Doris 数据比对，实现自动化配置进行数据比对，避免频繁写sql 进行处理，后续考虑支持ck等等

![image](https://user-images.githubusercontent.com/28300167/207563954-6e3dba02-84de-4881-9a23-371b88ed5b1e.png)

#### 功能介绍
(1)低代码配置，即可实现数据表的对比，不需要繁琐的sql开发

(2)目前已经支持如下功能： 量级对比、一致性对比、差异case 发现，已经支持MySQL、Hive、Doris

(3)后续计划支持：陌生表指针探测，包括：枚举值探测、范围值探测、主键id hash 探测


#### 软件架构

![输入图片说明](image77.png)

技术栈:

后端：Spring boot + Mybatis

数据库:MySQL

解析引擎：Antrl

数据存储引擎、计算引擎：Hive、Spark 等


#### 系统流程图

![输入图片说明](image1.png)
![输入图片说明](image2.png)
![输入图片说明](image3.png)
![输入图片说明](image4.png)

![img_1.png](img_1.png)

![img.png](img.png)


#### 系统功能演示
系统主页
![image](https://user-images.githubusercontent.com/28300167/207257662-273fc531-c21e-437a-9d20-f15a533b58bd.png)

数据库配置

mysql配置

![image](https://user-images.githubusercontent.com/28300167/207256310-8c6d0be4-90c2-4a71-a49b-c54d3537a7bf.png)

hive配置

![image](https://user-images.githubusercontent.com/28300167/207497891-8dc317f6-06f3-4d53-96d5-400586e0a488.png)


job配置
![image](https://user-images.githubusercontent.com/28300167/207256145-7ce5eaa2-7030-4c2c-91d9-3e566162e91e.png)

对比结果展示
![image](https://user-images.githubusercontent.com/28300167/208607718-0767ff93-223a-408d-a586-7d509f278197.png)

![image](https://user-images.githubusercontent.com/28300167/207259977-fd2258a8-fce1-4a3b-85a3-2b6213e3b0a7.png)

![3fd83de9c582347f7f88cc82f438db4](https://user-images.githubusercontent.com/28300167/208607767-94cffce0-30f3-45ec-a280-978964e153bb.png)

#### 系统运行
系统运行环境要求：

java jdk8

mysql 5.7.36

运行配置

(1)将sql目录中的sql文件进行运行在数据库，创建库和表

(2)下载发布好的jar(https://github.com/zhugezifang/dataCompare/releases) 或者自己构建jar

(3)修改数据库配置信息 application.yml，将数据库连接信息改为步骤(1)的数据库连接信息
![47d1145d147214348d6d0f2fc599ea7](https://user-images.githubusercontent.com/28300167/219598181-5731e845-a49e-43af-a96c-db6d6148b11a.png)

(4)运行 java -jar -Dspring.config.location=application.yml dataCompare.jar (application.yml和jar在同一目录下)

(5)访问 http://127.0.0.1/ 即可 (账号:admin 密码:admin123)

环境安装配置

(1)如果要实现Hive数据对比配置需要先安装Hive 环境(安装文档参考docker快速安装Hive环境：https://blog.csdn.net/ifenggege/article/details/107860477)

(2)安装好之后新建数据源连接时选择Hive，地址为：jdbc:hive2://ip:10000 


#### 技术交流
![image](https://user-images.githubusercontent.com/28300167/207255900-152d6834-9602-4ada-91ca-ad9906d89bf8.png)


#### 致谢
感谢ruoyi 提供前端服务
