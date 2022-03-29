package com.example.data.model.weather.api.current

import com.google.gson.annotations.SerializedName

data class ApiWind(
    @SerializedName("speed")
    val speed: Double,
    @SerializedName("deg")
    val degree: Double
)
