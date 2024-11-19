package com.app.movies.features.moviedetails.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import com.app.movies.core.data.models.CastMember
import com.app.movies.core.data.models.CrewMember
import com.app.movies.core.data.models.Movie
import com.app.movies.core.data.models.MovieDetailsResponse
import com.app.movies.core.utils.ImageUtils.constructPosterUrl

@Composable
fun CastSection(actors: List<CastMember>, directors: List<CrewMember>) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Stars from Similar Movies",
            style = MaterialTheme.typography.titleLarge,
            color = Color.White,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Actors
        Text(text = "Actors", style = MaterialTheme.typography.titleMedium, color = Color.White)
        LazyRow {
            items(actors) { actor ->
                PersonCard(
                    name = actor.name,
                    imageUrl = constructPosterUrl(actor.profilePath)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Directors
        Text(text = "Directors", style = MaterialTheme.typography.titleMedium,color = Color.White)
        LazyRow {
            items(directors) { director ->
                PersonCard(
                    name = director.name,
                    imageUrl = constructPosterUrl(director.profilePath)
                )
            }
        }
    }
}

@Composable
fun PersonCard(name: String, imageUrl: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .width(80.dp)
            .padding(8.dp)
    ) {
        Image(
            painter = rememberImagePainter(data = imageUrl),
            contentDescription = "$name's Profile Picture",
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            color = Color.White,
            text = name,
            style = MaterialTheme.typography.titleMedium,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}

