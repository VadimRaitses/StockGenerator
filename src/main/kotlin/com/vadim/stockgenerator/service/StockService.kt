package com.vadim.stockgenerator.service

import com.vadim.stockgenerator.model.Stock
import com.vadim.stockgenerator.model.StockPrice

interface StockService {

    fun getStock(isin: String): Stock?
    fun addStock()
    fun getNextStockEvent(): Stock?
    fun deleteStock(isin: String)
    fun updateStockState(isin: String, stock: Stock)
    fun getNextPrice(): StockPrice?
}
