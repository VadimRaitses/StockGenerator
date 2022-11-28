package com.vadim.stockgenerator.rest

import com.vadim.stockgenerator.model.Message
import com.vadim.stockgenerator.model.StockSocketSession
import com.vadim.stockgenerator.service.SocketSessionService
import lombok.AllArgsConstructor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/sessions")
@AllArgsConstructor
class SocketSessionsResource @Autowired constructor(private val socketSessionService : SocketSessionService<Message>) {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getSessions(): MutableCollection<StockSocketSession> {
      return socketSessionService.getSessions()
    }
}
