package ru.yusdm.jdbcvsr2dbc.jdbc.api

import org.springframework.web.bind.annotation.*
import ru.yusdm.jdbcvsr2dbc.common.dto.CountryDTO
import ru.yusdm.jdbcvsr2dbc.jdbc.domain.City
import ru.yusdm.jdbcvsr2dbc.jdbc.domain.Country
import ru.yusdm.jdbcvsr2dbc.jdbc.domain.toDTO
import ru.yusdm.jdbcvsr2dbc.jdbc.service.CityService
import ru.yusdm.jdbcvsr2dbc.jdbc.service.CountryService
import java.util.*
import java.util.concurrent.atomic.AtomicInteger

@RestController
@RequestMapping(value = ["/api"])
class ApplicationRestController(
    private val countryService: CountryService,
    private val cityService: CityService
) {

    private val counter = AtomicInteger(0)

    @GetMapping("/cities/{cityId}")
    fun getCity(@PathVariable("cityId") cityId: UUID): City {
        return cityService.getById(cityId)
    }

    @GetMapping("/countries/{countryId}")
    fun getCountry(@PathVariable("countryId") countryId: UUID): Country {
        return countryService.getCountryById(countryId)
    }

    @PostMapping("/countries")
    fun createCountry(): Country {
        return countryService.createCountry()
    }

    @GetMapping("/countries")
    fun getCountries(@RequestParam("fetch_cities") fetchCities: Boolean): List<Country> {
        val c = counter.incrementAndGet()
        println("COUNTER " + c)
        return countryService.findAllCountries()
    }

}