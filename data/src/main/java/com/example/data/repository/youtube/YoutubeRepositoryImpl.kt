package com.example.data.repository.youtube

import com.example.data.model.youtube.domain.YoutubeQuery
import com.example.data.service.YoutubeService
import io.reactivex.Flowable

class YoutubeRepositoryImpl(
    private val youtubeService: YoutubeService
) : YoutubeRepository {

    override fun getVideoForQuery(query: String): Flowable<YoutubeQuery> =
        youtubeService.getVideoQuery(PART_SNIPPET, query, VIDEO_TYPE, YOUTUBE_API_KEY)
            .map { YoutubeQuery(it.items.firstOrNull()?.youtubeItemId?.videoId ?: "") }
            .toFlowable()

}
