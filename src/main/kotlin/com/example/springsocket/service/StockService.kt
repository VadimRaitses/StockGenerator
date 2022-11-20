package com.example.springsocket.service

import com.example.springsocket.model.Stock

interface StockService {

    fun getStock()
    fun getStocks()
    fun addStock(stock: Stock)
    fun deleteStock()
    fun updateStockState()

}
