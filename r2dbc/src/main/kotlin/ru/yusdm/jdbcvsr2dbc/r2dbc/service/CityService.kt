package ru.yusdm.jdbcvsr2dbc.r2dbc.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.Mono
import ru.yusdm.jdbcvsr2dbc.r2dbc.domain.City
import ru.yusdm.jdbcvsr2dbc.r2dbc.repository.CityRepository
import java.util.*

@Service
@Transactional
class CityService(private val cityRepository: CityRepository) {

    fun getById(id: UUID): Mono<City> {
        return cityRepository.findById(id)
    }
}