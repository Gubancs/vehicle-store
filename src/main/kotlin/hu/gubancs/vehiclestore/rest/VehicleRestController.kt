package hu.gubancs.vehiclestore.rest

import hu.gubancs.vehiclestore.rest.dto.VehicleDto
import hu.gubancs.vehiclestore.service.VehicleService
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
class VehicleRestController {

    @Autowired
    private lateinit var service: VehicleService

    @PostMapping(
        "/jarmuvek",
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun create(@Valid @RequestBody vehicle: VehicleDto): ResponseEntity<VehicleDto> {
        return if (service.existsByRegistration(vehicle.registration!!)) {
            ResponseEntity.status(HttpStatus.CONFLICT)
                .build()
        } else {
            val res = service.create(vehicle)
            ResponseEntity.created(URI.create("/jarmuvek/${res.uuid}"))
                .body(res)
        }
    }

    @GetMapping("/jarmuvek/{uuid}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun get(@PathVariable(required = false) uuid: String?): ResponseEntity<VehicleDto> {
        return if (uuid.isNullOrBlank()) ResponseEntity.badRequest().build()
        else ResponseEntity.of(service.findById(uuid))
    }

    @GetMapping("/kereses", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun search(@RequestParam(name = "q", required = false) keyword: String?): ResponseEntity<List<VehicleDto>> {
        return if (keyword.isNullOrBlank()) ResponseEntity.badRequest().build()
        else ResponseEntity.ok(service.search(keyword))
    }

    @GetMapping(value = ["/jarmuvek"], produces = [MediaType.TEXT_PLAIN_VALUE])
    fun count(): String {
        return service.count().toString()
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun notReadableMessageHandler(e: HttpMessageNotReadableException): ResponseEntity<String> {
        return ResponseEntity.badRequest().body(e.message)
    }

    @ExceptionHandler(DataIntegrityViolationException::class)
    fun dataIntegrityMessageHandler(e: DataIntegrityViolationException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.message)
    }

}