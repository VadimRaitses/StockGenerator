package com.vadim.stockgenerator.configuration

import com.vadim.stockgenerator.model.Stock
import com.vadim.stockgenerator.service.impl.StockServiceImpl
import com.vadim.stockgenerator.socket.StockHandler
import com.vadim.stockgenerator.socket.StockRxHandler
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.socket.config.annotation.EnableWebSocket
import org.springframework.web.socket.config.annotation.WebSocketConfigurer
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry

@Configuration
@EnableWebSocket
class SocketConfiguration : WebSocketConfigurer {


    fun stocksData(): List<Stock> {
        val fileContent: String = StockServiceImpl::class.java.getResource("/stocks.json")?.readText()
            .orEmpty()
        return ObjectMapper().readValue(fileContent)
    }

    @Bean
    fun stockService(): StockServiceImpl {
        return StockServiceImpl();
    }

    override fun registerWebSocketHandlers(registry: WebSocketHandlerRegistry) {
        registry.addHandler(StockHandler(stockService(),stocksData()), "/stock")
        registry.addHandler(StockRxHandler(stockService(), stocksData()), "/rx-stock")


    }
}
