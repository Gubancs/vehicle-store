package hu.gubancs.vehiclestore.service

import hu.gubancs.vehiclestore.mapper.VehicleMapper
import hu.gubancs.vehiclestore.repository.VehicleRepository
import hu.gubancs.vehiclestore.rest.dto.VehicleDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.Cacheable
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import java.util.*

@Service
class VehicleService {

    @Autowired
    private lateinit var repository: VehicleRepository

    @Autowired
    private lateinit var mapper: VehicleMapper

    @Async
    fun saveAsync(dto: VehicleDto) {
        repository.save(mapper.mapToEntity(dto))
    }

    @Cacheable("plateNumberCache")
    fun existsByPlateNumber(plateNumber: String): Boolean {
        return repository.existsByPlateNumber(plateNumber)
    }

    @Cacheable("uuidCache")
    fun findByUuid(uuid: String): Optional<VehicleDto> {
        return repository.findByUuid(uuid).map { mapper.mapToDto(it) }
    }

    @Cacheable("searchCache")
    fun search(keyword: String): List<VehicleDto> {
        return repository.search(keyword).map { mapper.mapToDto(it) }
    }

    @Cacheable("countCache")
    fun count(): Long {
        return repository.count()
    }
}
