package ru.yusdm.jdbcvsr2dbc.jdbc.api

import org.springframework.web.bind.annotation.*
import ru.yusdm.jdbcvsr2dbc.common.dto.CountryDTO
import ru.yusdm.jdbcvsr2dbc.jdbc.domain.Country
import ru.yusdm.jdbcvsr2dbc.jdbc.domain.toDTO
import ru.yusdm.jdbcvsr2dbc.jdbc.service.CountryService
import java.util.concurrent.atomic.AtomicInteger

@RestController
@RequestMapping(value = ["/api"])
class ApplicationRestController(private val countryService: CountryService) {

    private val counter = AtomicInteger(0)

    @GetMapping("/countries/{countryId}")
    fun getCountry(@PathVariable("countryId") countryId: Long): Country {
        return countryService.getCountryById(countryId)
    }

    @GetMapping("/countries")
    fun getCountries(@RequestParam("fetch_cities") fetchCities: Boolean): List<Country> {
        val c = counter.incrementAndGet()
        println("COUNTER " + c)
        return countryService.findAllCountries()
    }

}