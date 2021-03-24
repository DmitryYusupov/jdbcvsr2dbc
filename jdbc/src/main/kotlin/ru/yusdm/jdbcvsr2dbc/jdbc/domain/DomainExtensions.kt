package ru.yusdm.jdbcvsr2dbc.jdbc.domain

import ru.yusdm.jdbcvsr2dbc.common.dto.CityDTO
import ru.yusdm.jdbcvsr2dbc.common.dto.CountryDTO

fun City.toDTO(): CityDTO {
    return CityDTO(id, name)
}

fun Country.toDTO(): CountryDTO {
    return CountryDTO(id, name, cities.map { it.toDTO() })
}