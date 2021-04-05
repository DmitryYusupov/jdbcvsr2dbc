package ru.yusdm.jdbcvsr2dbc.coroutines.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.yusdm.jdbcvsr2dbc.coroutines.domain.City
import ru.yusdm.jdbcvsr2dbc.coroutines.repository.CityRepository
import java.util.*

@Service
@Transactional
class CityService(private val cityRepository: CityRepository) {

    suspend fun getById(id: UUID): City? {
        return cityRepository.findById(id)
    }
}