package ru.yusdm.jdbcvsr2dbc.jdbc.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.yusdm.jdbcvsr2dbc.jdbc.domain.City
import ru.yusdm.jdbcvsr2dbc.jdbc.domain.Country
import ru.yusdm.jdbcvsr2dbc.jdbc.repository.CountryRepository

@Service
@Transactional
class CountryService(
    private val countryRepository: CountryRepository,
) {

    fun findAllCountries(): List<Country> {
        return countryRepository.findAll()
    }

    fun createCountry(): Country {
        return countryRepository.save(createNewCountry())
    }

    fun deleteRandom() {

    }

    private fun createNewCountry(): Country {
        val country = Country("New Country", mutableListOf())
        val cities = listOf(
            City("City_1", country),
            City("City_2", country),
            City("City_3", country),
            City("City_4", country),
        )
       country.cities.addAll(cities);

       return country
    }

}