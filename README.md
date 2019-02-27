# mongo-search

## Problem Description
Develop a simple matching engine that presents Workers with appropriate Jobs.  We will provide a REST API for this task /workers, which provides the list of all available Workers and /jobs which provides a list of all available Jobs.

## Prerequisite
### Software List
 - Mongo DB Server 4.0.6
 - JDK 10
 - Eclipse
 - Maven

### Setup Mongo DB
I have downloaded Mongo DB Server ver 4.0.6 setup and hosted server on my local machine for this project. Server setup can be downloaded from [here]( https://www.mongodb.com/download-center/community).
Alternatively, one can setup database using [Mongo Atlas](https://cloud.mongodb.com/) (_cloud based service_). If you planned to use cloud based service, do take care of allowing service calls through *firewall*.

### Java
I have used JDK Version 10.

## Initial Setup
### Seeding data
Once you have checked out code - use below commands to seed data to mongo DB using "mongoimport" utility.

```cmd
cd <checkout directory>
mongoimport /db:jobsdb /collection:worker /file:seed-data\workers.json /jsonArray
mongoimport /db:jobsdb /collection:job /file:seed-data\jobs.json /jsonArray
```

### Verify data
Use below commands to check record count.

```
mongo <connection string>
> use jobsdb
switched to db jobsdb
> db.worker.find().count()
50
> db.job.find().count()
40
>
```

### Update DB Connection in app.properties
Code uses Mongo DB Driver version 3.10.1. So pick the version 3.6+ DB Connection string format. Specify the same in app.properties.

### Running test

Run below command to run test case and to verify search functionality.

```cmd
mvn test
```

### Launch App
Run below command to start the app server.
```
mvn exec:java -Dexec.mainClass="com.ms.job.App"

```

Once the application starts up, use below URL in browser.
http://[ip]:[port]/worker/[empid]/suggest 
