### Stock Generator

JavaRx and Kotlin workaround over centralized publishing available stocks prices to subscribed client over websocket.

```
    mvn spring-boot:run  
    java -jar stock-generator-0.0.1-SNAPSHOT.jar
    
```

client available on **http://localhost:8080**  
socket with java rx publishing available on **http://localhost:8080/rx-stock**
socket with thread publishing available on **http://localhost:8080/stock**

All connected clients will always get same published stock emitted by server current state managed via :

```
                ConnectableObservable
```

stock data had taken from https://datahub.io/core/nyse-other-listings#data


###next steps
1. Implementation Stock and Prices service, thus replace json file with static data 
and support more realistic imitation of stock market, by adding and deleting available stocks
2. implement user service with subscription for stock list.
3. adding broker communication.

