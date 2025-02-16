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
- system-backend
- system-user
- system-auction
  
[//]: # (TODO add more about how it's structured)

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
[//]: # (TBC)

### Testing
[//]: # (TBC)

[//]: # (TODO add unit tests)
[//]: # (TODO add aggregate check)
[//]: # (TODO add surefire report)
[//]: # (TODO add jacoco)
[//]: # (TODO explain why verify)