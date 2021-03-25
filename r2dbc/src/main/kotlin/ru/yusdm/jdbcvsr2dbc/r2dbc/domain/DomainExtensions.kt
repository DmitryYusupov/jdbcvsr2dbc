package ru.yusdm.jdbcvsr2dbc.r2dbc.domain

import ru.yusdm.jdbcvsr2dbc.common.dto.CityDTO
import ru.yusdm.jdbcvsr2dbc.common.dto.CountryDTO

fun City.toDTO(): CityDTO {
    return CityDTO(id, name)
}

fun Country.toDTO(): CountryDTO {
    return CountryDTO(id, name)
}