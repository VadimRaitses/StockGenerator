package com.vadim.stockgenerator.model

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.web.socket.WebSocketSession

class StockSocketSession( val id: String,
                         @JsonIgnore val socketSession: WebSocketSession,
                         val socketType:StockSessionType,
                         var status: StockSessionStatus) {
    override fun toString(): String {
        return "StockSocketSession(id='$id', socketSession=$socketSession, status=$status)"
    }
}

