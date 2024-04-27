package hu.gubancs.vehiclestore.impl

import hu.gubancs.vehiclestore.api.VehicleDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import java.util.*

@Service
class VehicleService {

    @Autowired
    private lateinit var repository: VehicleRepository

    @Autowired
    private lateinit var mapper: VehicleDtoMapper

    @Async
    fun saveAsync(dto: VehicleDto) {
        repository.save(mapper.mapToEntity(dto))
    }

    fun existsByPlateNumber(plateNumber: String): Boolean {
        return repository.existsByPlateNumber(plateNumber)
    }

    fun findByUuid(uuid: String): Optional<VehicleDto> {
        return repository.findByUuid(uuid).map { mapper.mapToDto(it) }
    }

    fun search(keyword: String): List<VehicleDto> {
        return repository.search(keyword).map { mapper.mapToDto(it) }
    }

    fun count(): Long {
        return repository.count()
    }
}
