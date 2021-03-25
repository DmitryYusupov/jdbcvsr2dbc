package ru.yusdm.jdbcvsr2dbc.jdbc.domain

import ru.yusdm.jdbcvsr2dbc.common.dto.CityDTO
import ru.yusdm.jdbcvsr2dbc.common.dto.CountryDTO

fun City.toDTO(): CityDTO {
    return CityDTO(id, name)
}

fun Country.toDTO(fetchCities: Boolean = false): CountryDTO {
    return CountryDTO(id, name, if (fetchCities) cities.map { it.toDTO() } else emptyList())
}