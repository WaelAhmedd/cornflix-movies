package com.app.movies.features.home.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.app.movies.R
import com.app.movies.core.data.models.Movie
import com.app.movies.core.utils.ImageUtils

@Composable
fun MovieItem(
    movie: Movie, // Pass the movie object
    isInWatchlist: Boolean, // Indicates if the movie is in the watchlist
    onToggleWatchlist: (Boolean) -> Unit,
    navigateToMovies: (String) -> Unit,
    // Callback for toggling watchlist

    modifier: Modifier = Modifier
) {
    val posterUrl = ImageUtils.constructPosterUrl(movie.posterPath)

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(Color.DarkGray, shape = RoundedCornerShape(8.dp))
            .padding(8.dp)
            .clickable { navigateToMovies.invoke(movie.id.toString()) }
    ) {
        // Poster Image
        Image(
            painter = rememberImagePainter(data = posterUrl),
            contentDescription = "Movie Poster",
            modifier = Modifier
                .width(120.dp)
                .height(180.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color.LightGray),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(16.dp))

        // Movie Details
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Title
            Text(
                text = movie.title,
                style = MaterialTheme.typography.titleLarge,
                color = Color.White,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            // Overview
            Text(
                text = movie.overview,
                style = MaterialTheme.typography.titleMedium,
                color = Color.Gray,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(vertical = 4.dp)
            )

            // Release Date
            Text(
                text = movie.releaseDate,
                style = MaterialTheme.typography.titleMedium,
                color = Color.Gray,
                modifier = Modifier.padding(vertical = 4.dp)
            )

            // Watchlist Toggle Button
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = { onToggleWatchlist(!isInWatchlist) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isInWatchlist) Color.Gray else Color.Red
                )
            ) {
                Text(
                    text = if (isInWatchlist) "Remove from Watchlist" else "Add to Watchlist",
                    color = Color.White
                )
            }
        }
    }
}



