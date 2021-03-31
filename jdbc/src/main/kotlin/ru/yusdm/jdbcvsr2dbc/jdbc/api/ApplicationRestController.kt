package ru.yusdm.jdbcvsr2dbc.jdbc.api

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
    private val cityService: CityService
) {

    @GetMapping("/cities/{cityId}")
    fun getCity(@PathVariable("cityId") cityId: UUID): City {
        return cityService.getById(cityId)
    }

    @GetMapping("/countries/{countryId}")
    fun getCountry(@PathVariable("countryId") countryId: UUID): Country {
        return countryService.getCountryById(countryId)
    }

    @DeleteMapping("/countries")
    fun deleteRandomCountry(): UUID {
        return countryService.deleteRandom()
    }

    @PutMapping("/countries")
    fun updateRandomCountry() {
        countryService.updateRandom()
    }

    @PostMapping("/countries_with_cities")
    fun createCountryWithCities(): Country {
        return countryService.createCountryWithCities()
    }

    @PostMapping("/countries")
    fun createCountry(): Country {
        return countryService.createCountry()
    }

    @GetMapping("/countries")
    fun getCountries(@RequestParam("fetch_cities") fetchCities: Boolean): List<Country> {
        return countryService.findAllCountries()
    }

    @GetMapping("/call_blocking")
    fun callBlocking(): String {
        return countryService.callBlocking()
    }

    /**
     * CREATE OR REPLACE FUNCTION blocking_test() RETURNS text AS $$
    BEGIN
    PERFORM pg_sleep(2);
    RETURN 'test';
    END;
    $$ LANGUAGE plpgsql;

    select blocking_test();
     */

}