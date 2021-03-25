package ru.yusdm.jdbcvsr2dbc.r2dbc.repository

import org.springframework.data.repository.reactive.ReactiveCrudRepository
import ru.yusdm.jdbcvsr2dbc.r2dbc.domain.Country

interface CountryRepository: ReactiveCrudRepository<Country, Long>