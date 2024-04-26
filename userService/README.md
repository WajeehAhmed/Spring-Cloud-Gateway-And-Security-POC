# Sample RESTful MicroService

User, is a simple RESTful API created using SpringBoot, this repository help you get started with basic microservice in this case a RESTful API.
User microservice have basic CRUD operations for user entity.

We would be utilizing this microservice down the line when we get into **Spring Cloud and Spring security implementation**
## Stack

* Java 17
* Spring Boot 3

## Get Started
You can run this application locally via IDE also if you are interested in how to create a docker container of this application see the below instruction.

### Dockerize this application

In order to run this application using docker do the following :

This will create build in the target folder.
````
mvn install
````
This will create a docker image with the name userservice, this build command utilize Dockerfile to create this image.
````
docker build -t userservice .
````
This command will create a container form this image. -d flag mean run container in detach mode, -p flag bound ports <port_exposed> : <container_port> , --name represent container name.
````
docker container run -d -p 3000:8081 --name usercontainer userservice:latest
````
