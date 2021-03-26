package ru.yusdm.jdbcvsr2dbc.r2dbc.domain

import org.springframework.data.annotation.Id
import org.springframework.data.domain.Persistable
import java.util.*

class City: Persistable<UUID> {

    @Id
    val uid: UUID

    val name: String

    @Transient
    private var _isNew: Boolean

    constructor(name: String) {
        this.uid = UUID.randomUUID()
        this.name = name
        this._isNew = true
    }

    override fun getId() = this.uid

    override fun isNew() = this._isNew

}