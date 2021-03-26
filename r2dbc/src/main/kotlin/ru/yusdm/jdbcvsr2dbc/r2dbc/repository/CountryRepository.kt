package ru.yusdm.jdbcvsr2dbc.r2dbc.repository

import org.springframework.data.repository.reactive.ReactiveCrudRepository
import ru.yusdm.jdbcvsr2dbc.r2dbc.domain.Country
import java.util.*

interface CountryRepository: ReactiveCrudRepository<Country, UUID>