package com.vadim.stockgenerator.utils

import java.util.concurrent.ThreadLocalRandom
import kotlin.streams.asSequence

object StockNameGenerator {

    val STRING_LENGTH = 12

    val charPool: List<Char> = ('A'..'Z') + ('a'..'z') + ('0'..'9')

    fun randomStringByJavaRandom() = ThreadLocalRandom.current()
        .ints(STRING_LENGTH.toLong(), 0, charPool.size)
        .asSequence()
        .map(charPool::get)
        .joinToString("")
}
