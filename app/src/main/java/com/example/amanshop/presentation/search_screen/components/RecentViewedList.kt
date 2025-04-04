package com.example.amanshop.presentation.search_screen.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.amanshop.R
import com.example.amanshop.presentation.components.ScreenRoute
import com.example.amanshop.presentation.components.TrendFashionListData
import com.example.amanshop.presentation.ui.theme.Dimens
import com.example.amanshop.presentation.ui.theme.customPrimaryContainerPink
import com.example.amanshop.presentation.ui.theme.customPrimaryPink

@Composable
fun RecentViewedList(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    var hasElement by remember { mutableStateOf(recentlyViewedListData.isNotEmpty()) }
    val title = "Recently Viewed"

    AnimatedVisibility(visible = hasElement){

        Column(
            modifier = Modifier
                .background(customPrimaryContainerPink.copy(0.6f))
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
                    .clickable {
                        navController.navigate(ScreenRoute.TrendProductListScreen.route + "/${title}")
                    }
                ,
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Text(
                    text = title,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                    color = Color.Black,
                    modifier = Modifier
                        .weight(1f)
                        .padding(top = Dimens.SmallPadding, start = Dimens.SmallPadding)
                )

                Text(
                    text = "Clear All",
                    color = Color.Red,
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                    fontFamily = FontFamily.Default,
                    modifier = Modifier
                        .padding(horizontal = Dimens.SmallPadding)
                        .clip(RoundedCornerShape(Dimens.MediumRoundRadius))
                        .clickable {
                            hasElement=false
                            recentlyViewedListData.clear()
                        }
                        .padding(Dimens.MiniPadding)
                )
            }

            LazyRow(
                contentPadding = PaddingValues(Dimens.SmallPadding),
                verticalAlignment = Alignment.CenterVertically,

                ) {
                itemsIndexed(
                    items = recentlyViewedListData,
                    key = { _, item -> item.id }) { index, item ->
                    Column(
                        modifier = modifier
                            .padding(end = Dimens.SmallPadding)
                            .clip(RoundedCornerShape(Dimens.MediumRoundRadius))
                            .background(Color.White)
                            .clickable {
                                navController.navigate(ScreenRoute.ProductDetailScreen.route + "/${item.id}")
                            }
                    ) {
                        Image(
                            painter = painterResource(item.img),
                            contentDescription = null,
                            contentScale = ContentScale.Fit,

                            modifier = Modifier
                                .size(Dimens.RecentViewListImageSize)
                                .align(Alignment.CenterHorizontally)
                        )

                        Text(
                            text = "ETB " + item.price,
                            fontWeight = FontWeight.Bold,
                            fontSize = MaterialTheme.typography.bodySmall.fontSize,
                            color = customPrimaryPink,
                            modifier = Modifier
                                .padding(horizontal = Dimens.MiniPadding)
                        )
                    }

                    if (TrendFashionListData.size == index + 1) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,

                            ) {
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier.padding(end = Dimens.MediumPadding)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(Dimens.IconSizeLarge)
                                        .clip(CircleShape)
                                        .background(customPrimaryContainerPink.copy(red = 0.9f))
                                        .clickable {
                                            navController.navigate(ScreenRoute.TrendProductListScreen.route + "/${title}")
                                        }
                                )
                                Icon(
                                    imageVector = ImageVector.vectorResource(R.drawable.ic_frontarrow),
                                    contentDescription = null,
                                )
                            }

                            Text(
                                text = "See More",
                                fontWeight = FontWeight.Bold,
                                color = customPrimaryPink,
                                fontSize = MaterialTheme.typography.bodySmall.fontSize,
                                modifier = Modifier
                                    .padding(vertical = Dimens.SmallPadding)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun T() {
    RecentViewedList(navController = rememberNavController())
}