package ru.yusdm.jdbcvsr2dbc.jdbc

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import java.util.*

@SpringBootApplication
class Application

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}

private fun prepareInitialData(): List<String> {
    val countryIds = mutableListOf<UUID>()
    val sqls = mutableListOf<String>()

    for (i in 0..100) {
        val id = UUID.randomUUID();
        val sql = "INSERT INTO \${schema}.COUNTRY(ID, NAME) VALUES ('$id', 'Country_$i');"
        sqls.add(sql)
        countryIds.add(id)
    }

    countryIds.forEachIndexed { index, countryId ->
        for (i in 0..3) {
            val sql =
                "INSERT INTO \${schema}.CITY(ID, COUNTRY_ID, NAME) VALUES ('${UUID.randomUUID()}', '${countryId}', 'City_${index}_${i}');"
            sqls.add(sql)
        }
    }

    return sqls
}
