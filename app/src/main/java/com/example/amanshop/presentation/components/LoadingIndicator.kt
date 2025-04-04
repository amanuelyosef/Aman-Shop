package com.example.amanshop.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.tooling.preview.Preview
import com.example.amanshop.presentation.ui.theme.Dimens
import com.example.amanshop.presentation.ui.theme.customPrimaryPink

@Composable
fun LoadingIndicator() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Dimens.MediumPadding),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(color = customPrimaryPink, strokeCap = StrokeCap.Round)
    }
}

@Preview(showBackground = true)
@Composable
private fun T() {
    LoadingIndicator()
}