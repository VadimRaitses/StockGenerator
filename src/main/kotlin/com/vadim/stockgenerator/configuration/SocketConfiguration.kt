package com.vadim.stockgenerator.configuration

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Configuration
import org.springframework.web.socket.config.annotation.EnableWebSocket
import org.springframework.web.socket.config.annotation.WebSocketConfigurer
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry
import org.springframework.web.socket.handler.TextWebSocketHandler


@Configuration
@EnableWebSocket
class SocketConfiguration : WebSocketConfigurer {

    @Autowired
    @Qualifier("threadStockGeneratorHandler")
    private lateinit var stockHandler: TextWebSocketHandler

    @Autowired
    @Qualifier("rxStockGenerator")
    private lateinit var rxStockGenerator: TextWebSocketHandler


    @Autowired
    @Qualifier("threadPriceGeneratorHandler")
    private lateinit var priceStockGenerator: TextWebSocketHandler


    override fun registerWebSocketHandlers(registry: WebSocketHandlerRegistry) {

        registry.addHandler(stockHandler, "/stock")
        registry.addHandler(priceStockGenerator, "/stock-event")

        registry.addHandler(rxStockGenerator, "/rx-stock")
    }

}
