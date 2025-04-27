package com.example.moviesapp.ui.moviesapp

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.network.MoviesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MoviesAppViewModel(val fake: Boolean = false): ViewModel() {
    private var _movies = mutableStateListOf<Movie>()
    val movies: List<Movie>
        get() = _movies

    init {
        if(fake) _movies.addAll(fourMovies)
        else{
            viewModelScope.launch {
                val movies = MoviesApi.retrofitService.getMovies()
                _movies.addAll(movies)
            }
        }
    }

    fun getMovie(movieId:Int): Movie?{
       return _movies.find {movie -> movie.id == movieId }
    }
}