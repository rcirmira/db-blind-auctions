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
mvn package
```
### How to execute the code
You need to run 3 classes
backend-service/src/main/java/com/db/system/backend/StartBackendService.java
user-service/src/main/java/com/db/system/user/StartUserService.java
auction-service/src/main/java/com/db/system/auction/StartAuctionService.java

Alternatively you could just run fat jar in this way
- to start user-service
```
cd user-service/target
java -jar user-service-0.1-jar-with-dependencies.jar
```
- to start auction-service
```
cd auction-service/target
java -jar auction-service-0.1-jar-with-dependencies.jar
```
- to start backend-service
```
cd backend-service/target
java -jar backend-service-0.1-jar-with-dependencies.jar
```

Then these are the steps to run the auction
1. Get a token for a user, e.g. Adam
```
curl -X 'GET' \
  'http://localhost:8085/api/v1/backend/user/get/Adam' \
  -H 'accept: text/plain'
```
2. Add an auction
```
curl -X 'POST' \
  'http://localhost:8085/api/v1/backend/auction/add/user/token/eyJhbGciOiJFUzUxMiJ9.eyJzdWIiOiJBZGFtIiwiZXhwIjoxNzM5OTA4NTk0LCJpZCI6Mn0.AFtxTj0h0TzTLVzTcLRJ1Fj2dBmgKxfz7h-oDU5q7zkS9AgK8QM9sUYno_KW5kiKGagxYInEnxlkln5C0dN6Sou-AHT-EnD9ON1GIKEPK3v5FqAEsylVF_8SVxNrWDyN2PFbHCd6_1S53ZPDXiuSE_mQEc81sXg3yliQE8PZontJCKco' \
  -H 'accept: application/json' \
  -H 'Content-Type: application/json' \
  -d '{
  "name": "a",
  "minPrice": 10
}'
```
3. Bid on auction
```
curl -X 'POST' \
'http://localhost:8085/api/v1/backend/auction/bid/user/token/eyJhbGciOiJFUzUxMiJ9.eyJzdWIiOiJBZGFtIiwiZXhwIjoxNzM5OTA4NTk0LCJpZCI6Mn0.AFtxTj0h0TzTLVzTcLRJ1Fj2dBmgKxfz7h-oDU5q7zkS9AgK8QM9sUYno_KW5kiKGagxYInEnxlkln5C0dN6Sou-AHT-EnD9ON1GIKEPK3v5FqAEsylVF_8SVxNrWDyN2PFbHCd6_1S53ZPDXiuSE_mQEc81sXg3yliQE8PZontJCKco' \
-H 'accept: application/json' \
-H 'Content-Type: application/json' \
-d '{
"auctionId": 1,
"price": 12
}'
```
4. End an auction
```
curl -X 'POST' \
  'http://localhost:8085/api/v1/backend/auction/1/end/user/eyJhbGciOiJFUzUxMiJ9.eyJzdWIiOiJBZGFtIiwiZXhwIjoxNzM5OTA4NTk0LCJpZCI6Mn0.AFtxTj0h0TzTLVzTcLRJ1Fj2dBmgKxfz7h-oDU5q7zkS9AgK8QM9sUYno_KW5kiKGagxYInEnxlkln5C0dN6Sou-AHT-EnD9ON1GIKEPK3v5FqAEsylVF_8SVxNrWDyN2PFbHCd6_1S53ZPDXiuSE_mQEc81sXg3yliQE8PZontJCKco' \
  -H 'accept: application/json' \
  -d ''
```