package com.softdream.worldlocations.presentation.detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.softdream.worldlocations.ErrorButton
import com.softdream.worldlocations.R
import com.softdream.worldlocations.domain.Location


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun LocationDetailScreen(state: LocationDetailScreenState, viewModel: ViewModel) {
    when {
        state.location != null -> {
            val point = LatLng(state.location.lat ?: 0.0,
                state.location.lng ?: 0.0)
            val cameraPositionState = rememberCameraPositionState {
                position = CameraPosition.fromLatLngZoom(point, 2f)
            }
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
                    state.location,
                    Modifier.padding(bottom = dimensionResource(id = R.dimen.extraLargePadding)),
                    Alignment.CenterHorizontally
                )
                GlideImage(
                    model = state.location.flagsProperty.pngURL,
                    contentDescription = state.location.nameProperty.common,
                    contentScale = ContentScale.Crop
                )
                state.location.nameProperty.common.let {
                    Text(
                        text = stringResource(id = R.string.Flag_of) +" "+ state.location.nameProperty.common ,
                        Modifier.padding(vertical = dimensionResource(id = R.dimen.mediumPadding))
                    )
                }
                GoogleMap(
                    modifier = Modifier.fillMaxHeight(),
                    cameraPositionState = cameraPositionState
                ) {
                    Marker(
                        state = MarkerState(position = point),
                        title = state.location.nameProperty.common,
                        snippet = state.location.nameProperty.common
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

@Composable
fun LocationDetails(
    location : Location,
    modifier: Modifier,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start
) {
    Column(modifier = modifier, horizontalAlignment = horizontalAlignment) {
        Text(text = location.nameProperty.official, style = MaterialTheme.typography.titleMedium)
        Text(text =  stringResource(id = R.string.LocationCode) +" "+ location.id,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .alpha(0.8f)
                .padding(top = dimensionResource(id = R.dimen.mediumPadding))
        )
        Text(text =  stringResource(id = R.string.Capital) +" "+ location.capital,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .alpha(0.8f)
                .padding(top = dimensionResource(id = R.dimen.mediumPadding))
        )
        Text(text =  stringResource(id = R.string.Population) +" "+ location.population,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .alpha(0.8f)
                .padding(top = dimensionResource(id = R.dimen.mediumPadding))
        )
        Text(text =  stringResource(id = R.string.Area) +" "+ location.area,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .alpha(0.8f)
                .padding(top = dimensionResource(id = R.dimen.mediumPadding))
        )
        Text(text =  stringResource(id = R.string.Region) +" "+ location.region,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .alpha(0.8f)
                .padding(top = dimensionResource(id = R.dimen.mediumPadding))
        )
        Text(text =  stringResource(id = R.string.Subregion) +" "+ location.subregion,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .alpha(0.8f)
                .padding(top = dimensionResource(id = R.dimen.mediumPadding))
        )
    }
}