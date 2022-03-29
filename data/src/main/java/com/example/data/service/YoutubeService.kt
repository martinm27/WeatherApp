package com.example.data.service

import com.example.data.model.youtube.api.ApiYoutubeQuery
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface YoutubeService {

    @GET("search")
    fun getVideoQuery(@Query("part") part: String, @Query("q") query: String, @Query("type") type: String,
                                 @Query("key") key: String): Single<ApiYoutubeQuery>
}
