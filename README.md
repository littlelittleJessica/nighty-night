# **Nighty Night**
## Introduction

Nighty Night is a website that provide the functions of aiding sleep and record sleep conditions. It also contains administration operations. The backend code is constructed with the frame SpringBoot. The database is based on MyBatis and the database connection uses Aliyun RDS. File management service uses Aliyun OSS.

Its main functions are **Voice of Night**, **Sleep Record**, **Sleep Diary** and **website background management**

## Preparation
####	(1) Prerequisites for operation
1. You need to install the following software first: idea, nodejs，jdk1.8, mysql8.0, navicat(database visualization tool), redis
2. Open the source code with idea
3. Install Maven dependencies
4. Connect the database with preperties in the file: src/main/resources/application.properties
5. Execute the database sketch doc/all.sql

####	(2) Install

Use the command to install the code(hint: you need to install git on your host first):
https://gitee.com/JessicaAg/nighty.git

####	(3) Project operation steps

##### Compiles and hot-reloads for development
```
Run NightyApplication
```



## Code structure description

```
doc - contains the database sketch all.sql
src
-------java
----------com.example.nighty
-------------common //some common entities
-------------config //including startup class and CorsConfig
-------------controller //mapping interfaces
-------------domain //database field entity
-------------enums
-------------exception
-------------interceptor
-------------mapper //database operation Interfaces by Mybatis Generator
-------------Req //Request entity
-------------Resp //Response entity
-------------service //logic of handling request
-------------util //tool classes
-------resources
----------generator //mybatis generator config
----------mapper //database mapper.xml
----------application.properties
----------logback-spring.xml //record log to local
```



### Contact
Ruhan Yin: 446067482@qq.com



