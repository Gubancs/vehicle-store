package hu.gubancs.vehiclestore.model

import jakarta.persistence.*

@MappedSuperclass
open class BaseEntity {
    @Id
    @Column(name = "uuid", unique = true)
    @GeneratedValue(strategy = GenerationType.UUID)
    var uuid: String? = null
}