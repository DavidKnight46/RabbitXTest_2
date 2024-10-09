# RabbitX Backend Test

### Description

To create a simple REST Backend Orderbook cross-matching engine.

### Software Pre-requistes

The following software is required to run this application:

* Java 17
* Gradle
* GIT
* GitBash (Optional - to run build script)
* draw.io (Optional - to view the UML class diagram & ERD Diagram)

### How to build & run

To build and run this application please perform the following steps:

* Please run the build script from root directory with following command: ./build.sh
  (If unable to run set the permission of the build.sh script to chmod 777 from command line)

### Development 

The way I approached this task was to first read the task and get an understanding of the basic requirements and use cases, 
then create a basic UML class diagram to help me identify the data (i.e. models/objects), the methods (classes/interfaces) 
and how the data would flow around the application and basic core logic flow. I also take some time to 
design an Entity Relationship Diagram to help me identify the tables and how to store the data in my in-memory DB. 
Then I write the required interfaces and used them to make service unit test cases following an Test Driven Development
(TDD) approach to my development of this application. I also added in some Integration tests
following good testing procedure.

I then created the necessary scripts to set-up the databases and build and run the application,
in order to make it as easy as possible for the user to run.

### Improvements

There are a number of improvements that could be made including:

* Docker/Containerize application, enable to run on cloud environment and
scale according to demand
* Re-write in Kotlin

### Limitations

There are a number of limitations, including:

* Was unable to use tarantool as unable to get docker image, so as an alternative I have 
used H2 in-memory database
* Unable to add users 
* Unable to create new wallets for users

### Author

David Knight

### License

Property of RabbitX

