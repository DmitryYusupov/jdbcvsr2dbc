package ru.yusdm.jdbcvsr2dbc.r2dbc.domain

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.PersistenceConstructor
import org.springframework.data.annotation.Transient
import org.springframework.data.domain.Persistable
import org.springframework.data.relational.core.mapping.Table
import java.util.*

@Table
class City : Persistable<UUID> {

    @Id
    var uid: UUID

    val countryUID: UUID

    val name: String

    @Transient
    private var _isNew: Boolean

    constructor(countryUID: UUID, name: String) {
        this.uid = UUID.randomUUID()
        this.countryUID = countryUID
        this.name = name
        this._isNew = true
    }

    @PersistenceConstructor
    constructor(uid: UUID, countryUID: UUID, name: String) {
        this.uid = uid
        this.countryUID = countryUID
        this.name = name
        this._isNew = false
    }

    override fun getId() = this.uid

    override fun isNew() = this._isNew

}