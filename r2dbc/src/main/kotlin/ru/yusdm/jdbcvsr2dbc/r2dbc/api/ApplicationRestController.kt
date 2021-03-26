package ru.yusdm.jdbcvsr2dbc.r2dbc.api

import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import ru.yusdm.jdbcvsr2dbc.common.dto.CountryDTO
import ru.yusdm.jdbcvsr2dbc.r2dbc.domain.City
import ru.yusdm.jdbcvsr2dbc.r2dbc.domain.Country
import ru.yusdm.jdbcvsr2dbc.r2dbc.domain.toDTO
import ru.yusdm.jdbcvsr2dbc.r2dbc.service.CityService
import ru.yusdm.jdbcvsr2dbc.r2dbc.service.CountryService
import java.util.*
import java.util.concurrent.atomic.AtomicInteger

@RestController
@RequestMapping(value = ["/api"])
class ApplicationRestController(
    private val countryService: CountryService,
    private val cityService: CityService
    ) {

    private val counter = AtomicInteger(0)

    @GetMapping("/countries/{countryId}")
    fun getCountry(@PathVariable("countryId") countryId: UUID): Mono<Country> {
        return countryService.getCountryById(countryId)
    }

    @PutMapping("/countries")
    fun updateRandomCountry(): Mono<Country> {
        return countryService.updateRandom()
    }

    @DeleteMapping("/countries")
    fun deleteRandomCountry(): Mono<UUID> {
        return countryService.deleteRandom()
    }

    @GetMapping("/cities/{cityId}")
    fun getCity(@PathVariable("cityId") cityId: UUID): Mono<City> {
        return cityService.getById(cityId)
    }

    @GetMapping("/countries")
    fun getCountries(@RequestParam("fetch_cities") fetchCities: Boolean): Flux<Country> {
        return countryService.findAllCountries().doOnComplete { counter.incrementAndGet() }
    }

    @PostMapping("/countries")
    fun createCountry(): Mono<Country> {
        return countryService.createCountry()
    }

}