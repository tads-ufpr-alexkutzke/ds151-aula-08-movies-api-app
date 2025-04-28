package com.example.moviesapp.ui.moviesapp

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.moviesapp.ui.theme.MoviesAppTheme

@Composable
fun MovieDetailsScreen(
    movieId: Int,
    moviesAppViewModel: MoviesAppViewModel = viewModel(),
    onGoBackClick: () -> Unit = {},
){
    val movie = moviesAppViewModel.movieDetails.collectAsState()

    LaunchedEffect(movieId) {
        moviesAppViewModel.getMovie(movieId)
    }

    if(movie.value == null) Text("Carregando ...")
    movie.value?.let{ movie ->
        MovieDetailsScreen(movie = movie)
    }
}

@Composable
fun MovieDetailsScreen(
    movie: Movie = fourMovies[0],
    onGoBackClick: () -> Unit = {},
){
    MovieItem(movie = movie)
}


@Preview
@Composable
fun MovieDetailsScreenPreview(){
    MoviesAppTheme {
       MovieDetailsScreen(
           movie = fourMovies[0]
       )
    }
}