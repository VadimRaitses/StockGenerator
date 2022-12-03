package com.vadim.stockgenerator.service.impl

import com.vadim.stockgenerator.model.Stock
import com.vadim.stockgenerator.model.StockPrice
import com.vadim.stockgenerator.model.StockStatus
import com.vadim.stockgenerator.service.StockService
import com.vadim.stockgenerator.utils.Lorem
import com.vadim.stockgenerator.utils.StockNameGenerator.randomStringByJavaRandom
import org.springframework.stereotype.Service
import java.util.concurrent.ConcurrentHashMap
import kotlin.random.Random


@Service
class StockServiceImpl : StockService {

    private val stockBank = ConcurrentHashMap<String, Stock>()

    init {
        for (i in 1..100) {
            val isin = randomStringByJavaRandom().uppercase()
            stockBank[isin] = Stock(isin, Lorem.sentence(3), StockStatus.ADD)
        }
    }

    override fun getStock(isin: String): Stock? {
        return stockBank[isin]
    }

    override fun getStocks(): Collection<Stock> {
        return stockBank.values
    }

    @Synchronized
    override fun getNextStockEvent(): Stock? {
        return if (Random.nextBoolean())
            getStocks().elementAt(Random.nextInt(stockBank.values.size))
        else {
           return if(Random.nextBoolean()) addStock() else deleteStock()
        }
    }

    private fun deleteStock(): Stock {
        var stock: Stock = stockBank.values.elementAt(Random.nextInt(stockBank.values.size))
        stock.stockStatus = StockStatus.DELETE
        stockBank -= stock.isin
        return stock
    }

    override fun addStock() : Stock? {
        val isin = randomStringByJavaRandom()
        stockBank[isin] = Stock(isin, Lorem.sentence(3), StockStatus.ADD)
        return  stockBank[isin]
    }

    override fun deleteStock(isin: String) {
        stockBank -= isin
    }

    override fun updateStockState(isin: String, stock: Stock) {
        stockBank[stock.isin] = stock
    }

    @Synchronized
    override fun getNextPrice(): StockPrice? {
        val nextInt = Random.nextInt(stockBank.size)
        if(nextInt>=0) {
            val stock: Stock = stockBank.values.elementAt(nextInt)
            return StockPrice(0, stock.isin, Random.nextDouble(from = 0.1, until = 100.0))
        }
        return  StockPrice(0, "", 0.0)
    }


}
