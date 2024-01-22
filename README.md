
# Demo application

#### Run locally

Steps:
- Make sure you have java 21 installed and JAVA_HOME variable set
- Run _./gradlew bootRun_
- Application will run with in memory h2 database that will be populated at the start
- All endpoints are secured with basic authentication (user:password base64 encoded)
- User with credentials: username: demo and password: demo is created during initialization

#### Run with docker

If local setup is too much you can build docker image and run it.
Steps:
- ./gradlew bootBuildImage --imageName=demoapp/image
- docker run -d -p 8080:8080 demoapp/image:latest

#### Swagger

It is possible to test api over swagger-ui at:
_http://localhost:8080/swagger-ui/index.html_.


[**UML diagram** ](demo_uml.png)
