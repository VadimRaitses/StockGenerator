package com.vadim.stockgenerator.socket

import com.vadim.stockgenerator.model.Message
import com.vadim.stockgenerator.model.StockSessionType
import com.vadim.stockgenerator.service.PriceThread
import com.vadim.stockgenerator.service.SocketSessionService
import com.vadim.stockgenerator.service.StockService
import com.vadim.stockgenerator.service.StockThread
import lombok.AllArgsConstructor
import org.springframework.stereotype.Component
import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler

@Component("threadPriceGeneratorHandler")
@AllArgsConstructor
class PriceHandler(
    private val stockService: StockService,
    private val sessionService: SocketSessionService<Message>
) : TextWebSocketHandler() {

    private lateinit var stock: PriceThread

    @Throws(Exception::class)
    override fun afterConnectionEstablished(session: WebSocketSession) {
        println("welcome to price thraed $session,${sessionService.getSessions()} : ${Thread.currentThread()}")
        if (sessionService.isSessionListEmpty(StockSessionType.PRICE)) {
            sessionService.addSession(session,StockSessionType.PRICE)
            stock = PriceThread(sessionService, stockService)
            stock.start();
        }
        println("thread object memory  $stock, ${Thread.currentThread()}")
        sessionService.addSession(session,StockSessionType.PRICE)
    }

    @Throws(Exception::class)
    override fun afterConnectionClosed(session: WebSocketSession, status: CloseStatus) {
        sessionService.closeSession(session, status)
    }

    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
    }
}
