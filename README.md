### Stock And Prices Generator

JavaRx, MultiThread and Kotlin workaround over centralized\
publishing available stocks prices to subscribed client over websocket.

Useful for those who are looking to generate stocks over socket channel and want to develop client for stock broker.\
#websocket #broker #stocks

```
    mvn spring-boot:run  
    java -jar stock-generator-0.0.1-SNAPSHOT.jar
    
```

client available on **http://localhost:8080**  
swagger http://localhost:8080/swagger-ui.html#/ \
http://localhost:8080/v2/api-docs  \


socket with java rx publishing available on **http://localhost:8080/rx-stock**
socket with thread publishing available on **http://localhost:8080/stock**
socket with thread publishing available on **http://localhost:8080/stock-event**

infinitive stock generated server with client,\
**ws:/stock will** generate stocks events with event ADD or DELETE\
**ws:/stock-event** will generate prices to stocks already created\


