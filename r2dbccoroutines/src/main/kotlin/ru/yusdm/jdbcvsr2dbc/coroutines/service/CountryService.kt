package ru.yusdm.jdbcvsr2dbc.coroutines.service

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.toList
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.yusdm.jdbcvsr2dbc.coroutines.domain.City
import ru.yusdm.jdbcvsr2dbc.coroutines.domain.Country
import ru.yusdm.jdbcvsr2dbc.coroutines.repository.CityRepository
import ru.yusdm.jdbcvsr2dbc.coroutines.repository.CountryRepository
import java.util.*

@Service
@Transactional
class CountryService(
    private val countryRepository: CountryRepository,
    private val cityRepository: CityRepository
) {

    suspend fun getCountryById(id: UUID): Country? {
        val country = countryRepository.findById(id)
        cityRepository.saveAll(cityRepository.findAllByCountryUID(id))

        return country
    }

    fun findAllCountries(): Flow<Country> {
        return countryRepository.findAll()
    }

    suspend fun createCountryWithCities(): Country {
        val country = createNewCountry()
        val created = countryRepository.save(country)

        return created
    }

    suspend fun createCountry(): Country {
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

    suspend fun deleteRandom(): UUID {
        val ids = countryRepository.getAllIds().toList()
        val countryToDelete = ids.random()

        countryRepository.deleteById(countryToDelete)
        return countryToDelete
    }

    suspend fun updateRandom() {
        val ids = countryRepository.getAllIds().toList()
        val countryToUpdate = ids.random()
        countryRepository.updateName("NewName", countryToUpdate)
    }

    suspend fun callBlocking(): String {
        return countryRepository.callBlocking()
    }

}