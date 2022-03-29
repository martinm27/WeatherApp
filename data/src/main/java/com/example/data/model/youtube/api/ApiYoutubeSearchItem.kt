package com.example.data.model.youtube.api

import com.google.gson.annotations.SerializedName

data class ApiYoutubeSearchItem(
    @SerializedName("id")
    val youtubeItemId: ApiYoutubeItemIdentifier
)
