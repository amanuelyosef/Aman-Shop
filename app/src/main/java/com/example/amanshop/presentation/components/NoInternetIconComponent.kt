package com.example.amanshop.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.example.amanshop.R
import com.example.amanshop.presentation.ui.theme.Dimens

@Composable
fun NoInternetIconComponent(
    onRetryClicked : ()->Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
            .padding(vertical = Dimens.LargePadding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_no_internet_connection),
            contentDescription = "",
            tint = Color.LightGray,
            modifier = Modifier.size(Dimens.NoInternetIconSize)
        )

        Text(
            text = "No internet connection",
            color = Color.LightGray,
            fontSize = MaterialTheme.typography.bodyLarge.fontSize
        )

        Button(
            onClick = {
                onRetryClicked()
            },
            modifier = Modifier.padding(vertical = Dimens.LargePadding)
        ) {
            Text(
                text = "Retry"
            )
        }
    }
}