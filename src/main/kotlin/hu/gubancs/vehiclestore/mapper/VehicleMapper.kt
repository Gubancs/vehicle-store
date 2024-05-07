package hu.gubancs.vehiclestore.mapper

import hu.gubancs.vehiclestore.model.Vehicle
import hu.gubancs.vehiclestore.rest.dto.VehicleDto
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

@Component
 class VehicleMapper {

    fun mapToDto(vehicle: Vehicle): VehicleDto {
        val vehicleDto = VehicleDto()
        vehicleDto.uuid = vehicle.uuid
        vehicleDto.registration = vehicle.registration
        vehicleDto.validity = vehicle.validity
        vehicleDto.owner = vehicle.owner
        vehicleDto.data = vehicle.data?.split("|")
        return vehicleDto
    }

    fun mapToEntity(dto: VehicleDto): Vehicle {
        val vehicle = Vehicle()
        vehicle.uuid = dto.uuid
        vehicle.registration = dto.registration
        vehicle.validity = dto.validity
        vehicle.owner = dto.owner
        vehicle.data = dto.data?.joinToString("|")
        return vehicle
    }

}
