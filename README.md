# db-blind-auctions

### Prerequisites
This project has been implemented using Maven 3.9.9 and Java JDK 11, to check what version you've got installed run these commands
```
> mvn -version
Apache Maven 3.9.9 (8e8579a9e76f7d015ee5ec7bfcdc97d260186937)
Maven home: /opt/apache-maven-3.9.9
Java version: 17.0.14, vendor: Ubuntu, runtime: /usr/lib/jvm/java-17-openjdk-amd64
Default locale: en_US, platform encoding: UTF-8
OS name: "linux", version: "6.8.0-52-generic", arch: "amd64", family: "unix"
> java -version
openjdk version "17.0.14" 2025-01-21
OpenJDK Runtime Environment (build 17.0.14+7-Ubuntu-124.04)
OpenJDK 64-Bit Server VM (build 17.0.14+7-Ubuntu-124.04, mixed mode, sharing)
```

## Code Structure
The code was divided into 3 modules under `system-parent` root maven project
- backend-service - this is where all the API calls to the auction system are coming in
- user-service - internal user service authenticating the users and verifying if the token is valid
- shared-resource - API interface of the user service making API calls easier 
- auction-service - internal auction service managing all the auctions
- auction-service-resource - API interface of the auction service making API calls easier
  
The backend-service is running on http://localhost:8085 and the swagger over there is used to show easy to use UI to fire requests
The user-service is running on http://localhost:8086
The auction-service is running on http://localhost:8087

The Swagger UI is running on http://localhost:8085/swagger-ui

There are pre-canned users inserted into the DB defined by name in `user-service/src/main/resources/users.txt`


## Source Code

### Development setup
To install all the dependencies please execute this commands from the main folder
```
mvn install
```
Then clean the build
```
mvn clean
```
Then clean the build
```
mvn verify
```
### How to execute the code
You need to run 3 classes
backend-service/src/main/java/com/db/system/backend/StartBackendService.java
user-service/src/main/java/com/db/system/user/StartUserService.java
auction-service/src/main/java/com/db/system/auction/StartAuctionService.java
