package ru.yusdm.jdbcvsr2dbc.coroutines.api

import kotlinx.coroutines.flow.Flow
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

    @GetMapping("/countries/{countryId}")
    suspend fun getCountry(@PathVariable("countryId") countryId: UUID): Country? {
        return countryService.getCountryById(countryId)
    }

    @GetMapping("/update_country")
    suspend fun updateRandomCountry() {
        countryService.updateRandom()
    }

    @GetMapping("/delete_country")
    suspend fun deleteRandomCountry(): UUID {
        return countryService.deleteRandom()
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