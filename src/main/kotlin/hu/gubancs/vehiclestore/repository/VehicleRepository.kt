package hu.gubancs.vehiclestore.repository

import hu.gubancs.vehiclestore.model.Vehicle
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface VehicleRepository : JpaRepository<Vehicle, Long> {

    fun existsByRegistration(plateNumber: String): Boolean

    fun findByUuid(uuid: String): Optional<Vehicle>

    @Query("SELECT v FROM Vehicle v WHERE v.registration ILIKE %:query% OR v.owner ILIKE %:query% OR v.data ILIKE %:query%")
    fun search(query: String): List<Vehicle>
}
