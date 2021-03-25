package ru.yusdm.jdbcvsr2dbc.r2dbc.api

import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import ru.yusdm.jdbcvsr2dbc.common.dto.CountryDTO
import ru.yusdm.jdbcvsr2dbc.r2dbc.domain.Country
import ru.yusdm.jdbcvsr2dbc.r2dbc.domain.toDTO
import ru.yusdm.jdbcvsr2dbc.r2dbc.service.CountryService
import java.util.concurrent.atomic.AtomicInteger

@RestController
@RequestMapping(value = ["/api"])
class ApplicationRestController(private val countryService: CountryService) {

    private val counter = AtomicInteger(0)

    @GetMapping("/countries/{countryId}")
    fun getCountry(@PathVariable("countryId") countryId: Long): Mono<Country> {
        println(counter)
        return countryService.getCountryById(countryId)
    }

    @GetMapping("/countries")
    fun getCountries(@RequestParam("fetch_cities") fetchCities: Boolean): Flux<Country> {
        return countryService.findAllCountries().doOnComplete { counter.incrementAndGet() }

    }

}