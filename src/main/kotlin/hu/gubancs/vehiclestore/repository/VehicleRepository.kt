package hu.gubancs.vehiclestore.repository

import hu.gubancs.vehiclestore.model.Vehicle
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface VehicleRepository : JpaRepository<Vehicle, Long> {

    fun existsByPlateNumber(plateNumber: String): Boolean

    fun findByUuid(uuid: String): Optional<Vehicle>

    @Query("SELECT v FROM Vehicle v WHERE lower(v.plateNumber) LIKE %:query% OR lower(v.ownerName) LIKE %:query% OR lower(v.attributes) LIKE %:query%")
    fun search(query: String): List<Vehicle>
}
