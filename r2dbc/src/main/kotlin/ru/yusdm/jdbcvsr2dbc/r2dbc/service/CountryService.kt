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

    fun createCountryWithCities(): Mono<Country> {
        val country = createNewCountry()
        return countryRepository.save(country).flatMap {
            it.cities.toFlux().flatMap {
                cityRepository.save(it)
            }.then(Mono.just(it))
        }
    }

    fun createCountry(): Mono<Country> {
        return countryRepository.save(Country("New Country"))
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
        country.cities.addAll(cities)

        return country
    }

    fun getRandom(): Mono<Country> {
        return countryRepository.getAllIds().collectList().flatMap {
            countryRepository.findById(it.random())
        }
    }

    fun deleteRandom(): Mono<UUID> {
        return countryRepository.getAllIds().collectList().flatMap {
            val countryToDelete = it.random()
            countryRepository.deleteById(countryToDelete).thenReturn(countryToDelete)
        }
    }

    fun deleteRandomSelectedRow(): Mono<UUID> {
        return countryRepository.getRandomRowUid().flatMap {
            countryRepository.deleteById(it).thenReturn(it)
        }
    }

    fun updateRandom2(): Mono<Country> {
        return countryRepository.getAllIds().collectList().flatMap {
            countryRepository.findById(it.random()).flatMap { country->
                country.name = "Updated country name ${country.uid}"
                Mono.just(country)
            }.flatMap { countryRepository.save(it) }.flatMap { country->
                cityRepository.findAllByCountryUID(country.uid).collectList().map {
                    country.cities.addAll(it)
                    country
                }
            }
        }
    }

    fun updateRandom(): Mono<Void> {
        return countryRepository.getAllIds().collectList().flatMap {
            val countryIdRandom = it.random()
            countryRepository.updateName("NewName", countryIdRandom)
        }
    }

    fun updateRandomSelectedRow(): Mono<Void> {
        return countryRepository.getRandomRowUid().flatMap {
            countryRepository.updateName("NewName", it)
        }
    }

    fun callBlocking(): Mono<String> {
        return countryRepository.callBlocking()
    }

    fun getAllIds(): Flux<UUID> {
        return countryRepository.getAllIds()
    }

    fun updateRandomSelectedFromMemoryRow(countryIds: List<UUID>): Mono<Void> {
        return countryRepository.updateName("NewName", countryIds.random())
    }

    fun getRandomSelectedFromMemoryRow(countryIds: List<UUID>): Mono<Country> {
        return countryRepository.findById(countryIds.random())
    }


}