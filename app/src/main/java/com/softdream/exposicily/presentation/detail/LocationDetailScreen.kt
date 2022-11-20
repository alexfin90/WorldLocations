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
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {

            LocationDetails(
                title = item.property.site,
                message = item.property.shortDescription,
                Modifier.padding(bottom = dimensionResource(id = R.dimen.extraLargePadding)),
                Alignment.CenterHorizontally
            )

            AsyncImage(
                model = item.property.imageUrl,
                contentDescription = item.property.site,
                modifier = Modifier.size(400.dp, 400.dp),
                filterQuality = FilterQuality.High,
                contentScale = ContentScale.Crop,
                placeholder =  painterResource(id = R.drawable.ic_launcher_foreground),
            )


            Text(
                text = item.property.location,
                Modifier.padding(vertical = dimensionResource(id = R.dimen.mediumPadding))
            )
        }
    } else {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
            CircularProgressIndicator()
        }
    }
}