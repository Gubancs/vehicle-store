package hu.gubancs.vehiclestore.rest

import hu.gubancs.vehiclestore.rest.dto.VehicleDto
import hu.gubancs.vehiclestore.service.VehicleService
import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI
import java.util.*

@RestController
class VehicleRestController {

    @Autowired
    private lateinit var service: VehicleService

    @PostMapping("/jarmuvek")
    fun create(@Valid @RequestBody dto: VehicleDto): ResponseEntity<VehicleDto> {
        return if (service.existsByPlateNumber(dto.plateNumber!!)) {
            conflictResponse()
        } else {
            dto.uuid = UUID.randomUUID().toString()
            service.createAsync(dto)
            createdResponse(dto)
        }
    }

    private fun createdResponse(dto: VehicleDto): ResponseEntity<VehicleDto> {
        return ResponseEntity.status(HttpStatus.CREATED)
            .headers { headers -> headers.location = URI.create("/jarmuvek/${dto.uuid}") }
            .body(dto)
    }

    private fun conflictResponse(): ResponseEntity<VehicleDto> {
        return ResponseEntity.status(HttpStatus.CONFLICT).build()
    }

    @GetMapping("/jarmuvek/{uuid}")
    fun get(@NotBlank @PathVariable uuid: String): ResponseEntity<VehicleDto> {
        return ResponseEntity.of(service.findByUuid(uuid))
    }

    @GetMapping("/kereses")
    fun search(@NotBlank @RequestParam(name = "q") keyword: String?): List<VehicleDto> {
        if (keyword.isNullOrBlank()) {
            return emptyList();
        }
        return service.search(keyword)
    }

    @GetMapping(value = ["/jarmuvek"], produces = [MediaType.TEXT_PLAIN_VALUE])
    fun count(): String {
        return service.count().toString()
    }
}