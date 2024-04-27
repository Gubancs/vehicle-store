package hu.gubancs.vehiclestore.model

import jakarta.persistence.*
import org.hibernate.annotations.Cache
import org.hibernate.annotations.CacheConcurrencyStrategy
import java.time.LocalDate

//@Cacheable
//@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@Entity
@Table(
    name = "VEHICLES", indexes = [
        Index(name = "IDX_UUID", columnList = "UUID"),
        Index(name = "IDX_PLATE_NUMBER", columnList = "PLATE_NUMBER"),
        Index(name = "IDX_OWNER_NAME", columnList = "OWNER_NAME"),
    ]
)
class Vehicle : BaseEntity() {

    @Column(name = "UUID", length = 36, unique = true)
    var uuid: String? = null

    @Column(name = "PLATE_NUMBER", length = 20, unique = true)
    var plateNumber: String? = null

    @Column(name = "OWNER_NAME", length = 200)
    var ownerName: String? = null

    @Column(name = "LICENSE_VALID_UNTIL")
    @Temporal(TemporalType.DATE)
    var licenseValidUntil: LocalDate? = null

    @Column(name = "ATTRIBUTES")
    var attributes: String? = null
}
