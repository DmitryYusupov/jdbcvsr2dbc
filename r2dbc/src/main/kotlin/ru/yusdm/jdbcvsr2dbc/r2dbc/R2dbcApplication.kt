package ru.yusdm.jdbcvsr2dbc.r2dbc

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class R2dbcApplication

fun main(args: Array<String>) {
    SpringApplication.run(R2dbcApplication::class.java, *args)
}