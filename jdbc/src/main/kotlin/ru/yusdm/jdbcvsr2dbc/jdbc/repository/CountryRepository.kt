package ru.yusdm.jdbcvsr2dbc.jdbc.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.yusdm.jdbcvsr2dbc.jdbc.domain.Country
import java.util.*

interface CountryRepository: JpaRepository<Country, UUID>
