package com.vadim.stockgenerator.socket

import com.vadim.stockgenerator.model.Message
import com.vadim.stockgenerator.model.StockSessionType
import com.vadim.stockgenerator.service.SocketSessionService
import com.vadim.stockgenerator.service.StockService
import com.vadim.stockgenerator.service.StockThread
import lombok.AllArgsConstructor
import org.springframework.stereotype.Component
import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler

@Component("threadStockGeneratorHandler")
@AllArgsConstructor
class StockHandler(
    private val stockService: StockService,
    private val sessionService: SocketSessionService<Message>
) : TextWebSocketHandler() {

    private lateinit var stockThread: StockThread


    @Throws(Exception::class)
    override fun afterConnectionEstablished(session: WebSocketSession) {
        println("welcome to Stock Handler $session,${sessionService.getSessions()} : ${Thread.currentThread()}")
        if (sessionService.isSessionListEmpty(StockSessionType.STOCK)) {
            sessionService.addSession(session, StockSessionType.STOCK)
            stockThread = StockThread(sessionService, stockService)
            stockThread.start();
        }
        println("thread object memory  $stockThread, ${Thread.currentThread()}")
        sessionService.addSession(session, StockSessionType.STOCK)
    }

    @Throws(Exception::class)
    override fun afterConnectionClosed(session: WebSocketSession, status: CloseStatus) {
        sessionService.closeSession(session, status)
    }

    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
    }
}
