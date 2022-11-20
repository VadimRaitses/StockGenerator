package com.example.springsocket.socket

import com.example.springsocket.model.Message
import com.example.springsocket.model.Stock
import com.example.springsocket.model.StockPrice
import com.example.springsocket.service.StockService
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.observables.ConnectableObservable
import io.reactivex.rxjava3.schedulers.Schedulers
import lombok.AllArgsConstructor
import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger
import kotlin.random.Random


@AllArgsConstructor
class StockRxHandler(private val stockService: StockService, private val json: List<Stock>) : TextWebSocketHandler() {
    private var stockIndex = AtomicInteger(1)

    private var observableTrigger: ConnectableObservable<StockPrice> =
        ConnectableObservable
            .interval(100, TimeUnit.MILLISECONDS)
            .map {
                if (stockIndex.get() == Int.MAX_VALUE)
                    stockIndex = AtomicInteger(1)
                stockIndex.getAndIncrement()
                StockPrice(stockIndex.get(), Random.nextDouble(from = 0.1, until = 100.0))
            }
            .observeOn(Schedulers.io())
            .doOnDispose { println("Disposed") }
            .publish()

    private val sessionMapList = HashMap<WebSocketSession, Disposable>(0)


    @Throws(Exception::class)
    override fun afterConnectionEstablished(session: WebSocketSession) {
        observableTrigger.connect()
        val disposable: Disposable = observableTrigger.subscribe { item: StockPrice ->
            val stock = json[json.size.mod(item.index)]
            println("$item : $stock")
            //broadcast(Message("java-rx", stock,item.price))
            emit(session,Message("java-rx", stock,item.price))
        }
        sessionMapList[session] = disposable
        println("$session,$sessionMapList")

    }

    @Throws(Exception::class)
    override fun afterConnectionClosed(session: WebSocketSession, status: CloseStatus) {
        val obj = sessionMapList[session]
        obj?.dispose()
        sessionMapList -= session
        println("afterConnectionClosed ,$sessionMapList")
    }

    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
    }

    private fun emit(session: WebSocketSession, msg: Message) {
        session.sendMessage(TextMessage(jacksonObjectMapper().writeValueAsString(msg)))
    }

    private fun broadcast(msg: Message) = sessionMapList.forEach { emit(it.key, msg) }


}
