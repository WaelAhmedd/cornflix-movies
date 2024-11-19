package com.app.movies.features.moviedetails.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import com.app.movies.core.data.models.MovieDetailsResponse
import com.app.movies.core.utils.ImageUtils.constructPosterUrl

@Composable
fun MovieDetailsHeader(
    movie: MovieDetailsResponse,
    isInWatchlist: Boolean,
    onToggleWatchlist: () -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {

        Image(
            painter = rememberImagePainter(
                data = constructPosterUrl(movie.posterPath)
            ),
            contentDescription = "Movie Poster",
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .clip(RoundedCornerShape(16.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            color = Color.White,
            text = movie.title,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = movie.tagline ?: "",
            style = MaterialTheme.typography.titleMedium,
            color = Color.Gray,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = movie.overview,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.White,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Text(
            text = "Release Date: ${movie.releaseDate}",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.White
        )
        Text(
            text = "Revenue: ${movie.revenue ?: "N/A"}",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.White
        )
        Text(
            text = "Status: ${movie.status}",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onToggleWatchlist,
            colors = ButtonDefaults.buttonColors(
                containerColor = if (isInWatchlist) Color.Gray else Color.Red
            ),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(
                color = Color.White,
                text = if (isInWatchlist) "Remove from Watchlist" else "Add to Watchlist"
            )
        }
    }
}

