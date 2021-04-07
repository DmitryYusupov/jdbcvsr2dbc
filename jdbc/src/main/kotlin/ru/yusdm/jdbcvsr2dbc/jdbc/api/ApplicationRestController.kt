package ru.yusdm.jdbcvsr2dbc.jdbc.api

import io.micrometer.core.instrument.Counter
import io.micrometer.core.instrument.MeterRegistry
import org.springframework.web.bind.annotation.*
import ru.yusdm.jdbcvsr2dbc.jdbc.domain.City
import ru.yusdm.jdbcvsr2dbc.jdbc.domain.Country
import ru.yusdm.jdbcvsr2dbc.jdbc.service.CityService
import ru.yusdm.jdbcvsr2dbc.jdbc.service.CountryService
import java.util.*
import java.util.concurrent.atomic.AtomicInteger

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
        getCountryByIdCounter = Counter.builder("getCountryByIdCounterJDBC").register(registry)
        updateCountryCounter = Counter.builder("updateCountryCounterJDBC").register(registry)
        deleteCountryCounter = Counter.builder("deleteCountryCounterJDBC").register(registry)
        getCityByIdCounter = Counter.builder("getCityByIdCounterJDBC").register(registry)
        getCountriesCounter = Counter.builder("getCountriesCounterJDBC").register(registry)
        createCountryWithCitiesCounter = Counter.builder("createCountryWithCitiesCounterJDBC").register(registry)
        createCountryCounter = Counter.builder("createCountryCounterJDBC").register(registry)
        callBlockingCounter = Counter.builder("callBlockingCounterJDBC").register(registry)
    }

    @GetMapping("/set_all_country_ids")
    fun setAllCountryIds(): Int {
        countryIds = countryService.getAllIds()
        return countryIds.size
    }

    @GetMapping("/get_country")
    fun getRandomCountry(): Country {
        // updateCountryCounter.increment()
        return countryService.getRandom()
    }

    @GetMapping("/get_country_selecting_row_from_memory")
    fun getCountrySelectingRandomRowFromMemory() : Country {
        //countryIds contains all country ids
        return countryService.getRandomSelectedFromMemoryRow(countryIds)
    }

    @GetMapping("/countries/{countryId}")
    fun getCountry(@PathVariable("countryId") countryId: UUID): Country {
       // getCountryByIdCounter.increment()
        return countryService.getCountryById(countryId)
    }

    @GetMapping("/update_country")
    fun updateRandomCountry() {
       // updateCountryCounter.increment()
         countryService.updateRandom()
    }

    @GetMapping("/update_country_selecting_row_from_memory")
    fun updateCountrySelectingRandomRowFromMemory() {
         countryService.updateRandomSelectedFromMemoryRow(countryIds)
    }

    @GetMapping("/update_country_selecting_random_row")
    fun updateCountrySelectingRandomRow() {
        countryService.updateRandomSelectedRow()
    }

    @GetMapping("/delete_country")
    fun deleteRandomCountry(): UUID {
    //    deleteCountryCounter.increment()
        return countryService.deleteRandom()
    }

    @GetMapping("/delete_country_selecting_row_from_memory")
    fun deleteCountrySelectingRandomRowFromMemory() {
        countryService.deleteRandomSelectedFromMemoryRow(countryIds)
    }

    @GetMapping("/delete_country_selecting_random_row")
    fun deleteCountrySelectingRandomRow(): UUID {
        return countryService.deleteRandomSelectedRow()
    }

    @GetMapping("/cities/{cityId}")
    fun getCity(@PathVariable("cityId") cityId: UUID): City {
       // getCityByIdCounter.increment()
        return cityService.getById(cityId)
    }

    @GetMapping("/getall_countries")
    fun getCountries(): List<Country> {
       // getCountriesCounter.increment()
        return countryService.findAllCountries()
    }

    @GetMapping("/create_country_with_cities")
    fun createCountryWithCities(): Country {
      //  createCountryWithCitiesCounter.increment()
        return countryService.createCountryWithCities()
    }

    @GetMapping("/create_country")
    fun createCountry(): Country {
       // println("create")
        //createCountryCounter.increment()
        return countryService.createCountry()
    }

    @GetMapping("/create_country_calling_blocking_function")
    fun createCountryCallingBlockingFunction(): Country {
        return countryService.createCountryCallingBlockingFunction()
    }

    @GetMapping("/call_blocking")
    fun callBlocking(): String {
       // println("Blocking")
       // callBlockingCounter.increment()
        return countryService.callBlocking()
    }
}