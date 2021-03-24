package ru.yusdm.jdbcvsr2dbc.jdbc.api

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.yusdm.jdbcvsr2dbc.common.dto.CountryDTO
import ru.yusdm.jdbcvsr2dbc.jdbc.domain.toDTO
import ru.yusdm.jdbcvsr2dbc.jdbc.service.CountryService

@RestController
@RequestMapping(value = ["/api"])
class ApplicationRestController(private val countryService: CountryService) {

    @GetMapping("/country/{countryId}")
    fun country(@PathVariable("countryId") countryId: Long): CountryDTO {
        println("Id")
        return countryService.getCountryById(countryId).toDTO()
    }

}