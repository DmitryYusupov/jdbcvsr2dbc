package ru.yusdm.jdbcvsr2dbc.r2dbc.domain

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.PersistenceConstructor
import org.springframework.data.annotation.Transient
import org.springframework.data.domain.Persistable
import org.springframework.data.relational.core.mapping.Table
import java.util.*

@Table
class Country : Persistable<UUID> {

    @Id
    var uid: UUID

    var name: String

    @Transient
    private var _isNew: Boolean

    @Transient
    val cities: MutableList<City> = mutableListOf()

    /**
     * @PersistenceConstructor - marks a given constructor - even a package protected one -
     * to use when instantiating the object from the database.
     * Constructor arguments are mapped by name to the key values in the retrieved DBObject.
     */
    @PersistenceConstructor
    constructor(uid: UUID, name: String) {
        this.uid = uid
        this.name = name
        this._isNew = false
    }

    constructor(name: String) {
        this.uid = UUID.randomUUID()
        this.name = name
        this._isNew = true
    }

    override fun getId() = this.uid

    override fun isNew() = this._isNew

}