# server-logs-processor - (Large File Processing Example Project)
This is a sample Java / Maven / Spring Boot application that can be used to show demo of large file processing and much more. I hope it helps you.

## How to Run

### Using intellij :
- Download the zip or clone the Git repository.
- Unzip the zip file (if you downloaded one)
- Open Command Prompt and Change directory (cd) to folder containing pom.xml
- Open intellij 
   - File -> Open -> Navigate to the folder where you unzipped the zip
   - Select the right project
- Select 'Open in new window'
- Configure Application and "Environment variables" as shown in below :

  ![image](https://user-images.githubusercontent.com/50489831/187118567-1f03b575-6d03-4645-9d88-27e17bd0aed1.png)

- Right Click on the file and Run as Java Application
- You are all Set

### Using command line :
This application is packaged as a jar. It is spring boot applicationn still No Tomcat or JBoss installation is necessary. You run it using the java -jar command.

- Clone this repository
- Make sure you are using JDK 1.8 and Maven 3.x
- You can build the project and run the tests by running mvn commands
   -  mvn clean
   -  mvn compile
   -  mvn install
- Once successfully built, you can run the service by one of these two methods:

        java -jar -Dspring.profiles.active=dev target/server-logs-processor-0.0.1-SNAPSHOT.jar --file.path=<absolute file path>/logFile.txt
        or
        mvn spring-boot:run -Drun.arguments="spring.profiles.active=dev,file.path=<absolute file path>/logFile.txt"
