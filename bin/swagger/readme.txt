Build:

git clone https://github.com/swagger-api/swagger-samples

cd to java/java-jaxrs-no-webxml (NOT java/java-servlet !!!)

mvn package -Dlog4j.configuration=file:./conf/log4j.properties jetty:run

This will start Jetty embedded on port 8002 but just terminate it.

Start GAE4J locally, you can navigate to http://localhost:8888/api/swagger.json to view the Swagger Resource Listing.
This tells you that the server is up and ready to demonstrate Swagger.

Successful launch:

http://localhost:8888/api/swagger.json

Sample log should include something like the following:

Nov 10, 2015 8:10:10 PM org.reflections.Reflections scan
INFO: Reflections took 132 ms to scan 1 urls, producing 3 keys and 3 values

References:

***Customizing Bootstrap (Shown on Swagger-UI)***
https://github.com/swagger-api/swagger-core/wiki/1.3--1.5-Migration

***General Setup***
https://github.com/swagger-api/swagger-samples/tree/master/java/java-jaxrs-no-webxml just for the build as java-servlet build was broken.
Once the build is successful, use the sample codes of https://github.com/swagger-api/swagger-samples/tree/master/java/java-servlet for everything!
