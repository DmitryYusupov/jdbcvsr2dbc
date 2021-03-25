package ru.yusdm.jdbcvsr2dbc.jdbc.api

import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.*
import ru.yusdm.jdbcvsr2dbc.common.jmeter.JMeterGetRequest
import ru.yusdm.jdbcvsr2dbc.common.jmeter.JMeterTestRunner

@RestController
@RequestMapping(value = ["/api/jmeter"])
class JMeterInvokeRestController(@Value("\${server.port}") private val serverPort: Int) {

    @GetMapping("/countries/{countryId}")
    fun getCountry(@PathVariable("countryId") countryId: Long) {

        JMeterTestRunner.runSuiteForGetRequest(
            JMeterGetRequest(
                host = "localhost",
                port = serverPort,
                numThreads = 2,
                path = "/api/countries",
                params = mapOf("fetch_cities" to "false")
            )
        )

    }

    @GetMapping("/countries")
    fun getCountries(
        @RequestParam("fetch_cities") fetchCities: Boolean,
        @RequestParam("num_threads") numThreads: Int
    ) {
        JMeterTestRunner.runSuiteForGetRequest(
            JMeterGetRequest(
                host = "localhost",
                port = serverPort,
                numThreads = numThreads,
                path = "/api/countries",
                params = mapOf("fetch_cities" to "false")
            )
        )
    }
}