package com.oke.player.model.api

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.net.UnknownHostException
import javax.inject.Inject

interface MovieApiService {

    @GET("search/shows")
    suspend fun getMovieList(
        @Query("q") query: String
    ): Response<List<MovieListResponse>>
}

interface MovieApi {

    suspend fun getMovieList(text: String): ResultWrapper<Response<List<MovieListResponse>>>
}

class MovieApiImpl @Inject constructor(
) : MovieApi {

    private val apiService = create()

    override suspend fun getMovieList(text: String) = safeApiCall { apiService.getMovieList(text) }

    companion object {
        fun create(): MovieApiService {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(MoshiConverterFactory.create())
                .baseUrl("https://api.tvmaze.com/")
                .build()

            return retrofit.create(MovieApiService::class.java)
        }
    }
}

suspend fun <T> safeApiCall(apiCall: suspend () -> T): ResultWrapper<T> =
    withContext(Dispatchers.IO) {
        try {
            ResultWrapper.Success(apiCall.invoke())
        } catch (e: UnknownHostException) {
            ResultWrapper.NetworkError
        } catch (e: Throwable) {
            ResultWrapper.GenericError(e.message)
        }
    }

sealed class ResultWrapper<out T> {
    data class Success<out T>(val value: T) : ResultWrapper<T>()
    data class GenericError(val msg: String? = null) : ResultWrapper<Nothing>()
    object NetworkError : ResultWrapper<Nothing>()
}