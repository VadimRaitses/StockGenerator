package com.vadim.stockgenerator.service

import com.vadim.stockgenerator.model.Message
import com.vadim.stockgenerator.model.StockSessionType


class StockThread(private val sessionService: SocketSessionService<Message>, private val stockService: StockService) : Thread() {

    override fun run() {

        while (!sessionService.isSessionListEmpty(StockSessionType.STOCK)) {
            try {
                val stock = stockService.getNextStockEvent()
                sleep(400)
                sessionService.broadCastSession(
                    Message(
                        "thread Runnable ${currentThread()}",
                        stock as Any,
                    ),
                    StockSessionType.STOCK
                )
            } catch (ex: Exception) {
                println("StockThread error: ${ex.localizedMessage}")
                println("error: ${ex.localizedMessage}")
            }
        }
        println("Stock Thread${currentThread()} stopped.")
    }

}
