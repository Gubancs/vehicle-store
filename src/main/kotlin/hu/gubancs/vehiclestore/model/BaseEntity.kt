package hu.gubancs.vehiclestore.model

import jakarta.persistence.*

@MappedSuperclass
open class BaseEntity {
    @Id
    @Column(name = "ID")
     @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqGen")
    @SequenceGenerator(name = "seqGen", sequenceName = "entity_seq", allocationSize = 50, initialValue = 1)
    var id: Long? = null
}