package com.example.data.model.youtube.api

import com.google.gson.annotations.SerializedName

data class ApiYoutubeItemIdentifier(
    @SerializedName("kind")
    val kind: String,
    @SerializedName("videoId")
    val videoId: String
)
