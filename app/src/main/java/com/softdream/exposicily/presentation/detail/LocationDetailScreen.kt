package com.softdream.exposicily.presentation.detail

import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.res.dimensionResource
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.softdream.exposicily.LocationDetails
import com.softdream.exposicily.R


@Composable
fun LocationDetailScreen() {
    val viewModel: LocationDetailViewModel = viewModel()
    val item = viewModel.state.value
    if (item != null) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(
                    dimensionResource(R.dimen.largePadding)
                )
        ) {
            LocationDetails(
                title = item.property.site,
                message = item.property.shortDescription,
                Modifier.padding(bottom = dimensionResource(id = R.dimen.extraLargePadding)),
                Alignment.CenterHorizontally
            )
            AsyncImage(
                item.property.imageUrl, contentDescription = item.property.site,
                modifier = Modifier.fillMaxWidth(),
                filterQuality = FilterQuality.High,

                )
            Text("More info coming soon!")
        }
    } else {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
            CircularProgressIndicator()
        }
    }
}