package com.vadim.stockgenerator.service

import com.vadim.stockgenerator.model.Message
import com.vadim.stockgenerator.model.StockSessionType


class PriceThread(private val sessionService: SocketSessionService<Message>, private val stockService: StockService) : Thread() {

    override fun run() {

        while (!sessionService.isSessionListEmpty(StockSessionType.PRICE)) {
            try {
                sleep(100)
                val stock = stockService.getNextPrice()
                sessionService.broadCastSession(
                    Message(
                        "thread Runnable ${currentThread()}",
                        stock as Any,

                        ),
                    StockSessionType.PRICE
                )
            } catch (ex: Exception) {
                println(" PriceThread error: ${ex.localizedMessage}")
            }
        }
        println("Price Thread ${currentThread()} stopped.")
    }

}
