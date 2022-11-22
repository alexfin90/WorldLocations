package com.softdream.exposicily.presentation.detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import coil.compose.AsyncImage
import com.softdream.exposicily.ErrorButton
import com.softdream.exposicily.LocationDetails
import com.softdream.exposicily.R


@Composable
fun LocationDetailScreen(state: LocationDetailScreenState, viewModel: ViewModel) {
    when {
        state.location != null -> {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(
                        dimensionResource(R.dimen.largePadding)
                    )
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                LocationDetails(
                    title = state.location.property.site,
                    message = state.location.property.shortDescription,
                    Modifier.padding(bottom = dimensionResource(id = R.dimen.extraLargePadding)),
                    Alignment.CenterHorizontally
                )
                AsyncImage(
                    model = state.location.property.imageUrl,
                    contentDescription = state.location.property.site,
                    modifier = Modifier.size(400.dp, 400.dp),
                    filterQuality = FilterQuality.High,
                    contentScale = ContentScale.Crop,
                    placeholder = painterResource(id = R.drawable.ic_launcher_foreground),
                )
                state.location.property.let {
                    Text(
                        text = it.location,
                        Modifier.padding(vertical = dimensionResource(id = R.dimen.mediumPadding))
                    )
                }
            }
        }

        state.isLoading -> {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator()
            }
        }
        state.error.isNotEmpty() -> {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                ErrorButton(errorText = state.error, viewModel)
            }
        }

    }

}