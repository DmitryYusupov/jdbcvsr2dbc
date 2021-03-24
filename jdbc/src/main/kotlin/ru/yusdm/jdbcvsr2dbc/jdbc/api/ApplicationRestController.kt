package ru.yusdm.jdbcvsr2dbc.jdbc.api

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.yusdm.jdbcvsr2dbc.jdbc.service.CountryService

@RestController
@RequestMapping(value = ["/api"])
class ApplicationRestController(private val countryService: CountryService) {

    @GetMapping("/insert10")
    fun test() {

    }

}