package hu.gubancs.vehiclestore.model

import jakarta.persistence.*
import org.hibernate.annotations.Cache
import org.hibernate.annotations.CacheConcurrencyStrategy
import java.util.*

@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name = "vehicles")
class Vehicle : BaseEntity() {

    @Column(name = "uuid", unique = true)
    var uuid: String? = null

    @Column(length = 20)
    var registration: String? = null

    @Column(length = 200)
    var owner: String? = null

    @Temporal(TemporalType.DATE)
    var validity: Date? = null

    @Column(length = 200)
    var data: String? = null

}
