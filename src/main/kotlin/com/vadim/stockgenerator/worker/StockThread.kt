package com.vadim.stockgenerator.worker

import com.vadim.stockgenerator.model.Message
import com.vadim.stockgenerator.model.StockSessionType
import com.vadim.stockgenerator.service.SocketSessionService
import com.vadim.stockgenerator.service.StockService
import io.reactivex.rxjava3.subjects.Subject


class StockThread(
    private val sessionService: SocketSessionService<Message>,
    private val stockService: StockService,
    private val subject: Subject<Boolean>
) : Thread() {

    @Volatile
    var isRunning: Boolean = true
    override fun run() {

        subject.subscribe { isRunning = false }
        while (isRunning) {
            try {
                sleep(400)
                val stock = stockService.getNextStockEvent()
                sessionService.broadCastSession(
                    Message(
                        "${currentThread()}",
                        stock as Any
                    ),
                    StockSessionType.STOCK
                )
            } catch (ex: Exception) {
                println("StockThread error: ${ex.localizedMessage}")
            }
        }
        println("Stock Thread:${currentThread()} had stopped.")
    }

}
