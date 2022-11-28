### Stock Generator

JavaRx, MultiThread and Kotlin workaround over centralized\
publishing available stocks prices to subscribed client over websocket.

```
    mvn spring-boot:run  
    java -jar stock-generator-0.0.1-SNAPSHOT.jar
    
```

client available on **http://localhost:8080**  
socket with java rx publishing available on **http://localhost:8080/rx-stock**
socket with thread publishing available on **http://localhost:8080/stock**
socket with thread publishing available on **http://localhost:8080/stock-event**

infinitive stock generated server with client,\
**ws:/stock will** generate stocks events with event ADD or DELETE\
**ws:/stock-event** will generate prices to stocks already created\
**http:/sessions** will show list of connected sessions and their states


