package ru.yusdm.jdbcvsr2dbc.jdbc.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.yusdm.jdbcvsr2dbc.jdbc.domain.City
import ru.yusdm.jdbcvsr2dbc.jdbc.repository.CityRepository
import java.util.*

@Service
@Transactional
class CityService(private val cityRepository: CityRepository) {

    fun getById(id: UUID): City {
        return cityRepository.findById(id).get()
    }
}