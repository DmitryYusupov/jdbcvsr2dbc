package ru.yusdm.jdbcvsr2dbc.r2dbc.api

import io.micrometer.core.instrument.Counter
import io.micrometer.core.instrument.MeterRegistry
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import ru.yusdm.jdbcvsr2dbc.r2dbc.domain.City
import ru.yusdm.jdbcvsr2dbc.r2dbc.domain.Country
import ru.yusdm.jdbcvsr2dbc.r2dbc.service.CityService
import ru.yusdm.jdbcvsr2dbc.r2dbc.service.CountryService
import java.util.*


@RestController
@RequestMapping(value = ["/api"])
class ApplicationRestController(
    private val countryService: CountryService,
    private val cityService: CityService,
    private val registry: MeterRegistry
) {

    private val getCountryByIdCounter: Counter
    private val updateCountryCounter: Counter
    private val deleteCountryCounter: Counter
    private val getCityByIdCounter: Counter
    private val getCountriesCounter: Counter
    private val createCountryWithCitiesCounter: Counter
    private val createCountryCounter: Counter
    private val callBlockingCounter: Counter
    private var countryIds = listOf<UUID>()

    init {
        getCountryByIdCounter = Counter.builder("getCountryByIdCounterR2DBC").register(registry)
        updateCountryCounter = Counter.builder("updateCountryCounterR2DBC").register(registry)
        deleteCountryCounter = Counter.builder("deleteCountryCounterR2DBC").register(registry)
        getCityByIdCounter = Counter.builder("getCityByIdCounterR2DBC").register(registry)
        getCountriesCounter = Counter.builder("getCountriesCounterR2DBC").register(registry)
        createCountryWithCitiesCounter = Counter.builder("createCountryWithCitiesCounterR2DBC").register(registry)
        createCountryCounter = Counter.builder("createCountryCounterR2DBC").register(registry)
        callBlockingCounter = Counter.builder("callBlockingCounterR2DBC").register(registry)
    }

    @GetMapping("/set_all_country_ids")
    fun setAllCountryIds() =  {
      /*  countryService.getAllIds().collectList().map {
            println("AAA")
            countryIds = it
        }*/
    }

    @GetMapping("/countries/{countryId}")
    fun getCountry(@PathVariable("countryId") countryId: UUID): Mono<Country> {
        // getCityByIdCounter.increment()
        return countryService.getCountryById(countryId)
    }

    @GetMapping("/update_country")
    fun updateRandomCountry(): Mono<Void> {
        // updateCountryCounter.increment()
        return countryService.updateRandom()
    }

    @GetMapping("/update_country_selecting_random_row")
    fun updateCountrySelectingRandomRow(): Mono<Void> {
        return countryService.updateRandomSelectedRow()
    }

    @GetMapping("/update_country_selecting_row_from_memory")
    fun updateCountrySelectingRandomRowFromMemory(): Mono<Void> {
        return countryService.updateRandomSelectedFromMemoryRow(countryIds)
    }

    @GetMapping("/get_country")
    fun getRandomCountry(): Mono<Country> {
        // updateCountryCounter.increment()
        return countryService.getRandom()
    }

    @GetMapping("/get_country_selecting_row_from_memory")
    fun getCountrySelectingRandomRowFromMemory() : Mono<Country> {
        return countryService.getRandomSelectedFromMemoryRow(countryIds)
    }

    @GetMapping("/delete_country")
    fun deleteRandomCountry(): Mono<UUID> {
        //   deleteCountryCounter.increment()
        return countryService.deleteRandom()
    }

    @GetMapping("/delete_country_selecting_random_row")
    fun deleteCountrySelectingRandomRow(): Mono<UUID> {
        return countryService.deleteRandomSelectedRow()
    }

    @GetMapping("/cities/{cityId}")
    fun getCity(@PathVariable("cityId") cityId: UUID): Mono<City> {
        //  getCityByIdCounter.increment()
        return cityService.getById(cityId)
    }

    @GetMapping("/getall_countries")
    fun getCountries(): Flux<Country> {
        //  getCountriesCounter.increment()
        return countryService.findAllCountries()
    }

    @GetMapping("/create_country_with_cities")
    fun createCountryWithCities(): Mono<Country> {
        //   createCountryCounter.increment()
        return countryService.createCountryWithCities()
    }

    @GetMapping("/create_country")
    fun createCountry(): Mono<Country> {
        // println("create")
        //createCountryCounter.increment()
        return countryService.createCountry()
    }

    @GetMapping("/call_blocking")
    fun callBlocking(): Mono<String> {
        //println("Blocking")
        //  callBlockingCounter.increment()
        return countryService.callBlocking()
    }

}