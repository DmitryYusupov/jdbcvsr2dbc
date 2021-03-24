package ru.yusdm.jdbcvsr2dbc.jdbc.domain

import org.springframework.data.domain.Persistable
import javax.persistence.*

@Entity
@Table(name = "country")
class Country: Persistable<Long> {

    @Id
    @Column(name = "id")
    val uid: Long

    @Column(name = "name")
    val name: String

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "country")
    val cities: MutableList<City>

    @Transient
    private var _isNew: Boolean

    constructor(uid: Long, name: String, cities: MutableList<City>) {
        this.uid = uid
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