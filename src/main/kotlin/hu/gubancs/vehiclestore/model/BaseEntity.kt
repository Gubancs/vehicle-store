package hu.gubancs.vehiclestore.model

import jakarta.persistence.Column
import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass

@MappedSuperclass
open class BaseEntity {
    @Id
    @Column(name = "uuid", unique = true)
    var uuid: String? = null
}