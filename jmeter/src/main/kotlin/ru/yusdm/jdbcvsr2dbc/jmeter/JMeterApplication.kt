package ru.yusdm.jdbcvsr2dbc.jmeter

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication


@SpringBootApplication
class JMeterApplication

fun main(args: Array<String>) {
    SpringApplication.run(JMeterApplication::class.java, *args)
}