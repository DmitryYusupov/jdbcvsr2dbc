package ru.yusdm.jdbcvsr2dbc.coroutines.repository

import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Flux
import ru.yusdm.jdbcvsr2dbc.coroutines.domain.City
import java.util.*

interface CityRepository : ReactiveCrudRepository<City, UUID> {

    fun findAllByCountryUID(countryUID: UUID): Flux<City>
}