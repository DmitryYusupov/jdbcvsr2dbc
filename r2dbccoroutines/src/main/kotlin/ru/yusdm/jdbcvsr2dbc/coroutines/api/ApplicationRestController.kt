package ru.yusdm.jdbcvsr2dbc.coroutines.api

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.yusdm.jdbcvsr2dbc.coroutines.domain.City
import ru.yusdm.jdbcvsr2dbc.coroutines.domain.Country
import ru.yusdm.jdbcvsr2dbc.coroutines.service.CityService
import ru.yusdm.jdbcvsr2dbc.coroutines.service.CountryService
import java.util.*


@RestController
@RequestMapping(value = ["/api"])
class ApplicationRestController(
    private val countryService: CountryService,
    private val cityService: CityService,
) {

    private var countryIds = listOf<UUID>()

    @GetMapping("/set_all_country_ids")
    fun setAllCountryIds() : Int {
        runBlocking {
            countryIds = countryService.getAllIds().toList()
        }
        return countryIds.size
    }

    @GetMapping("/countries/{countryId}")
    suspend fun getCountry(@PathVariable("countryId") countryId: UUID): Country? {
        return countryService.getCountryById(countryId)
    }

    @GetMapping("/get_country_selecting_row_from_memory")
    suspend fun getCountrySelectingRandomRowFromMemory() : Country {
        return countryService.getRandomSelectedFromMemoryRow(countryIds)
    }

    @GetMapping("/get_country")
    suspend fun getRandomCountry(): Country? {
        return countryService.getRandomCountry()
    }

    @GetMapping("/update_country")
    suspend fun updateRandomCountry() {
        countryService.updateRandom()
    }

    @GetMapping("/update_country_selecting_random_row")
    suspend fun updateCountrySelectingRandomRow() {
        countryService.updateRandomSelectedRow()
    }

    @GetMapping("/update_country_selecting_row_from_memory")
    suspend fun updateCountrySelectingRandomRowFromMemory() {
        countryService.updateRandomSelectedFromMemoryRow(countryIds)
    }

    @GetMapping("/delete_country")
    suspend fun deleteRandomCountry(): UUID {
        return countryService.deleteRandom()
    }

    @GetMapping("/delete_country_selecting_random_row")
    suspend fun deleteCountrySelectingRandomRow(): UUID {
        return countryService.deleteRandomSelectedRow()
    }

    @GetMapping("/cities/{cityId}")
    suspend fun getCity(@PathVariable("cityId") cityId: UUID): City? {
        return cityService.getById(cityId)
    }

    @GetMapping("/getall_countries")
    fun getCountries(): Flow<Country> {
        return countryService.findAllCountries()
    }

    @GetMapping("/create_country_with_cities")
    suspend fun createCountryWithCities(): Country {
        return countryService.createCountryWithCities()
    }

    @GetMapping("/create_country")
    suspend fun createCountry(): Country {
        return countryService.createCountry()
    }

    @GetMapping("/call_blocking")
    suspend fun callBlocking(): String {
        return countryService.callBlocking()
    }

}