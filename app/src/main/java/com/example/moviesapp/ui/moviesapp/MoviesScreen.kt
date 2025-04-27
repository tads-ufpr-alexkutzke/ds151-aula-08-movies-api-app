package com.example.moviesapp.ui.moviesapp

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.moviesapp.ui.theme.MoviesAppTheme

@Composable
fun MoviesScreen(
    movies: List<Movie>,
    onGoToMovieDetailsClick: (movieId:Int) -> Unit = {},
){
    MoviesList(
        movies = movies,
        onMovieClick = onGoToMovieDetailsClick
    )

}

@Preview
@Composable
fun MoviesScreenPreview(){
    MoviesAppTheme {
        MoviesScreen(
            movies = fourMovies,
            onGoToMovieDetailsClick = {},
        )
    }
}

