package com.vadim.stockgenerator.service

import com.vadim.stockgenerator.model.StockSessionType

interface WorkerManagementService {

    fun startWorker(stockSessionType: StockSessionType)
    fun stopWorker(stockSessionType: StockSessionType)
    fun updateWorker(stockSessionType: StockSessionType)
    fun getWorkerStatus(stockSessionType: StockSessionType):Boolean?
    fun getWorker(stockSessionType: StockSessionType)
    fun getWorkers()

}
