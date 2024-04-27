package hu.gubancs.vehiclestore.api

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.Valid
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import java.time.LocalDate

class VehicleDto {

    @JsonProperty("uuid")
    var uuid: String? = null

    @NotBlank
    @Size(max = 20)
    @JsonProperty("rendszam")
    var plateNumber: String? = null

    @NotBlank
    @Size(max = 200)
    @JsonProperty("tulajdonos")
    var ownerName: String? = null

    @NotNull
    @JsonProperty("forgalmi_ervenyes")
    var licenseValidUntil: LocalDate? = null

    @Valid
    @NotNull
    @Size(max = 200)
    @JsonProperty("adatok")
    var attributes: List<@NotBlank @Max(200) String>? = null

    override fun toString(): String {
        return "VehicleDto(uuid=$uuid, plateNumber=$plateNumber, ownerName=$ownerName, licenseValidUntil=$licenseValidUntil, attributes=$attributes)"
    }
}
