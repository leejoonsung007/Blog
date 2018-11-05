# Blog System
The Blog is under construction, more features for this system will release in the future...

[![Visit Website](https://img.shields.io/badge/website-open-blue.svg)](http://www.leejoonsung007.com) 

## Table of Contents
1. [Introduction](https://github.com/leejoonsung007/School/#introdution)
2. [Workflow](https://github.com/leejoonsung007/School/#workflow)
3. [Structure](https://github.com/leejoonsung007/School/#structure)
4. [Features](https://github.com/leejoonsung007/School/#features)

## Introduction

This blog is used to share some insights into development and recording study notes and process

## Workflow
Back-End technology stack: **Spring Boot**, **RESTful**, **Swagger 2**

Data storage: **MySQL**, **AWS S3**, **MyBatis(persistence layer)**

Deployment: **Tomcat**, **AWS EC2**

Front-end technology stack: **JavaScript**, **BootStrap**

**Note**: **Mybatis Generator** can introspect a database table (or many tables) and will generate artifacts that can be used to access the table(s), MBG can make a major impact on the large percentage of database operations that are simple CRUD
(Create, Retrieve, Update, Delete) - generate corresponding mapper. 

# Structure
```bash
SchoolBestChoice.github
├── _Blog	               #  The sources root of the program 
|  ├── src	               # The main dictionary 
|  |  ├── java                   
|  |  |  ├── com.springboot.blog            # package
|  |  |  |  ├── config                     # configure file
|  |  |  |  ├── controller                     # controller
|  |  |  |  ├── dao                     # a data access object
|  |  |  |  ├── dto                     # a data transfer object
|  |  |  |  ├── entity                     # entity class
|  |  |  |  ├── filter                     # filter
|  |  |  |  ├── generator                     # entity and mapper.xml file genrator
|  |  |  |  ├── interceptor                     # authentication management
|  |  |  |  ├── service                     # service
|  |  |  |  ├── util                     # util
|  |  |  |  ├── BlogApplication                # start-up file
|  |  |  |  ├── Swagger2                     # API document
|  |  ├── resources                     
|  |  |  ├── log                    # log file
|  |  |  ├── mapper                     # data mapper
|  |  |  ├── static                     # static file including js, css, html
|  |  |  ├── application.properties                     # configuration file
|  |  |  ├── generatorConfig.xml                     # genertator configuration file
|  |  |  ├── logback-spring.xml                     # log configurator fil
|  |  ├── test                    # test files
```
## Features
### Home page
![Default Type on Strap blog](https://github.com/leejoonsung007/Blog/blob/master/screenshot/Screenshot%202018-11-05%20at%2000.44.51.png)

### Blog page
you can view the article by clicking the corresponding picture.
![Default Type on Strap blog](https://github.com/leejoonsung007/Blog/blob/master/screenshot/Screenshot%202018-11-05%20at%2000.45.03.png)

### Note page
this is the page to recode my study notes and study process
![Default Type on Strap blog](https://github.com/leejoonsung007/Blog/blob/master/screenshot/Screenshot%202018-11-05%20at%2000.45.14.png)

### Aboutme page
![Default Type on Strap blog](https://github.com/leejoonsung007/Blog/blob/master/screenshot/Screenshot%202018-11-05%20at%2000.45.30.png)

