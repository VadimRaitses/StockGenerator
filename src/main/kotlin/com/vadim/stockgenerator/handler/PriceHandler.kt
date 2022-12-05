package com.vadim.stockgenerator.handler

import com.vadim.stockgenerator.model.StockSessionType
import com.vadim.stockgenerator.service.StockHandlerService
import lombok.AllArgsConstructor
import org.springframework.stereotype.Component
import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler

@Component("threadPriceGeneratorHandler")
@AllArgsConstructor
class PriceHandler(
    private val stockHandlerService: StockHandlerService
) : TextWebSocketHandler() {

    @Throws(Exception::class)
    override fun afterConnectionEstablished(session: WebSocketSession) {
        println("welcome to Price Handler  $session : ${Thread.currentThread()}")
        stockHandlerService.addSession(session, StockSessionType.PRICE)
    }

    @Throws(Exception::class)
    override fun afterConnectionClosed(session: WebSocketSession, status: CloseStatus) {
        stockHandlerService.closeSession(session, StockSessionType.PRICE, status)

    }

    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
    }
}
