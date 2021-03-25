package ru.yusdm.jdbcvsr2dbc.r2dbc.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import ru.yusdm.jdbcvsr2dbc.r2dbc.domain.Country
import ru.yusdm.jdbcvsr2dbc.r2dbc.repository.CountryRepository

@Service
@Transactional
class CountryService(
    private val countryRepository: CountryRepository,
) {

    fun getCountryById(id: Long): Mono<Country> {
        return countryRepository.findById(id)
    }

    fun findAllCountries(): Flux<Country> {
        return countryRepository.findAll()
    }

}