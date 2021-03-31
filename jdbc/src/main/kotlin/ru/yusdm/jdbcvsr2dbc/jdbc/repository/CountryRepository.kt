package ru.yusdm.jdbcvsr2dbc.jdbc.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import ru.yusdm.jdbcvsr2dbc.jdbc.domain.Country
import java.util.*

interface CountryRepository: JpaRepository<Country, UUID> {
    @Query("SELECT c.uid FROM Country c")
    fun getAllIds(): List<UUID>

    @Modifying
    @Query("UPDATE COUNTRY SET name = :newName WHERE uid = :id", nativeQuery = true)
    fun updateName(@Param("newName") newName: String,@Param("id") id: UUID)

    @Query("select blocking_test()", nativeQuery = true)
    fun callBlocking(): String
}
