package ru.yusdm.jdbcvsr2dbc.jdbc

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import java.util.*

@SpringBootApplication
class Application

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
//    prepareInitialData().forEach { println(it) }
}

private fun prepareInitialData(): List<String> {

    val countryIds = mutableListOf<Long>()
    val sqls = mutableListOf<String>()

    var id = 1
    for (i in 0..100) {
        val sql = "INSERT INTO \${schema}.COUNTRY(ID, NAME) VALUES ('$id', 'Country_$id');"
        sqls.add(sql)
        countryIds.add(id.toLong())
        id++
    }

    countryIds.forEachIndexed { index, countryId ->
        for (i in 0..3) {
            val sql =
                "INSERT INTO \${schema}.CITY(ID, COUNTRY_ID, NAME) VALUES ('${id}', '${countryId}', 'City_${index}_${id}');"
            sqls.add(sql)
            id++
        }
    }

    return sqls
}
