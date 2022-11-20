package com.vadim.stockgenerator

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class StockGeneratorApplication

fun main(args: Array<String>) {
    runApplication<StockGeneratorApplication>(*args)
}
