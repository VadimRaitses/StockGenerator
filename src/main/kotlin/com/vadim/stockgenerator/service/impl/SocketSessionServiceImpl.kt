package com.vadim.stockgenerator.service.impl

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.vadim.stockgenerator.exception.SessionNotFoundException
import com.vadim.stockgenerator.model.StockSessionStatus
import com.vadim.stockgenerator.model.StockSessionType
import com.vadim.stockgenerator.model.StockSocketSession
import com.vadim.stockgenerator.service.SocketSessionService
import org.springframework.stereotype.Service
import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import java.util.concurrent.ConcurrentHashMap

@Service
class SocketSessionServiceImpl<T> : SocketSessionService<T> {

    private val sessionMap = ConcurrentHashMap<String, StockSocketSession>()


    override fun getSession(sessionId: String): WebSocketSession {
        return sessionMap[sessionId]?.socketSession ?: throw SessionNotFoundException("not found")
    }

    override fun isSessionListEmpty( socketType: StockSessionType): Boolean {
        return sessionMap.values.filter { value -> value.socketType == socketType }.isEmpty()
    }

    override fun getSessions(): MutableCollection<StockSocketSession> {
        return sessionMap.values
    }

    @Synchronized
    override fun addSession(session: WebSocketSession, socketType: StockSessionType) {
        println("add session method")
        if (!sessionMap.containsKey(session.id)) {
            sessionMap[session.id] = StockSocketSession(session.id, session, socketType, StockSessionStatus.OPEN)
        }
        sessionMap.entries.forEach { (key, value) -> println("$key:$value") }
    }

    @Synchronized
    private fun deleteSession(session: WebSocketSession) {
        println("deleting session: ${session.id}")
        sessionMap -= session.id
    }

    @Synchronized
    override fun closeSession(session: WebSocketSession, status: CloseStatus) {
        sessionMap.computeIfPresent(session.id) { _, value -> value.also { value.status = StockSessionStatus.CLOSED } }
        deleteSession(session)
    }

    private fun emit(session: StockSocketSession, msg: T) {
        try {
//            if (session.status == StockSessionStatus.CLOSED)
//                deleteSession(session.socketSession)
            if (session.socketSession.isOpen && session.status != StockSessionStatus.CLOSED)
                session.socketSession.sendMessage(TextMessage(jacksonObjectMapper().writeValueAsString(msg)))
        } catch (ex: IllegalStateException) {
            println("error: ${ex.localizedMessage}")

        }

    }

    override fun broadCastSession(msg: T, socketType: StockSessionType) = getSessions()
        .filter { e -> e.socketType == socketType }
        .forEach { e -> emit(e, msg) }

}
