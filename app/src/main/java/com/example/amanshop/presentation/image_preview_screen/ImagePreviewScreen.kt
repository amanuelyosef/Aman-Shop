package com.example.amanshop.presentation.image_preview_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Share
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.amanshop.R
import com.example.amanshop.presentation.ui.theme.Dimens

@Composable
fun ImagePreviewScreen(
    imageList : List<Int>,
    navController: NavController,
    initialPage:Int = 0,
) {
    val pagerState = rememberPagerState(pageCount = { imageList.size}, initialPage = initialPage)

    Box(
        modifier = Modifier.fillMaxSize()
            .background(Color.Black.copy(alpha = 0.9f))
            .clickable (indication = null, interactionSource = null){ navController.popBackStack() }
        ,
        contentAlignment = Alignment.Center
    ){

        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
        ) {index->
            Image(
                painter = painterResource(id = imageList[index]),
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
                onClick = { navController.popBackStack() }
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_backarrow),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier
                        .size(Dimens.IconSizeMedium)
                )
            }

                Row(
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                ) {

                    IconButton(
                        onClick = { },
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.ic_add_to_cart),
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier
                                .size(Dimens.IconSizeMedium)
                        )
                    }

                    IconButton(
                        onClick = { },
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Share,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier
                                .size(Dimens.IconSizeMedium)

                        )
                    }
                }


        }

    }
}

@Preview(showBackground = true)
@Composable
private fun T() {
    ImagePreviewScreen(
        imageList = listOf(R.drawable.rcd_car),
        navController = rememberNavController()
    )
}
