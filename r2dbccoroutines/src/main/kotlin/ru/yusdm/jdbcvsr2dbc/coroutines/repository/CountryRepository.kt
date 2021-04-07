package ru.yusdm.jdbcvsr2dbc.coroutines.repository

import kotlinx.coroutines.flow.Flow
import org.springframework.data.r2dbc.repository.Modifying
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.data.repository.query.Param
import ru.yusdm.jdbcvsr2dbc.coroutines.domain.Country
import java.util.*

interface CountryRepository: CoroutineCrudRepository<Country, UUID> {
    @Query("SELECT c.uid FROM Country c")
    fun getAllIds(): Flow<UUID>

    @Modifying
    @Query("UPDATE COUNTRY SET name = :newName WHERE uid = :id")
    suspend fun updateName(@Param("newName") newName: String, @Param("id") id: UUID)

    //@Query("select blocking_test()")
    @Query("select dbo.blocking_test()")
    suspend fun callBlocking(): String

    //SELECT c.UID FROM country c ORDER BY RANDOM() LIMIT 1
    @Query("SELECT TOP 1 c.UID FROM country c ORDER BY NEWID()")
    suspend fun getRandomRowUid(): UUID
}