# mongo-search

## Problem Description
Develop a simple matching engine that presents Workers with appropriate Jobs.  We will provide a REST API for this task – /workers, which provides the list of all available Workers and /jobs which provides a list of all available Jobs.

## Prerequisite
### Setup Mongo DB
I have downloaded Mongo DB Server ver 4.0.6 setup and hosted server on my local machine for this project. Server setup can be downloaded from [here]( https://www.mongodb.com/download-center/community).
Alternatively, one can setup database using [Mongo Atlas](https://cloud.mongodb.com/) (_cloud based service_). If you planned to use cloud based service, do take care of allowing service calls through *firewall*.

### Java
I have used JDK Version 10.

### Eclipse

## Initial Setup
### Seeding data
Once you have checked out code - use below commands to seed data to mongo DB using "mongoimport" utility.

```cmd
mongoimport /db:jobsdb /collection:worker /file:C:\Users\Mayank\Downloads\workers.json /jsonArray
mongoimport /db:jobsdb /collection:job /file:C:\Users\Mayank\Downloads\jobs.json /jsonArray
```

### Update DB Connection in app.properties
Code uses Mongo DB Driver version 3.10.1. So pick the version 3.6+ DB Connection string format.

### Running test

Run below command to run test case and to verify search functionality.

```cmd
mvn test
```
