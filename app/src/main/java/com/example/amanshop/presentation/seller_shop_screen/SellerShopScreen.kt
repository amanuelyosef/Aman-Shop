package com.example.amanshop.presentation.seller_shop_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.amanshop.R
import com.example.amanshop.presentation.components.BottomNavigationBar
import com.example.amanshop.presentation.components.LoadingIndicator
import com.example.amanshop.presentation.components.NoInternetIconComponent
import com.example.amanshop.presentation.components.ProductGridDisplayItem
import com.example.amanshop.presentation.components.ProductDisplayListData
import com.example.amanshop.presentation.components.ReportScreenHint
import com.example.amanshop.presentation.components.ScreenRoute
import com.example.amanshop.presentation.components.SellersInfoList
import com.example.amanshop.presentation.components.openDialer
import com.example.amanshop.presentation.ui.theme.Dimens
import com.example.amanshop.presentation.ui.theme.customOnPrimaryContainerPink
import com.example.amanshop.presentation.ui.theme.customPrimaryContainerPink
import com.example.amanshop.presentation.ui.theme.homeHeadlineTitle
import com.example.amanshop.presentation.ui.theme.nunitoFontFamily
import kotlinx.coroutines.delay
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SellerShopScreen(
    navController: NavController,
    sellerId : String
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val sellerInfo = SellersInfoList.find{it.id == sellerId}!!
    var optionExpanded by remember { mutableStateOf(false) }
    var callButtonText by remember { mutableStateOf("Call") }
    val localContext = LocalContext.current
    val lazyListState = rememberLazyStaggeredGridState()
    val productStateListData = remember { mutableStateListOf(*(ProductDisplayListData).toTypedArray()) }
    var internetConnection by remember { mutableStateOf(true) }

    LaunchedEffect( lazyListState.firstVisibleItemIndex > productStateListData.size-4) {
        if(lazyListState.firstVisibleItemIndex > productStateListData.size-4){
            delay(5000L)
            productStateListData.addAll(ProductDisplayListData.map { it.copy(id = UUID.randomUUID().toString()) })

        }
    }


    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Shop",
                        fontSize = MaterialTheme.typography.headlineLarge.fontSize,
                        fontWeight = FontWeight.Bold,
                        color = homeHeadlineTitle,
                        fontFamily = nunitoFontFamily
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.ic_backarrow),
                            contentDescription = null,
                            modifier = Modifier
                                .size(Dimens.IconSizeMedium),
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = {
                            navController.navigate(ScreenRoute.SearchScreen.route)
                        }
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.ic_lucide_search),
                            contentDescription = null,
                            modifier = Modifier
                                .size(Dimens.IconSizeMedium),
                        )
                    }

                    Box {

                        IconButton(
                            onClick = {optionExpanded=true }
                        ) {
                            Icon(
                                imageVector = Icons.Default.MoreVert,
                                contentDescription = null,
                                modifier = Modifier
                                    .size(Dimens.IconSizeMedium),
                            )
                        }

                        DropdownMenu(
                            expanded =optionExpanded,
                            onDismissRequest = {optionExpanded=false}
                        ) {
                            DropdownMenuItem(
                                text = {
                                    Text(
                                        text = "Share",
                                        fontSize = MaterialTheme.typography.bodyMedium.fontSize
                                    )
                                },
                                leadingIcon = {
                                    Icon(
                                        imageVector = Icons.Outlined.Share,
                                        contentDescription = null
                                    )
                                },
                                onClick = {
                                    optionExpanded = false
                                }
                            )

                            DropdownMenuItem(
                                text = {
                                    Text(
                                        text = "Report",
                                        fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                                        color = Color.Red.copy(0.8f)
                                    )
                                },
                                leadingIcon = {
                                    Icon(
                                        imageVector = ImageVector.vectorResource(R.drawable.ic_outlined_flag),
                                        contentDescription = null,
                                        tint = Color.Red.copy(0.8f)
                                    )
                                },
                                onClick = {
                                    val screenHint = ReportScreenHint.Seller.hint
                                    val reportedName = sellerInfo.name
                                    optionExpanded = false
                                    navController.navigate(
                                        ScreenRoute.ReportScreen.route +"/${screenHint} ${reportedName}"
                                    )
                                }
                            )
                        }
                    }
                },
                scrollBehavior = scrollBehavior,
                colors = TopAppBarDefaults.topAppBarColors(
                    scrolledContainerColor = Color.White
                )
            )
        },
        bottomBar = {
            BottomNavigationBar(
                navController = navController,
                selected = 5
            )
        }

    ) {
        if(!internetConnection){
            NoInternetIconComponent {
                internetConnection = true
            }

        }else {
            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Fixed(2),
                modifier = Modifier
                    .padding(it)
                    .nestedScroll(scrollBehavior.nestedScrollConnection),
                state = lazyListState
            ) {

                item(
                    span = StaggeredGridItemSpan.FullLine,
                    key = "Shop PP and background"
                ) {

                    Box(
                        modifier = Modifier
                            .height(
                                Dimens.ShopBackgroundImageHeight.plus(
                                    Dimens.ShopProfilePictureHeight.times(
                                        0.5f
                                    )
                                )
                            )
                    ) {
                        Image(
                            painter = painterResource(R.drawable.display_shop_background),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxWidth()
                                .height(Dimens.ShopBackgroundImageHeight)
                        )

                        Box(
                            modifier = Modifier
                                .align(Alignment.BottomStart)
                                .padding(horizontal = Dimens.MediumPadding)
                                .size(Dimens.ShopProfilePictureHeight)
                                .clip(CircleShape)
                                .border(
                                    width = Dimens.SmallRoundRadius,
                                    color = Color.White,
                                    shape = CircleShape
                                ),
                            contentAlignment = Alignment.BottomCenter
                        ) {
                            Image(
                                painter = painterResource(sellerInfo.image),
                                contentScale = ContentScale.Crop,
                                contentDescription = null,
                                modifier = Modifier.size(Dimens.ShopProfilePictureHeight)
                            )
                        }
                    }
                }

                item(
                    span = StaggeredGridItemSpan.FullLine,
                    key = "Shop Information"
                ) {
                    Column(
                        modifier = Modifier
                            .padding(
                                start = Dimens.MediumPadding,
                                end = Dimens.MediumPadding,
                                bottom = Dimens.MediumPadding
                            )
                    ) {
                        Text(
                            text = sellerInfo.shopName,
                            fontSize = MaterialTheme.typography.headlineMedium.fontSize,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = "Location: " + sellerInfo.location,
                            fontSize = MaterialTheme.typography.bodySmall.fontSize,
                            color = Color.DarkGray
                        )

                        Text(
                            text = "${sellerInfo.durationOnApp} on Aman",
                            fontSize = MaterialTheme.typography.bodySmall.fontSize,
                            color = Color.DarkGray
                        )

                        Row(
                            modifier = Modifier.padding(top = Dimens.MediumPadding)
                        ) {

                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(Dimens.MediumPadding)
                                    .clip(RoundedCornerShape(Dimens.MediumRoundRadius))
                                    .background(customPrimaryContainerPink.copy(0.7f))
                                    .border(
                                        width = Dimens.OneDp.plus(Dimens.OneDp),
                                        color = customOnPrimaryContainerPink,
                                        shape = RoundedCornerShape(Dimens.MediumRoundRadius)
                                    )
                                    .clickable {
                                        openDialer(localContext, sellerInfo.phoneNumber)
                                        callButtonText = sellerInfo.phoneNumber
                                    }
                                    .padding(vertical = Dimens.MediumPadding),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = callButtonText,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                                    color = customOnPrimaryContainerPink
                                )
                            }
                        }

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(Dimens.MediumRoundRadius))
                                .background(Color.LightGray)
                                .clickable {
                                    val sellerId = sellerInfo.id
                                    navController.navigate(ScreenRoute.FeedbackScreen.route + "/${sellerId}")
                                }
                                .padding(Dimens.MediumPadding),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Feedback(${sellerInfo.feedbackList.size})",
                                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                                fontWeight = FontWeight.Bold
                            )

                            Icon(
                                imageVector = ImageVector.vectorResource(R.drawable.ic_frontarrow),
                                contentDescription = null
                            )
                        }
                    }
                }

                item(
                    span = StaggeredGridItemSpan.FullLine
                ) {
                    Text(
                        text = "All Items",
                        fontWeight = FontWeight.Bold,
                        fontSize = MaterialTheme.typography.displaySmall.fontSize,
                        color = Color.Black,
                        modifier = Modifier
                            .padding(Dimens.SmallPadding)
                    )
                }

                items(items = productStateListData, key = { it.id }) { item ->
                    ProductGridDisplayItem(
                        item = item,
                        modifier = Modifier
                            .clickable {
                                navController.navigate(ScreenRoute.ProductDetailScreen.route + "/${item.id}")
                            }
                    )
                }

                item(span = StaggeredGridItemSpan.FullLine) {
                    LoadingIndicator()
                }
            }
        }
    }
}

@Preview
@Composable
private fun T() {
    SellerShopScreen(
        navController = rememberNavController(),
        sellerId = SellersInfoList[0].id
    )
}