package com.vadim.stockgenerator.socket

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.vadim.stockgenerator.model.Message
import com.vadim.stockgenerator.model.Stock
import com.vadim.stockgenerator.service.StockRunnableService
import com.vadim.stockgenerator.service.StockService
import lombok.AllArgsConstructor
import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler


@AllArgsConstructor
class StockHandler(private val stockService: StockService, private val json: List<Stock>) : TextWebSocketHandler() {

    private val sessionList = ArrayList<WebSocketSession>(0)
    private lateinit var threadWithRunnable: Thread


    @Throws(Exception::class)
    override fun afterConnectionEstablished(session: WebSocketSession) {
        println("$session,$sessionList")
        if (sessionList.size == 0) {
            threadWithRunnable = Thread(StockRunnableService(sessionList, json))
            threadWithRunnable.start();
        }
        sessionList += session
        println("after observable")

    }

    @Throws(Exception::class)
    override fun afterConnectionClosed(session: WebSocketSession, status: CloseStatus) {
        println("afterConnectionClosed ,$sessionList")
        sessionList -= session
    }

    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
    }

    private fun emit(session: WebSocketSession, msg: Message) = session.sendMessage(TextMessage(jacksonObjectMapper().writeValueAsString(msg)))
    private fun broadcast(msg: Message) = sessionList.forEach { emit(it, msg) }
    private fun broadcastToOthers(me: WebSocketSession, msg: Message) = sessionList.filterNot { it == me }.forEach { emit(it, msg) }
}
