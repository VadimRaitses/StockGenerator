package com.vadim.stockgenerator.service

import com.vadim.stockgenerator.model.Stock

interface StockService {

    fun getStock()
    fun getStocks()
    fun addStock(stock: Stock)
    fun deleteStock()
    fun updateStockState()

}
