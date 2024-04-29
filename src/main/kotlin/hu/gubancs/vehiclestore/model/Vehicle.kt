package hu.gubancs.vehiclestore.model

import jakarta.persistence.*
import org.hibernate.annotations.Cache
import org.hibernate.annotations.CacheConcurrencyStrategy
import java.time.LocalDate

@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region = "entity")
@Entity
@Table(
    name = "vehicles", indexes = [
        Index(name = "idx_vehicle_uuid", columnList = "uuid", unique = true),
        Index(name = "idx_vehicle_registration", columnList = "registration", unique = true),
        Index(name = "idx_vehicle_owner", columnList = "owner"),
        Index(name = "idx_vehicle_data", columnList = "data"),
    ]
)
class Vehicle : BaseEntity() {

    @Column(length = 36)
    var uuid: String? = null

    @Column(length = 20)
    var registration: String? = null

    @Column(length = 200)
    var owner: String? = null

    @Temporal(TemporalType.DATE)
    var validity: LocalDate? = null

    var data: String? = null

}
