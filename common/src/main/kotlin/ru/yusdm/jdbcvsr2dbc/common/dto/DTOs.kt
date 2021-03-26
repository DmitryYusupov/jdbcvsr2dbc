package ru.yusdm.jdbcvsr2dbc.common.dto

import java.util.*

data class CityDTO(val id: UUID, val name: String)

data class CountryDTO(val id: UUID, val name: String, val cities: List<CityDTO> = listOf())