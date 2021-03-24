package ru.yusdm.jdbcvsr2dbc.jdbc.repository

import java.util.concurrent.atomic.AtomicLong

object IdGenerator {
    private val generator = AtomicLong(1000)

    fun getId(): Long {
        return generator.incrementAndGet()
    }
}