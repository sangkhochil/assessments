## URL Lookup

### How to run application
1. import application as maven project in your IDE (I used eclipse)
2. after successfully imported and download all dependencies run as java application.
3. application will run on port <b>8031</b> (you can change in application.properties file)
4. you can test API's by rest client(ex. Postman) or curl

### Root url
[http://localhost:8031/](http://localhost:8031/)

### API doc

[http://localhost:8031/swagger-ui.html](http://localhost:8031/swagger-ui.html)

### Actuator and HAL
[http://localhost:8031/actuator](http://localhost:8031/actuator)

[http://localhost:8031/](http://localhost:8031/)

### Note
- Current implementation is straightforward, using in-memory map to store data and caching system.
for better performance used executor framework for multiple task execution at simultaneously and 
also use in-memory caching map.
- Load sample mapped table from .xml file(resources/data.xml)

### Thoughts
- For better performance we can use load balancer(ex. round robin/hash based) & caching system(ex. memchched/redis)

- Spring cloud Load Balancer(Feign/Ribbon) provide client side balancing.
As container-orchestration(kubernetes) also provide load balancing by pods replicaSet.
