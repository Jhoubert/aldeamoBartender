# Aldeamo Tech Challenge

There is the solution to the tech challenge in order to continue with the process.

## Stack:
- Java 8 as programming language
- Spring Boot 2.5 as framework
- Mysql 5.7 as database
- Docker (with docker-compose) as container


## How to run:

There is to ways to run this project.

---
### Running with docker
(Unit-Test skipped).
  
*This is the easiest way to run the project, because the docker-compose.yml contains every single requirement configured for this project including maven compilation and satisfying all te dependencies or libraries. The unit-test are skipped because during the maven compilation mysql isn't running yet, it just require a extra configuration with a ci/cd in order to be able to run test in this step.*

- **Requirements**:
    - Docker (with `docker-compose` on Windows is auto-intalled)

- **Running project**
    - For run, you only need to run `docker-compose up -d` and it will resolve the dependencies and compile the project automatically.
    
        ***note**:The first launch will compile the project and resolve all the dependencies, so the project will take a few minutes to download all the required packages.*

----

### Running locally 
*In this case you need to has java installed, be able to run a project with mvn and has a Mysql installed in your environmen, you can use a dockerized mysql database with no problem*
- **Requirements**:
    - Java 8
    - An IDE _(optional)_
    - MVN installed
    - Mysql (can be a dockerized mysql instance)


- **Configuring the project**
    
    If you want to run the project locally you need to configure the file `service.properties`
  
    In this file you need to change the `datasource url`, `datasource.username` and `datasource.password` for your own. 
  
    For use a local url you must uncomment the line wich contains localhost url `#spring.datasource.url=jdbc:mysql://localhost:3306/bartender` and just comment the another one.
    For `dataosource.username` and `datasource.passowrd` you only need to change it for your own database credentials, if the user doesn't have privileges to create databases you need to create `bartender` and grant privileges to the user were you using.
    

- **Running project**
    
  *First way:*
  
  You need to compile the project using mvn, you must run mvn installer:
  
  `mvn clean install`

  When the project is compiled you must move to the target folder and run the jar with mvn or java:
  
  `cd target`
  
  `mvn spring-boot:run`

  Second way (easier): 
  
  Importing the project in a IDE with mvn support and running it the file `BartenderTestApplication.java` for run the project (service) or `BartenderTestApplicationTests.java` in order tu run the unitTests.



## How to consume the service/API:
The base api endpoint is `/api/bartender/solve/{q}/{id}` 

The method for this request is `GET`

Where `q` means the number of iteration to do and `id` represents the id in the database.

The service is listening on port `8080` by default, it can be changed in the docker configuration easily, also the service is running `without SSL`, just `HTTP`.



If I do a normal and existing request such  `http://localhost:8080/api/bartender/solve/3/1` I'll get this response:

```
{
    "status": "SUCCESS",
    "result": "8,6,4,2,5,7",
    "time": "4 ms"
}
```


If I try to request a non existing id I will just get a 404 response.
`http://localhost:8080/api/bartender/solve/3/122`
