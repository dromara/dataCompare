# dataCompare


#### 介绍
dataCompare 是一个数据库比对工具：支持hive表数据比对，mysql 数据比对，实现自动化配置进行数据比对，避免频繁写sql 进行处理，后续考虑支持doris、ck等等

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
![image](https://user-images.githubusercontent.com/28300167/207256310-8c6d0be4-90c2-4a71-a49b-c54d3537a7bf.png)

job配置
![image](https://user-images.githubusercontent.com/28300167/207256145-7ce5eaa2-7030-4c2c-91d9-3e566162e91e.png)

对比结果展示
![image](https://user-images.githubusercontent.com/28300167/207259977-fd2258a8-fce1-4a3b-85a3-2b6213e3b0a7.png)

#### 系统运行
系统运行环境要求：

java jdk8

mysql 5.7.36

运行配置

(1)将sql目录中的sql文件进行运行在数据库，创建库和表

(2)下载发布好的jar(https://github.com/zhugezifang/dataCompare/releases)或者自己构建jar

(3)修改数据库配置信息 application.yml

(4)运行 java -jar -Dspring.config.location=application.yml dataCompare.jar (application.yml和jar在同一目录下)

(5)访问 http://127.0.0.1/ 即可


#### 技术交流
![image](https://user-images.githubusercontent.com/28300167/207255900-152d6834-9602-4ada-91ca-ad9906d89bf8.png)


#### 致谢
感谢ruoyi 提供前端服务