package com.example.moviesapp.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.moviesapp.ui.moviesapp.Movie
import com.example.moviesapp.ui.moviesapp.MovieDetailsScreen
import com.example.moviesapp.ui.moviesapp.MoviesAppViewModel
import com.example.moviesapp.ui.moviesapp.MoviesScreen
import com.example.moviesapp.ui.theme.MoviesAppTheme

@Composable
fun MoviesApp(
    navController: NavHostController = rememberNavController(),
    moviesAppViewModel: MoviesAppViewModel = viewModel(),
){
    Scaffold (modifier = Modifier.fillMaxSize()) { innerPadding ->
        NavHost(
            modifier = Modifier.padding(innerPadding),
            navController = navController,
            startDestination = "movies",
        ) {
            composable("movies") {
                if(moviesAppViewModel.movies.isNotEmpty()){
                    MoviesScreen(
                        movies = moviesAppViewModel.movies,
                        onGoToMovieDetailsClick = { movieId ->
                            navController.navigate("movieDetails/$movieId")
                        })
                }
                else{
                    Text("Carregando ...")
                }
            }
            composable(
                route="movieDetails/{movieId}",
                arguments = listOf(
                    navArgument ("movieId") {
                        defaultValue = 0
                        type = NavType.IntType
                    }
                )
            ) { backStackEntry ->
                val movieId:Int = backStackEntry.arguments?.getInt("movieId") ?: 1
                val movie = moviesAppViewModel.movieDetails.collectAsState()

                LaunchedEffect(movieId) {
                    moviesAppViewModel.getMovie(movieId)
                }

                if(movie.value == null) Text("Carregando ...")
                else{
                    movie.value?.let {
                        MovieDetailsScreen(
                            movie = it,
                            onGoBackClick = {
                                navController.navigate("movies")
                            }
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun MoviesAppPreview(){
    MoviesAppTheme {
        MoviesApp(moviesAppViewModel = MoviesAppViewModel(fake = true))
    }
}