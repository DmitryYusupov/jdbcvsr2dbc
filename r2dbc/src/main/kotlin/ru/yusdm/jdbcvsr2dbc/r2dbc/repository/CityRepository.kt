package ru.yusdm.jdbcvsr2dbc.r2dbc.repository

import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Flux
import ru.yusdm.jdbcvsr2dbc.r2dbc.domain.City
import java.util.*

interface CityRepository : ReactiveCrudRepository<City, UUID> {

    fun findAllByCountryUID(countryUID: UUID): Flux<City>
}