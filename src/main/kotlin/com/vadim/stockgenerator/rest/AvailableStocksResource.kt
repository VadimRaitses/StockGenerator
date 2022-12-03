package com.vadim.stockgenerator.rest

import com.vadim.stockgenerator.model.Message
import com.vadim.stockgenerator.model.Stock
import com.vadim.stockgenerator.model.StockSocketSession
import com.vadim.stockgenerator.service.SocketSessionService
import com.vadim.stockgenerator.service.StockService
import lombok.AllArgsConstructor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/stocks")
@AllArgsConstructor
class AvailableStocksResource @Autowired constructor(private val stocksResource : StockService) {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getStocks(): Collection<Stock> {
      return stocksResource.getStocks()
    }
}
