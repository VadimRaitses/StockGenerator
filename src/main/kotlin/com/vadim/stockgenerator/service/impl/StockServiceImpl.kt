package com.vadim.stockgenerator.service.impl

import com.vadim.stockgenerator.model.Stock
import com.vadim.stockgenerator.service.StockService
import org.springframework.stereotype.Service


@Service
class StockServiceImpl : StockService {

    val stockBank = HashMap<String, Stock>()



    override fun getStock() {
        TODO("Not yet implemented")
    }

    override fun getStocks() {
        TODO("Not yet implemented")
    }

    override fun addStock(stock: Stock) {
        TODO("Not yet implemented")
    }

    override fun deleteStock() {
        TODO("Not yet implemented")
    }

    override fun updateStockState() {
        TODO("Not yet implemented")
    }
}
