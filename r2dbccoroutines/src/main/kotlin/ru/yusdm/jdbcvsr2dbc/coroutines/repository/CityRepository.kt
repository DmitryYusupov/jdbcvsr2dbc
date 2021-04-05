package ru.yusdm.jdbcvsr2dbc.coroutines.repository

import kotlinx.coroutines.flow.Flow
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import ru.yusdm.jdbcvsr2dbc.coroutines.domain.City
import java.util.*

interface CityRepository : CoroutineCrudRepository<City, UUID> {

    fun findAllByCountryUID(countryUID: UUID): Flow<City>
}