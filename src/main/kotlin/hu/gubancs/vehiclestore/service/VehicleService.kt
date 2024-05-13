package hu.gubancs.vehiclestore.service

import hu.gubancs.vehiclestore.mapper.VehicleMapper
import hu.gubancs.vehiclestore.repository.VehicleRepository
import hu.gubancs.vehiclestore.rest.dto.VehicleDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service
import java.util.*

@Service
class VehicleService {

    @Autowired
    private lateinit var repository: VehicleRepository

    @Autowired
    private lateinit var mapper: VehicleMapper

    fun create(dto: VehicleDto): VehicleDto {
        return repository.save(mapper.mapToEntity(dto))
            .let { mapper.mapToDto(it) }
    }

    @Cacheable("registrationCache")
    fun existsByRegistration(registration: String): Boolean {
        return repository.existsByRegistration(registration)
    }

    @Cacheable("uuidCache")
    fun findById(uuid: String): Optional<VehicleDto> {
        return repository.findById(uuid).map { mapper.mapToDto(it) }
    }

    @Cacheable("searchCache")
    fun search(keyword: String): List<VehicleDto> {
        return repository.search(keyword).map { mapper.mapToDto(it) }
    }

    fun count(): Long {
        return repository.count()
    }
}
