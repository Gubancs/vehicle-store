package hu.gubancs.vehiclestore.impl

import hu.gubancs.vehiclestore.api.VehicleApi
import hu.gubancs.vehiclestore.api.VehicleDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.Cacheable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import java.net.URI
import java.util.*

@RestController
class VehicleRestController : VehicleApi {

    @Autowired
    private lateinit var service: VehicleService

    override fun save(dto: VehicleDto): ResponseEntity<VehicleDto> {
        if (service.existsByPlateNumber(dto.plateNumber!!)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build()
        } else {
            dto.uuid = UUID.randomUUID().toString()
            service.saveAsync(dto)
            return ResponseEntity.status(HttpStatus.CREATED)
                .headers { headers -> headers.location = URI.create("/jarmuvek/${dto.uuid}") }
                .body(dto)
        }
    }

    //@Cacheable("vehicleCache")
    override fun get(uuid: String): ResponseEntity<VehicleDto> {
        return ResponseEntity.of(service.findByUuid(uuid))
    }

    override fun search(keyword: String?): List<VehicleDto> {
        if (keyword.isNullOrBlank()) {
            return emptyList();
        }
        return service.search(keyword)
    }

    override fun count(): String {
        return service.count().toString()
    }
}
