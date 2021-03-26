package ru.yusdm.jdbcvsr2dbc.r2dbc.service

import kotlinx.coroutines.reactor.flux
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toFlux
import reactor.kotlin.core.publisher.toMono
import ru.yusdm.jdbcvsr2dbc.r2dbc.domain.City
import ru.yusdm.jdbcvsr2dbc.r2dbc.domain.Country
import ru.yusdm.jdbcvsr2dbc.r2dbc.repository.CityRepository
import ru.yusdm.jdbcvsr2dbc.r2dbc.repository.CountryRepository
import java.util.*

@Service
@Transactional
class CountryService(
    private val countryRepository: CountryRepository,
    private val cityRepository: CityRepository
) {

    fun getCountryById(id: UUID): Mono<Country> {
        return Mono.zip(
            countryRepository.findById(id),
            cityRepository.findAllByCountryUID(id).collectList()
        ) { country, cities ->
            country.cities.addAll(cities)

            country
        }
    }

    fun findAllCountries(): Flux<Country> {
        return countryRepository.findAll()
    }

    fun createCountry(): Mono<Country> {
        val country = createNewCountry()
        return countryRepository.save(country).flatMap {
            it.cities.toFlux().flatMap {
                cityRepository.save(it)
            }.then(Mono.just(it))
        }
    }

    private fun createNewCountry(): Country {
        val country = Country("New Country")
        val countryId = country.id
        val cities = listOf(
            City(countryId, "City_1"),
            City(countryId, "City_2"),
            City(countryId, "City_3"),
            City(countryId, "City_4"),
        )
        country.cities.addAll(cities);

        return country
    }

}