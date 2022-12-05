package com.vadim.stockgenerator.service

import com.vadim.stockgenerator.model.Message
import com.vadim.stockgenerator.model.StockSessionType
import io.reactivex.rxjava3.subjects.Subject


class PriceThread(
    private val sessionService: SocketSessionService<Message>, private val stockService: StockService,
    private val subject: Subject<Boolean>
) : Thread() {

    @Volatile
    var isRunning: Boolean = true
    override fun run() {

        subject.subscribe { isRunning = false }
        while (isRunning) {
            try {
                sleep(100)
                val stock = stockService.getNextPrice()
                sessionService.broadCastSession(
                    Message(
                        "${currentThread()}",
                        stock as Any
                    ),
                    StockSessionType.PRICE
                )
            } catch (ex: Exception) {
                println("PriceThread error: ${ex.localizedMessage}")
            }
        }
        println("Price Thread ${currentThread()} stopped.")
    }

}
