package com.example.springsocket

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringSocketApplication

fun main(args: Array<String>) {
    runApplication<SpringSocketApplication>(*args)
}
