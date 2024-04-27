package hu.gubancs.vehiclestore.impl

import hu.gubancs.vehiclestore.api.VehicleDto
import hu.gubancs.vehiclestore.model.Vehicle
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

@Component
@Scope("prototype")
class VehicleDtoMapper {

    fun mapToDto(vehicle: Vehicle): VehicleDto {
        val vehicleDto = VehicleDto()
        vehicleDto.uuid = vehicle.uuid
        vehicleDto.plateNumber = vehicle.plateNumber
        vehicleDto.licenseValidUntil = vehicle.licenseValidUntil
        vehicleDto.ownerName = vehicle.ownerName
        vehicleDto.attributes = vehicle.attributes?.split("#")
        return vehicleDto
    }

    fun mapToEntity(vehicleDto: VehicleDto): Vehicle {
        val vehicle = Vehicle()
        vehicle.uuid = vehicleDto.uuid
        vehicle.plateNumber = vehicleDto.plateNumber
        vehicle.licenseValidUntil = vehicleDto.licenseValidUntil
        vehicle.ownerName = vehicleDto.ownerName
        vehicle.attributes = vehicleDto.attributes?.joinToString("#")
        return vehicle
    }
}
