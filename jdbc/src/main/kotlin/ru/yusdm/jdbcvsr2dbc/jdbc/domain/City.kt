package ru.yusdm.jdbcvsr2dbc.jdbc.domain

import org.springframework.data.annotation.Transient
import org.springframework.data.domain.Persistable
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "city")
class City: Persistable<UUID> {

    @Id
    @Column(name = "id")
    val uuid: UUID

    @Column(name = "name")
    val name: String

    @ManyToOne
    @JoinColumn(name = "country_id")
    private val country: Country

    @Transient
    private var _isNew: Boolean

    constructor(name: String, country: Country) {
        this.uuid = UUID.randomUUID()
        this.country = country
        this.name = name
        this._isNew = true
    }

    override fun getId() = this.uuid

    override fun isNew() = this._isNew

    @PostPersist
    @PostLoad
    private fun setPersisted() {
        this._isNew = false
    }
}