package ru.yusdm.jdbcvsr2dbc.jdbc.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.yusdm.jdbcvsr2dbc.jdbc.domain.City
import java.util.*

interface CityRepository: JpaRepository<City, Long>
