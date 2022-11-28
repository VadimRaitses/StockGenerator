package com.vadim.stockgenerator.configuration

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.vadim.stockgenerator.model.Stock
import com.vadim.stockgenerator.service.impl.StockServiceImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class StockMsConfiguration {


    @Bean
    fun getObjectMapper(): ObjectMapper {
        return ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

    }

    @Bean
    fun stockCollection(): List<Stock> {
        val fileContent: String = StockServiceImpl::class.java.getResource("/stocks.json")?.readText()
            .orEmpty()
        return getObjectMapper().readValue(fileContent)
    }


}
