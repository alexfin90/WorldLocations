package com.softdream.worldlocations.presentation.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModel
import coil.compose.AsyncImage
import com.softdream.worldlocations.ErrorButton
import com.softdream.worldlocations.R
import com.softdream.worldlocations.domain.Location


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
                    state.location,
                    Modifier.padding(bottom = dimensionResource(id = R.dimen.extraLargePadding)),
                    Alignment.CenterHorizontally
                )
                AsyncImage(
                    model = state.location.flagsProperty.pngURL,
                    contentDescription = state.location.nameProperty.common,
                    filterQuality = FilterQuality.High,
                    contentScale = ContentScale.Crop
                )
                state.location.nameProperty.common.let {
                    if (it != null) {
                        Text(
                            text = stringResource(id = R.string.Flag_of) + " "+ state.location.nameProperty.common ,
                            Modifier.padding(vertical = dimensionResource(id = R.dimen.mediumPadding))
                        )
                    }
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