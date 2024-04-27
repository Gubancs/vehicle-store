package hu.gubancs.vehiclestore.model

import jakarta.persistence.*

@MappedSuperclass
open class BaseEntity {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}