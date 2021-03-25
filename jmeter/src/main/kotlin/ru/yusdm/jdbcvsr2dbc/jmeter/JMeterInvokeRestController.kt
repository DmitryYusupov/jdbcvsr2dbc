package ru.yusdm.jdbcvsr2dbc.jmeter

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/api/jmeter"])
class JMeterInvokeRestController() {

    @GetMapping("/jdbc/countries")
    fun getCountriesWithJdbc(
        @RequestParam("fetch_cities") fetchCities: Boolean,
        @RequestParam("num_threads") numThreads: Int
    ) {
        JMeterTestRunner.runSuiteForGetRequest(
            JMeterGetRequest(
                host = "localhost",
                port = 8081,
                numThreads = numThreads,
                path = "/api/countries",
                params = mapOf("fetch_cities" to "false")
            )
        )
    }

    @GetMapping("/r2dbc/countries")
    fun getCountriesWithR2Dbc(
        @RequestParam("fetch_cities") fetchCities: Boolean,
        @RequestParam("num_threads") numThreads: Int
    ) {
        JMeterTestRunner.runSuiteForGetRequest(
            JMeterGetRequest(
                host = "localhost",
                port = 8082,
                numThreads = numThreads,
                path = "/api/countries",
                params = mapOf("fetch_cities" to "false")
            )
        )
    }
}