package com.vadim.stockgenerator.service.impl

import com.vadim.stockgenerator.model.Message
import com.vadim.stockgenerator.model.StockSessionType
import com.vadim.stockgenerator.service.*
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.Subject
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service
import java.util.concurrent.ConcurrentHashMap

@Service
@RequiredArgsConstructor
class WorkerManagementServiceImpl(
    private val stockService: StockService,
    private val sessionService: SocketSessionService<Message>
) : WorkerManagementService {

    @Volatile
    private var sessionMap = ConcurrentHashMap<StockSessionType, WorkerState>()


    override fun startWorker(stockSessionType: StockSessionType) {
        val subject : Subject<Boolean> = BehaviorSubject.create()
        when (stockSessionType) {
            StockSessionType.STOCK -> {
                sessionMap[stockSessionType] = WorkerState(StockThread(sessionService, stockService, subject), subject)
                sessionMap[stockSessionType]?.thread?.start()
            }
            StockSessionType.PRICE -> {
                sessionMap[stockSessionType] = WorkerState(PriceThread(sessionService, stockService, subject), subject)
                sessionMap[stockSessionType]?.thread?.start()
            }
        }
    }

    override fun stopWorker(stockSessionType: StockSessionType) {
        sessionMap[stockSessionType]?.subject?.onNext(false)
    }

    override fun updateWorker(stockSessionType: StockSessionType) {
        TODO("Not yet implemented")
    }

    override fun getWorkerStatus(stockSessionType: StockSessionType): Boolean? {
        return sessionMap[stockSessionType]?.thread?.isAlive
    }

    override fun getWorker(stockSessionType: StockSessionType) {
        TODO("Not yet implemented")
    }

    override fun getWorkers() {
        TODO("Not yet implemented")
    }

    class WorkerState(var thread: Thread, var subject: Subject<Boolean>)
}
