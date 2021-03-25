package ru.yusdm.jdbcvsr2dbc.jdbc.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.yusdm.jdbcvsr2dbc.jdbc.domain.Country
import ru.yusdm.jdbcvsr2dbc.jdbc.repository.CountryRepository

@Service
@Transactional
class CountryService(
    private val countryRepository: CountryRepository,
    private val cityRepository: CountryRepository
) {

    fun getCountryById(id: Long): Country {
        return countryRepository.getOne(id)
    }

    fun findAllCountries(): List<Country> {
        return countryRepository.findAll()
    }

}