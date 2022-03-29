package com.example.search.usecase

import com.example.core.usecase.QueryUseCaseWithParam
import com.example.data.model.youtube.domain.YoutubeQuery
import com.example.data.repository.youtube.YoutubeRepository
import io.reactivex.Flowable

class QueryYoutubeVideo(private val youtubeRepository: YoutubeRepository) :
    QueryUseCaseWithParam<String, YoutubeQuery> {

    override fun invoke(query: String): Flowable<YoutubeQuery> =
        youtubeRepository.getVideoForQuery(query)
}
