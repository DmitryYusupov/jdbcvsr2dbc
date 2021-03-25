package ru.yusdm.jdbcvsr2dbc.jdbc.api

import org.springframework.web.bind.annotation.*
import ru.yusdm.jdbcvsr2dbc.common.dto.CountryDTO
import ru.yusdm.jdbcvsr2dbc.jdbc.domain.toDTO
import ru.yusdm.jdbcvsr2dbc.jdbc.service.CountryService

@RestController
@RequestMapping(value = ["/api"])
class ApplicationRestController(private val countryService: CountryService) {

    @GetMapping("/countries/{countryId}")
    fun getCountry(@PathVariable("countryId") countryId: Long): CountryDTO {
        return countryService.getCountryById(countryId).toDTO()
    }

    @GetMapping("/countries")
    fun getCountries(@RequestParam("fetch_cities") fetchCities: Boolean): List<CountryDTO> {
        return countryService.findAllCountries().map { it.toDTO(fetchCities) }
    }

}