package com.oke.player.model.api

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieListResponse(
    val score: Double?,
    val show: MovieBody?
) {

    @JsonClass(generateAdapter = true)
    data class MovieBody(
        val name: String?,
        val type: String?,
        val image: ImageBody?
    ) {

        @JsonClass(generateAdapter = true)
        data class ImageBody(
            val medium: String?,
            val original: String?
        )
    }
}