package ru.yusdm.jdbcvsr2dbc.r2dbc.repository

import org.springframework.data.r2dbc.repository.Modifying
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import ru.yusdm.jdbcvsr2dbc.r2dbc.domain.Country
import java.util.*

interface CountryRepository: ReactiveCrudRepository<Country, UUID> {
    @Query("SELECT c.uid FROM Country c")
    fun getAllIds(): Flux<UUID>

    @Modifying
    @Query("UPDATE COUNTRY SET name = :newName WHERE uid = :id")
    fun updateName(@Param("newName") newName: String, @Param("id") id: UUID): Mono<Void>

    @Query("select blocking_test()")
    fun callBlocking(): Mono<String>

    //SELECT c.UID FROM country c ORDER BY RANDOM() LIMIT 1
    @Query("SELECT TOP 1 c.UID FROM country c ORDER BY NEWID()")
    fun getRandomRowUid(): Mono<UUID>

}