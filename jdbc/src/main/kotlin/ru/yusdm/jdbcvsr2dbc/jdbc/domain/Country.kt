package ru.yusdm.jdbcvsr2dbc.jdbc.domain

import org.springframework.data.domain.Persistable
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "country")
class Country: Persistable<UUID> {

    @Id
    @Column(name = "uid")
    val uid: UUID

    @Column(name = "name")
    val name: String

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "country", cascade = [CascadeType.PERSIST])
    val cities: MutableList<City>

    @Transient
    private var _isNew: Boolean

    constructor(name: String, cities: MutableList<City>) {
        this.uid = UUID.randomUUID()
        this.name = name
        this.cities = cities
        this._isNew = true
    }

    override fun getId() = this.uid

    override fun isNew() = this._isNew

    @PostPersist
    @PostLoad
    private fun setPersisted() {
        this._isNew = false
    }
}