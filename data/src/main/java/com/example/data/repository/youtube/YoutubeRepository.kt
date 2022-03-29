package com.example.data.repository.youtube

import com.example.data.model.youtube.domain.YoutubeQuery
import io.reactivex.Flowable

const val YOUTUBE_API_KEY = "AIzaSyBOm8DiBmsznpSBiqwVKQ3g6voEHaczzIE"
const val PART_SNIPPET = "snippet"
const val VIDEO_TYPE = "snippet"

interface YoutubeRepository {

    fun getVideoForQuery(query: String): Flowable<YoutubeQuery>
}
