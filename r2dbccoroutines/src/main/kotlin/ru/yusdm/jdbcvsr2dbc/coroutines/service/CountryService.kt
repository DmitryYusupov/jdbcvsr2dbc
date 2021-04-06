package ru.yusdm.jdbcvsr2dbc.coroutines.service

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.yusdm.jdbcvsr2dbc.coroutines.domain.City
import ru.yusdm.jdbcvsr2dbc.coroutines.domain.Country
import ru.yusdm.jdbcvsr2dbc.coroutines.repository.CityRepository
import ru.yusdm.jdbcvsr2dbc.coroutines.repository.CountryRepository
import java.util.*

@Service
class CountryService(
    private val countryRepository: CountryRepository,
    private val cityRepository: CityRepository
) {

    @Transactional
    suspend fun getCountryById(id: UUID): Country? {
        val country = countryRepository.findById(id)
        cityRepository.saveAll(cityRepository.findAllByCountryUID(id))

        return country
    }

    @Transactional
    fun findAllCountries(): Flow<Country> {
        return countryRepository.findAll()
    }

    @Transactional
    suspend fun createCountryWithCities(): Country {
        val country = createNewCountry()
        val created = countryRepository.save(country)

        return created
    }

    @Transactional
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

    @Transactional
    suspend fun deleteRandom(): UUID {
        val ids = countryRepository.getAllIds().toList()
        val countryToDelete = ids.random()

        countryRepository.deleteById(countryToDelete)
        return countryToDelete
    }

    @Transactional
    suspend fun deleteRandomSelectedRow(): UUID {
        val countryToDelete = countryRepository.getRandomRowUid()

        countryRepository.deleteById(countryToDelete)
        return countryToDelete
    }

    @Transactional
    suspend fun updateRandom() {
        val ids = countryRepository.getAllIds().toList()
        val countryToUpdate = ids.random()
        countryRepository.updateName("NewName", countryToUpdate)
    }

    @Transactional
    suspend fun updateRandomSelectedRow() {
        val countryToUpdate = countryRepository.getRandomRowUid()
        countryRepository.updateName("NewName", countryToUpdate)
    }

    @Transactional
    suspend fun callBlocking(): String {
        return countryRepository.callBlocking()
    }

    @Transactional
    suspend fun getRandomCountry(): Country? {
        val ids = countryRepository.getAllIds().toList()
        val countryId = ids.random()
        return countryRepository.findById(countryId)
    }

    @Transactional
    fun getAllIds(): Flow<UUID> {
        return countryRepository.getAllIds()
    }

    @Transactional
    suspend fun updateRandomSelectedFromMemoryRow(countryIds: List<UUID>) {
        countryRepository.updateName("NewName", countryIds.random())
    }

    @Transactional
    suspend fun getRandomSelectedFromMemoryRow(countryIds: List<UUID>): Country {
        return countryRepository.findById(countryIds.random())!!
    }

}