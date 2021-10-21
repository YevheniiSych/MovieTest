package com.movietest.presentation.ui.movie_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.movietest.R
import com.movietest.common.Constants.IMAGE_URL
import com.movietest.domain.model.Movie
import com.skydoves.landscapist.glide.GlideImage


@Composable
fun MovieItem(movie: Movie) {
    Card(
        elevation = 20.dp,
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .clickable {
            }
            .padding(10.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Color.White)
    ) {
        ConstraintLayout(
            modifier = Modifier
                .padding(10.dp)
        ) {
            val (poster, title, rating) = createRefs()
            Poster(
                imageUrl = IMAGE_URL + movie.posterPath,
                modifier = Modifier
                    .constrainAs(poster) {
                        top.linkTo(parent.top)
                    }
                    .fillMaxWidth()
                    .fillMaxHeight(.9f)
            )
            Title(
                title = movie.title ?: "",
                modifier = Modifier
                    .constrainAs(title) {
                        linkTo(
                            top = poster.bottom,
                            bottom = parent.bottom,
                            start = parent.start,
                            end = parent.end,
                            verticalBias = 0.8f
                            )
                    }
            )
        }
    }
}

@Composable
private fun Poster(imageUrl: String, modifier: Modifier = Modifier) {
    GlideImage(
        imageModel = imageUrl,
        contentScale = ContentScale.Crop,
        placeHolder = ImageBitmap.imageResource(id = R.drawable.placeholder_image),
        modifier = modifier
    )
}

@Composable
private fun Title(title: String, modifier: Modifier) {
    Text(
        maxLines = 2,
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        text = title,
        modifier = modifier
    )
}
