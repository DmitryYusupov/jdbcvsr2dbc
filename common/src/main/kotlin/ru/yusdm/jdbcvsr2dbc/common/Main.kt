package ru.yusdm.jdbcvsr2dbc.common

import ru.yusdm.jdbcvsr2dbc.common.jmeter.JMeterGetRequest
import ru.yusdm.jdbcvsr2dbc.common.jmeter.JMeterTestRunner

class Main

fun main(args: Array<String>) {

    JMeterTestRunner.runSuiteForGetRequest(
        JMeterGetRequest(
            host = "localhost",
            port = 8080,
            numThreads = 2,
            path = "/api/countries",
            params = mapOf("fetch_cities" to "false")
        )
    )
}