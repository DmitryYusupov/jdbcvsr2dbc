package ru.yusdm.jdbcvsr2dbc.common.dto

data class CityDTO(val id: Long, val name: String)

data class CountryDTO(val id: Long, val name: String, val cities: List<CityDTO> = listOf())