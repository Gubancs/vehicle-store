package hu.gubancs.vehiclestore.rest

import hu.gubancs.vehiclestore.rest.dto.VehicleDto
import hu.gubancs.vehiclestore.service.VehicleService
import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.annotation.*
import java.net.URI
import java.util.*

@RestController
class VehicleRestController {

    @Autowired
    private lateinit var service: VehicleService

    @PostMapping(
        "/jarmuvek",
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun create(@Valid @RequestBody dto: VehicleDto): ResponseEntity<VehicleDto> {
        return if (service.existsByRegistration(dto.registration!!)) {
            ResponseEntity.status(HttpStatus.CONFLICT).build()
        } else {
            dto.uuid = UUID.randomUUID().toString()
            service.createAsync(dto)
            ResponseEntity.status(HttpStatus.CREATED)
                .headers { headers -> headers.location = URI.create("/jarmuvek/${dto.uuid}") }
                .body(dto)
        }
    }

    @GetMapping("/jarmuvek/{uuid}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun get(@NotBlank @PathVariable uuid: String): ResponseEntity<VehicleDto> {
        return ResponseEntity.of(service.findByUuid(uuid))
    }

    @GetMapping("/kereses", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun search(@NotBlank @RequestParam(name = "q") keyword: String?): List<VehicleDto> {
        return if (keyword.isNullOrBlank()) emptyList() else service.search(keyword)
    }

    @GetMapping(value = ["/jarmuvek"], produces = [MediaType.TEXT_PLAIN_VALUE])
    fun count(): String {
        return service.count().toString()
    }

    /**
     * Custom exception handler for HttpMessageNotReadableException.
     */
    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun notReadableMessageHandler(e: HttpMessageNotReadableException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.message)
    }
}