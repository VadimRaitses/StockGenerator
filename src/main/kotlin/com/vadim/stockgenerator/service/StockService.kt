package com.vadim.stockgenerator.service

import com.vadim.stockgenerator.model.Stock

interface StockService {

    fun getStock(isin:String)
    fun getStocks()
    fun addStock(stock: Stock)
    fun deleteStock(isin:String)
    fun updateStockState(isin:String,stock: Stock)
}
