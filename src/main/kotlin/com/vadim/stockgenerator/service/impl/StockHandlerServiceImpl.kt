package com.vadim.stockgenerator.service.impl

import com.vadim.stockgenerator.model.Message
import com.vadim.stockgenerator.model.StockSessionType
import com.vadim.stockgenerator.service.SocketSessionService
import com.vadim.stockgenerator.service.StockHandlerService
import com.vadim.stockgenerator.service.WorkerManagementService
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service
import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.WebSocketSession

@Service
@RequiredArgsConstructor
class StockHandlerServiceImpl(
    val workerManagementService: WorkerManagementService,
    val sessionService: SocketSessionService<Message>
) : StockHandlerService {

    @Synchronized
    override fun addSession(session: WebSocketSession, socketType: StockSessionType) {
        if (sessionService.isSessionListEmpty(socketType)) {
            workerManagementService.startWorker(socketType)
        }
        sessionService.addSession(session, socketType)
    }

    @Synchronized
    override fun closeSession(session: WebSocketSession, socketType: StockSessionType, status: CloseStatus) {
        sessionService.closeSession(session, status)
        if (sessionService.isSessionListEmpty(socketType)) {
            workerManagementService.stopWorker(socketType)
        }
    }
}
