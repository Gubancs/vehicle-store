package hu.gubancs.vehiclestore.model

import jakarta.persistence.*
import org.hibernate.annotations.Cache
import org.hibernate.annotations.CacheConcurrencyStrategy
import java.time.LocalDate

@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region = "entity")
@Entity
@Table(
    name = "VEHICLES", indexes = [
        Index(name = "IDX_VEHICLE_UUID", columnList = "UUID", unique = true),
        Index(name = "IDX_VEHICLE_PLATE_NUMBER", columnList = "PLATE_NUMBER", unique = true),
        Index(name = "IDX_VEHICLE_OWNER_NAME", columnList = "OWNER_NAME"),
        Index(name = "IDX_VEHICLE_ATTRIBUTES", columnList = "ATTRIBUTES"),
    ]
)
class Vehicle : BaseEntity() {

    @Column(name = "UUID", length = 36)
    var uuid: String? = null

    @Column(name = "PLATE_NUMBER", length = 20)
    var plateNumber: String? = null

    @Column(name = "OWNER_NAME", length = 200)
    var ownerName: String? = null

    @Column(name = "LICENSE_VALID_UNTIL")
    @Temporal(TemporalType.DATE)
    var licenseValidUntil: LocalDate? = null

    @Column(name = "ATTRIBUTES")
    var attributes: String? = null

}
