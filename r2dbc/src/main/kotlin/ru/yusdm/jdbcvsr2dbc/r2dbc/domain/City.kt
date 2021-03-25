package ru.yusdm.jdbcvsr2dbc.r2dbc.domain

import org.springframework.data.annotation.Id
import org.springframework.data.domain.Persistable

class City: Persistable<Long> {

    @Id
    val uid: Long

    val name: String

    @Transient
    private var _isNew: Boolean

    constructor(uid: Long, name: String) {
        this.uid = uid
        this.name = name
        this._isNew = true
    }

    override fun getId() = this.uid

    override fun isNew() = this._isNew

}