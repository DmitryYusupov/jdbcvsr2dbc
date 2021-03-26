package ru.yusdm.jdbcvsr2dbc.r2dbc.domain

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.PersistenceConstructor
import org.springframework.data.annotation.Transient
import org.springframework.data.domain.Persistable
import java.util.*

class Country: Persistable<UUID> {

    @Id
    val uid: UUID

    val name: String

    @Transient
    private var _isNew: Boolean

    @Transient
    val cities: MutableList<City> = mutableListOf()

    @PersistenceConstructor
    constructor( name: String) {
        this.uid = UUID.randomUUID()
        this.name = name
        this._isNew = true
    }

    override fun getId() = this.uid

    override fun isNew() = this._isNew

}