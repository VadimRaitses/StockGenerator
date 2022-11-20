package com.vadim.stockgenerator.service

import com.vadim.stockgenerator.model.Message
import com.vadim.stockgenerator.model.Stock
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import kotlin.random.Random

class StockRunnableService(private val sessions: ArrayList<WebSocketSession>, private val json: List<Stock>) : Runnable {


    private fun emit(session: WebSocketSession, msg: Message) {
        if (session.isOpen)
            session.sendMessage(TextMessage(jacksonObjectMapper().writeValueAsString(msg)))
    }

    private fun broadcast(msg: Message) = sessions.forEach { emit(it, msg) }

    override fun run() {
        while (sessions.isNotEmpty()) {
            val stock = json[json.size.mod(Random(1000).nextInt())]
            broadcast(Message("thread Runnable", stock, Random.nextDouble(from = 0.1, until = 100.0)))
        }
    }
}
