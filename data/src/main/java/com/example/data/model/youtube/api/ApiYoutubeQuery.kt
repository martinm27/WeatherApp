package com.example.data.model.youtube.api

import com.google.gson.annotations.SerializedName

data class ApiYoutubeQuery(
    @SerializedName("items")
    val items: List<ApiYoutubeSearchItem>
)
