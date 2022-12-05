package com.vadim.stockgenerator.handler

import com.vadim.stockgenerator.model.Message
import com.vadim.stockgenerator.model.StockSessionType
import com.vadim.stockgenerator.service.SocketSessionService
import com.vadim.stockgenerator.service.StockHandlerService
import com.vadim.stockgenerator.service.StockService
import com.vadim.stockgenerator.service.StockThread
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.Subject
import lombok.AllArgsConstructor
import org.springframework.stereotype.Component
import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler

@Component("threadStockGeneratorHandler")
@AllArgsConstructor
class StockHandler(
    private val stockHandlerService: StockHandlerService
) : TextWebSocketHandler() {

    @Throws(Exception::class)
    override fun afterConnectionEstablished(session: WebSocketSession) {
        println("Welcome to Stock Handler $session : ${Thread.currentThread()}")
        stockHandlerService.addSession(session,StockSessionType.STOCK)
    }

    @Throws(Exception::class)
    override fun afterConnectionClosed(session: WebSocketSession, status: CloseStatus) {
        stockHandlerService.closeSession(session,StockSessionType.STOCK,status)
    }

    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
    }
}
