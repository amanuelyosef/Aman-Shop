package com.example.amanshop.presentation.components

import androidx.compose.animation.core.InfiniteRepeatableSpec
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import com.example.amanshop.presentation.ui.theme.Dimens

@Composable
fun ProductDisplayItemShimmer(modifier: Modifier = Modifier) {

    Column(
        modifier = Modifier
            .height(Dimens.ProductDisplayShimmerHeight)
            .padding(Dimens.SmallPadding)
    ) {
        Box(modifier = modifier
            .weight(1f)
            .fillMaxWidth()

        )

        Box(
            modifier = modifier
                .fillMaxWidth()
        )

        Box(
            modifier = modifier
                .fillMaxWidth(0.7f)
        )

        Box(
            modifier = modifier
                .fillMaxWidth(0.4f)
        )

        Box(
            modifier = modifier
                .fillMaxWidth(0.6f)

        )
    }
}

fun Modifier.shimmerEffect(size:IntSize, startOffset:Float) : Modifier = composed{


    background(
        brush = Brush.linearGradient(
            colors = listOf(
                Color(0xFFC2C2C2),
                Color(0xFF969696),
                Color(0xFFC2C2C2),
            ),

            start = Offset(startOffset, 0f),
            end = Offset(startOffset + size.width.toFloat(), size.height.toFloat())
        )
    )
}



@Preview(showBackground = true)
@Composable
private fun T() {
    var size by remember { mutableStateOf(IntSize.Zero) }
    val transition = rememberInfiniteTransition()
    val startOffset by transition.animateFloat(
        initialValue = -2 * size.width.toFloat(),
        targetValue = 2 * size.width.toFloat(),
        animationSpec = InfiniteRepeatableSpec(
            animation = tween(3000)
        )
    )

    ProductDisplayItemShimmer(
        modifier = Modifier
            .shimmerEffect(size, startOffset)
            .onGloballyPositioned { size = it.size }
    )
}