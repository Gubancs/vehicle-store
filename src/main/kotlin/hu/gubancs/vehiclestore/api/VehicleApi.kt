package hu.gubancs.vehiclestore.api

import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@FeignClient(name = "vehicle-api", url = "{vehicle.api.url}")
interface VehicleApi {

    @PostMapping("/jarmuvek")
    fun save(@Valid @RequestBody dto: VehicleDto): ResponseEntity<VehicleDto>

    @GetMapping("/jarmuvek/{uuid}")
    fun get(@NotBlank @PathVariable uuid: String): ResponseEntity<VehicleDto>

    @GetMapping("/kereses")
    fun search(@NotBlank @RequestParam(name = "q") keyword: String?): List<VehicleDto>

    @GetMapping(value = ["/jarmuvek"], produces = [MediaType.TEXT_PLAIN_VALUE])
    fun count(): String
}
