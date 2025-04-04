package com.example.amanshop.presentation.chat_screen.components

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import coil.compose.AsyncImage
import com.example.amanshop.R
import com.example.amanshop.presentation.ui.theme.Dimens

@Composable
fun ImageviewComponent(
    imageList: List<Uri>,
    initialPage : Int,
    onDismissRequest : () -> Unit
)  {
    val pagerState = rememberPagerState(pageCount = { imageList.size}, initialPage = initialPage)

    Box(
        modifier = Modifier.fillMaxSize()
            .background(Color.Black.copy(alpha = 0.9f))
            .clickable { onDismissRequest() }
        ,
        contentAlignment = Alignment.Center
    ){

        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
        ) {index->
            AsyncImage(
                model = imageList[index],
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.fillMaxWidth()
            )
        }

        Text(
            text = "${pagerState.currentPage + 1}/${imageList.size}",
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(vertical = Dimens.LargePadding),
            color = Color.White,
            fontWeight = FontWeight.SemiBold,
            fontSize = MaterialTheme.typography.bodyMedium.fontSize
        )

        Box(
            modifier = Modifier
                .align(Alignment.TopStart)
                .fillMaxWidth()
                .padding(Dimens.MiniPadding),
        ) {

            IconButton(
                onClick = { onDismissRequest()}
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_backarrow),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier
                        .size(Dimens.IconSizeMedium)
                )
            }

        }

    }
}