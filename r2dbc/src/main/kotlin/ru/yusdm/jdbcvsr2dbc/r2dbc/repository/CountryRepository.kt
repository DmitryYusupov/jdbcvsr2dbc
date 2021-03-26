package ru.yusdm.jdbcvsr2dbc.r2dbc.repository

import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Flux
import ru.yusdm.jdbcvsr2dbc.r2dbc.domain.Country
import java.util.*

interface CountryRepository: ReactiveCrudRepository<Country, UUID> {
    @Query("SELECT c.uid FROM Country c")
    fun getAllIds(): Flux<UUID>
}