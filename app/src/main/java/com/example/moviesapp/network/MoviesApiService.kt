package com.example.moviesapp.network

import com.example.moviesapp.ui.moviesapp.Movie
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private const val BASE_URL =
    "http://192.168.25.209:9999/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface MoviesApiService {
    @GET("/movies")
    suspend fun getMovies(): List<Movie>

    @GET("/movies/{id}")
    suspend fun getMovie(@Path("id") id:Int): Movie
}

object MoviesApi {
    val retrofitService: MoviesApiService by lazy {
        retrofit.create(MoviesApiService::class.java)
    }
}