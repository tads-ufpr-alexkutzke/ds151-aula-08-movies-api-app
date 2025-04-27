package com.example.moviesapp.ui.moviesapp

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.moviesapp.ui.theme.MoviesAppTheme

@Composable
fun MovieDetailsScreen(
    movie: Movie,
    onGoBackClick: () -> Unit = {},
){
    Text(text = movie.title)
}

@Preview
@Composable
fun MovieDetailsScreenPreview(){
    MoviesAppTheme {
       MovieDetailsScreen(
           movie = fourMovies[0],
           onGoBackClick = {}
       )
    }
}