# server-logs-processor - (Large File Processing Example Project)
This is a sample Java / Maven / Spring Boot application that can be used to show demo of large file processing and much more. I hope it helps you.

## How to Run

### Using intellij :
- Download the zip or clone the Git repository.
- Unzip the zip file (if you downloaded one)
- Open Command Prompt and Change directory (cd) to folder containing pom.xml
- Open Eclipse 
   - File -> Import -> Existing Maven Project -> Navigate to the folder where you unzipped the zip
   - Select the right project
- Choose the Spring Boot Application file (search for @SpringBootApplication)
- Right Click on the file and Run as Java Application
- You are all Set

### Using command line :
This application is packaged as a war which has Tomcat 8 embedded. No Tomcat or JBoss installation is necessary. You run it using the java -jar command.

- Clone this repository
- Make sure you are using JDK 1.8 and Maven 3.x
- You can build the project and run the tests by running mvn clean package
- Once successfully built, you can run the service by one of these two methods:

        java -jar -Dspring.profiles.active=dev target/server-logs-processor.jar
        or
        mvn spring-boot:run -Drun.arguments="spring.profiles.active=dev"
