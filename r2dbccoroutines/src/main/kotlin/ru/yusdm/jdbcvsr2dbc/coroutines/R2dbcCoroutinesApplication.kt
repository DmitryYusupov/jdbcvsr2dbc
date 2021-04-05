package ru.yusdm.jdbcvsr2dbc.coroutines

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class R2dbcCoroutinesApplication

fun main(args: Array<String>) {
    SpringApplication.run(R2dbcCoroutinesApplication::class.java, *args)
}