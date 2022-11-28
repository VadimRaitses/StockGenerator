package com.vadim.stockgenerator.service

import com.vadim.stockgenerator.model.StockSessionType
import com.vadim.stockgenerator.model.StockSocketSession
import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.WebSocketSession

interface SocketSessionService<T> {

    fun getSession(sessionId: String): WebSocketSession
    fun isSessionListEmpty(socketType: StockSessionType): Boolean
    fun getSessions(): MutableCollection<StockSocketSession>
    fun addSession(session: WebSocketSession, socketType: StockSessionType)
    fun closeSession(session: WebSocketSession, status: CloseStatus)
    fun broadCastSession(msg: T, socketType: StockSessionType)

}
