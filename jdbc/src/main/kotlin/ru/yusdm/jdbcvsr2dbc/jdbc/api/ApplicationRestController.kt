package ru.yusdm.jdbcvsr2dbc.jdbc.api

import io.micrometer.core.instrument.Counter
import io.micrometer.core.instrument.MeterRegistry
import org.springframework.web.bind.annotation.*
import ru.yusdm.jdbcvsr2dbc.jdbc.domain.City
import ru.yusdm.jdbcvsr2dbc.jdbc.domain.Country
import ru.yusdm.jdbcvsr2dbc.jdbc.service.CityService
import ru.yusdm.jdbcvsr2dbc.jdbc.service.CountryService
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

    init {
        getCountryByIdCounter = Counter.builder("getCountryByIdCounterJDBC").register(registry)
        updateCountryCounter = Counter.builder("updateCountryCounterJDBC").register(registry)
        deleteCountryCounter = Counter.builder("deleteCountryCounterJDBC").register(registry)
        getCityByIdCounter = Counter.builder("getCityByIdCounterJDBC").register(registry)
        getCountriesCounter = Counter.builder("getCountriesCounterJDBC").register(registry)
        createCountryWithCitiesCounter = Counter.builder("createCountryWithCitiesCounterJDBC").register(registry)
        createCountryCounter = Counter.builder("createCountryCounterJDBC").register(registry)
        callBlockingCounter = Counter.builder("callBlockingCounterJDBC").register(registry)
    }

    @GetMapping("/countries/{countryId}")
    fun getCountry(@PathVariable("countryId") countryId: UUID): Country {
        getCountryByIdCounter.increment()
        return countryService.getCountryById(countryId)
    }

    @GetMapping("/update_country")
    fun updateRandomCountry() {
        updateCountryCounter.increment()
         countryService.updateRandom()
    }

    @GetMapping("/delete_country")
    fun deleteRandomCountry(): UUID {
        deleteCountryCounter.increment()
        return countryService.deleteRandom()
    }

    @GetMapping("/cities/{cityId}")
    fun getCity(@PathVariable("cityId") cityId: UUID): City {
        getCityByIdCounter.increment()
        return cityService.getById(cityId)
    }

    @GetMapping("/getall_countries")
    fun getCountries(@RequestParam("fetch_cities") fetchCities: Boolean): List<Country> {
        getCountriesCounter.increment()
        return countryService.findAllCountries()
    }

    @GetMapping("/create_country_with_cities")
    fun createCountryWithCities(): Country {
        createCountryWithCitiesCounter.increment()
        return countryService.createCountryWithCities()
    }

    @GetMapping("/create_country")
    fun createCountry(): Country {
        createCountryCounter.increment()
        return countryService.createCountry()
    }

    @GetMapping("/call_blocking")
    fun callBlocking(): String {
        callBlockingCounter.increment()
        return countryService.callBlocking()
    }
}