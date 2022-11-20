###Stock Generator

JavaRx workaround over centralized publishing available stocks prices to subscribed client over websocket.


    mvn spring-boot:run  
    java -jar stock-generator-0.0.1-SNAPSHOT.jar

client available on **http://localhost:8080**  
socket with java rx publishing available on **http://localhost:8080/rx-stock** 
socket with thread publishing  available on **http://localhost:8080/stock**
