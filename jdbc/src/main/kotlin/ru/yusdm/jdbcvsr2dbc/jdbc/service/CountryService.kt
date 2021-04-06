package ru.yusdm.jdbcvsr2dbc.jdbc.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.yusdm.jdbcvsr2dbc.jdbc.domain.City
import ru.yusdm.jdbcvsr2dbc.jdbc.domain.Country
import ru.yusdm.jdbcvsr2dbc.jdbc.repository.CountryRepository
import java.util.*

@Service
@Transactional
class CountryService(
    private val countryRepository: CountryRepository,
) {

    fun findAllCountries(): List<Country> {
        return countryRepository.findAll()
    }

    fun getCountryById(countryId: UUID): Country {
        val country = countryRepository.findById(countryId).map {
            it.cities?.size
            it
        }.get()

        return country
    }

    fun createCountryWithCities(): Country {
        return countryRepository.save(createNewCountry())
    }

    fun createCountry(): Country {
        return countryRepository.save(Country("New Country", null))
    }

    fun deleteRandom(): UUID {
        val countryIds = countryRepository.getAllIds()
        val countryToDelete = countryIds.random()
        countryRepository.deleteByUid(countryToDelete)

        return countryToDelete
    }

    fun deleteRandomSelectedRow(): UUID {
        val countryToDelete = countryRepository.getRandomRowUid()

        countryRepository.deleteById(countryToDelete)
        return countryToDelete
    }


    private fun createNewCountry(): Country {
        val country = Country("New Country", mutableListOf())
        val cities = listOf(
            City("City_1", country),
            City("City_2", country),
            City("City_3", country),
            City("City_4", country),
        )
        country.cities!!.addAll(cities);

        return country
    }

    fun updateRandom() {
        val countryIds = countryRepository.getAllIds()
        val countryIdToUpdate = countryIds.random()
        countryRepository.updateName("Update Name", countryIdToUpdate)
    }

    fun updateRandomSelectedRow() {
        val countryToUpdate = countryRepository.getRandomRowUid()
        countryRepository.updateName("NewName", countryToUpdate)
    }

    fun updateRandom2(): Country {
        val countryIds = countryRepository.getAllIds()
        val countryIdToUpdate = countryIds.random()
        val countryToUpdate = countryRepository.findById(countryIdToUpdate).get()
        countryToUpdate.name = "Updated country name $countryIdToUpdate"

        return countryToUpdate
    }

    fun callBlocking(): String {
        return countryRepository.callBlocking()
    }

    fun getRandom(): Country {
        val countryIds = countryRepository.getAllIds()
        val countryIdToUpdate = countryIds.random()
        return countryRepository.findById(countryIdToUpdate).get()
    }

    fun getAllIds(): List<UUID> {
        return countryRepository.getAllIds()
    }

    fun updateRandomSelectedFromMemoryRow(countryIds: List<UUID>) {
        countryRepository.updateName("NewName", countryIds.random())
    }

}