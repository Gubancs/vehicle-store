package hu.gubancs.vehiclestore.rest.dto

import com.google.gson.annotations.SerializedName
import jakarta.validation.Valid
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import java.util.*

class VehicleDto {

    @SerializedName("uuid")
    var uuid: String? = null

    @NotBlank
    @Size(max = 20)
    @SerializedName("rendszam")
    var registration: String? = null

    @NotBlank
    @Size(max = 200)
    @SerializedName("tulajdonos")
    var owner: String? = null

    @NotNull
    @SerializedName("forgalmi_ervenyes")
    var validity: Date? = null

    @Valid
    @NotNull
    @Size(max = 200)
    @SerializedName("adatok")
    var data: List<@NotBlank @Max(200) String>? = null

}
