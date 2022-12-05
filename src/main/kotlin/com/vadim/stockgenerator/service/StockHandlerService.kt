package com.vadim.stockgenerator.service

import com.vadim.stockgenerator.model.StockSessionType
import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.WebSocketSession

interface StockHandlerService {

    fun addSession(session: WebSocketSession, socketType: StockSessionType)
    fun closeSession(session: WebSocketSession, socketType: StockSessionType, status: CloseStatus)
}
