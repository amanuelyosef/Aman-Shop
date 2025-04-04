package com.example.amanshop.presentation.product_detail_screen

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandIn
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Share
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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ShareCompat
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.amanshop.R
import com.example.amanshop.presentation.cart_screen.components.CartListItemData
import com.example.amanshop.presentation.cart_screen.components.cartListData
import com.example.amanshop.presentation.chat_screen.components.ChatScreenData
import com.example.amanshop.presentation.chat_screen.components.ConversationsDataList
import com.example.amanshop.presentation.components.BottomNavigationBar
import com.example.amanshop.presentation.components.LoadingIndicator
import com.example.amanshop.presentation.components.NoInternetIconComponent
import com.example.amanshop.presentation.components.ProductGridDisplayItem
import com.example.amanshop.presentation.components.ProductDisplayListData
import com.example.amanshop.presentation.components.ReportScreenHint
import com.example.amanshop.presentation.components.ScreenRoute
import com.example.amanshop.presentation.components.openDialer
import com.example.amanshop.presentation.components.ProductDetailDataList
import com.example.amanshop.presentation.product_detail_screen.components.ProductViewComponent
import com.example.amanshop.presentation.message_list_screen.components.MessageListItem
import com.example.amanshop.presentation.message_list_screen.components.messageListData
import com.example.amanshop.presentation.ui.theme.Dimens
import com.example.amanshop.presentation.ui.theme.LocalString
import com.example.amanshop.presentation.ui.theme.customOnPrimaryContainerPink
import com.example.amanshop.presentation.ui.theme.customOrangeColor
import com.example.amanshop.presentation.ui.theme.customPrimaryContainerPink
import com.example.amanshop.presentation.ui.theme.customPrimaryPink
import com.example.amanshop.presentation.ui.theme.homeHeadlineTitle
import com.example.amanshop.presentation.ui.theme.nunitoFontFamily
import kotlinx.coroutines.delay
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailScreen(
    navController:NavController,
    itemId : String
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val item = ProductDetailDataList.find { it.id ==itemId }!!
    val pagerState = rememberPagerState(pageCount = { item.image.size})
    val localContext = LocalContext.current
    val lazyListState = rememberLazyStaggeredGridState()
    var callButtonText by remember { mutableStateOf("Call") }
    var isImageViewVisible by remember{ mutableStateOf(false) }
    val productAdURL = "I have found:\n" +
            "${item.title} \nFor ONLY ${item.price} birr on Aman Shop\n\n" +
            "download Aman Shop from - play.google.com/store/app/com.amanshop.app\n"
    val shareAdInitializer = ShareCompat.IntentBuilder(localContext)
        .setType("text/plain")
        .setText(productAdURL)

    val productStateListData = remember { mutableStateListOf(*(ProductDisplayListData).toTypedArray()) }
    var internetConnection by remember { mutableStateOf(true) }

    LaunchedEffect( lazyListState.firstVisibleItemIndex > productStateListData.size-6) {
        if(lazyListState.firstVisibleItemIndex > productStateListData.size-6){
            delay(5000L)
            productStateListData.addAll(ProductDisplayListData.map { it.copy(id = UUID.randomUUID().toString()) })
        }
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = "Details",
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
                                contentDescription = LocalString.notificationIcCd,
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
                                contentDescription = LocalString.notificationIcCd,
                                modifier = Modifier
                                    .size(Dimens.IconSizeMedium),
                            )
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

            }else{
                LazyVerticalStaggeredGrid (
                    columns = StaggeredGridCells.Fixed(2),
                    modifier = Modifier
                        .padding(it)
                        .nestedScroll(scrollBehavior.nestedScrollConnection),
                    state = lazyListState
                ) {

                    item(
                        span = StaggeredGridItemSpan.FullLine,
                        key = "Product Detail Image"
                    ) {
                        Box{
                            HorizontalPager(
                                state = pagerState,
                                modifier = Modifier
                                    .fillMaxWidth()
                            ) {index->

                                Image(
                                    painter = painterResource(item.image[index]),
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(Dimens.ProductDetailImageHeight)
                                        .clickable {
                                            val imageList = item.image.joinToString(",")
                                            isImageViewVisible = true
                                            //navController.navigate(ScreenRoute.ImagePreviewScreen.route + "/$imageList")
                                        }
                                )
                            }

                            Row(
                                modifier = Modifier
                                    .align(Alignment.BottomEnd)
                                    .padding(Dimens.SmallPadding)
                            ) {

                                IconButton(
                                    onClick = {
                                        val check = cartListData.find {
                                            it.id == itemId
                                        }
                                        if(check==null){
                                            cartListData.add(
                                                CartListItemData(
                                                    id  = itemId,
                                                    title = item.title,
                                                    img = item.image[0],
                                                    descriptionWords = listOf(item.condition, item.deliveryType),
                                                    price  = item.price,
                                                    location = item.productLocation,
                                                )
                                            )
                                            Toast.makeText(localContext, "Product is added to cart", Toast.LENGTH_LONG).show()
                                        }
                                        else{
                                            Toast.makeText(localContext, "Product is already added to cart", Toast.LENGTH_LONG).show()
                                        }
                                    },
                                    modifier = Modifier
                                        .padding(start = Dimens.MiniPadding)
                                        .clip(CircleShape)
                                        .size(Dimens.IconSizeMedium.plus(Dimens.SmallPadding))
                                        .background(Color.White)
                                ) {
                                    Icon(
                                        imageVector = ImageVector.vectorResource(R.drawable.ic_add_to_cart),
                                        contentDescription = null,
                                        modifier = Modifier.padding(Dimens.MiniPadding)
                                    )
                                }

                                IconButton(
                                    onClick = {
                                        shareAdInitializer.startChooser()
                                    },
                                    modifier = Modifier
                                        .padding(start = Dimens.MiniPadding)
                                        .clip(CircleShape)
                                        .size(Dimens.IconSizeMedium.plus(Dimens.SmallPadding))
                                        .background(Color.White)
                                ) {
                                    Icon(
                                        imageVector = Icons.Outlined.Share,
                                        contentDescription = null,
                                        modifier = Modifier.padding(Dimens.MiniPadding)
                                    )
                                }
                            }

                            Text(
                                text = "${pagerState.currentPage+1}/${item.image.size}",
                                fontSize = MaterialTheme.typography.bodySmall.fontSize,
                                color = Color.White,
                                fontWeight = FontWeight.Medium,
                                modifier = Modifier
                                    .align(Alignment.BottomStart)
                                    .padding(Dimens.SmallPadding)
                                    .clip(RoundedCornerShape(Dimens.SmallRoundRadius))
                                    .background(Color.Black.copy(0.7f))
                                    .padding(vertical = Dimens.OneDp, horizontal = Dimens.SmallRoundRadius)
                            )
                        }
                    }

                    item(
                        span = StaggeredGridItemSpan.FullLine,
                        key = "Product title and price with other description"
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(Dimens.SmallPadding)
                        ) {
                            Text(
                                text = item.postedDate,
                                fontSize = MaterialTheme.typography.bodySmall.fontSize,
                                color = Color.Gray,
                                modifier = Modifier
                                    .align(Alignment.End)
                            )

                            Text(
                                text = "ETB " + item.price,
                                color = customOnPrimaryContainerPink,
                                fontSize = MaterialTheme.typography.headlineMedium.fontSize,
                                fontWeight = FontWeight.ExtraBold,
                                modifier = Modifier
                                    .padding(vertical = Dimens.SmallPadding)
                            )

                            Text(
                                text = item.title,
                                fontWeight = FontWeight.Bold,
                                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                                modifier = Modifier.padding(bottom = Dimens.MiniPadding)
                            )

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = Dimens.SmallPadding)
                            ) {
                                Row(
                                    modifier = Modifier.weight(1f)
                                ) {

                                    Icon(
                                        imageVector = Icons.Outlined.LocationOn,
                                        contentDescription = null,
                                        modifier = Modifier.size(Dimens.IconSizeSmall)
                                    )

                                    Text(
                                        text = item.productLocation,
                                        fontSize = MaterialTheme.typography.bodySmall.fontSize,
                                        //color = Color.Gray
                                    )
                                }

                                Row(
                                    modifier = Modifier.weight(1f)
                                ) {

                                    Icon(
                                        painter = painterResource(R.drawable.ic_product_delivery),
                                        contentDescription = null,
                                        modifier = Modifier.size(Dimens.IconSizeSmall)
                                    )

                                    Text(
                                        text = item.deliveryType,
                                        fontSize = MaterialTheme.typography.bodySmall.fontSize,
                                        modifier = Modifier.padding(horizontal = Dimens.MiniPadding)
                                    )
                                }
                            }

                            Text(
                                text = item.numberOfView.toString() + " Views",
                                fontSize = MaterialTheme.typography.bodySmall.fontSize,
                                color = Color.Gray
                            )

                            Row {


                                Box(
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(
                                            vertical = Dimens.MediumPadding,
                                            horizontal = Dimens.MiniPadding
                                        )
                                        .clip(RoundedCornerShape(Dimens.MediumRoundRadius))
                                        .background(customPrimaryPink)
                                        .clickable {
                                            val check = ConversationsDataList.find {
                                                it.productId == item.id
                                            }
                                            if(check==null) {
                                                ConversationsDataList.add(0,
                                                    ChatScreenData(
                                                        productId = item.id,
                                                        chat = mutableListOf()
                                                    )
                                                )

                                                messageListData.add(0,
                                                    MessageListItem(
                                                        productId = item.id
                                                    )
                                                )
                                            }
                                            navController.navigate(ScreenRoute.ChatScreen.route+"/${item.id}")
                                        }
                                        .padding(vertical = Dimens.MediumPadding),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = "Chat",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                                        color = Color.White
                                    )
                                }

                                Box(
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(
                                            vertical = Dimens.MediumPadding,
                                            horizontal = Dimens.MiniPadding
                                        )
                                        .clip(RoundedCornerShape(Dimens.MediumRoundRadius))
                                        .background(customPrimaryContainerPink.copy(0.7f))
                                        .border(
                                            width = Dimens.OneDp.plus(Dimens.OneDp),
                                            color = customOnPrimaryContainerPink,
                                            shape = RoundedCornerShape(Dimens.MediumRoundRadius)
                                        )
                                        .clickable {
                                            openDialer(localContext,item.sellerData.phoneNumber)
                                            callButtonText = item.sellerData.phoneNumber
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
                        }
                    }

                    item(
                        span = StaggeredGridItemSpan.FullLine,
                        key = "spacer 1"
                    ){
                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(Dimens.MiniPadding)
                                .background(Color.LightGray.copy(0.6f))
                        )
                    }

                    items(
                        items =  item.definedDescription,
                        key = {it.second},
                    ){
                        Column(
                            modifier = Modifier
                                .padding(Dimens.SmallPadding)
                        ){
                            Text(
                                text = it.first,
                                fontSize = MaterialTheme.typography.bodySmall.fontSize
                            )
                            Text(
                                text = it.second,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                                modifier = Modifier.padding(bottom = Dimens.MiniPadding)
                            )
                        }
                    }

                    item(
                        span = StaggeredGridItemSpan.FullLine,
                        key = "spacer 2"
                    ){
                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = Dimens.SmallPadding)
                                .height(Dimens.MiniPadding)
                                .background(Color.LightGray.copy(0.6f))
                        )
                    }

                    item(
                        span = StaggeredGridItemSpan.FullLine,
                        key = "product Description"
                    ) {
                        var maxLine by remember { mutableStateOf(3) }

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable(
                                    onClick = {
                                        if (maxLine == 3) maxLine = 100
                                        else maxLine = 3
                                    },
                                    indication = null,
                                    interactionSource = null
                                )
                                .background(Color.White)
                                .padding(horizontal = Dimens.SmallPadding)
                        ) {

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {

                                Text(
                                    text = "Description",
                                    fontWeight = FontWeight.Bold,
                                    //fontSize = MaterialTheme.typography.titleSmall.fontSize,
                                    modifier = Modifier.padding(vertical = Dimens.SmallPadding)
                                )

                                IconButton(
                                    onClick = {
                                        if(maxLine==3) maxLine=100
                                        else maxLine=3
                                    },
                                    modifier = Modifier
                                        .size(Dimens.IconSizeMedium)
                                        .clip(CircleShape)
                                        .background(Color.LightGray)
                                        .padding(top = Dimens.OneDp, end = Dimens.OneDp)
                                ) {
                                    Icon(
                                        imageVector = ImageVector.vectorResource(
                                            if(maxLine==3) R.drawable.ic_drop_down else R.drawable.ic_drop_up
                                        ),
                                        contentDescription = null
                                    )
                                }
                            }

                            Text(
                                text = item.description,
                                maxLines = maxLine,
                                overflow = TextOverflow.Ellipsis,
                                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                                fontFamily = FontFamily.Default,
                                color = Color.Black
                            )
                        }
                    }

                    item(
                        span = StaggeredGridItemSpan.FullLine,
                        key = "spacer 3"
                    ){
                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = Dimens.MiniPadding, bottom = Dimens.SmallPadding)
                                .height(Dimens.MiniPadding)
                                .background(Color.LightGray.copy(0.6f))
                        )
                    }

                    item(
                        span = StaggeredGridItemSpan.FullLine,
                        key = "Seller Data display"
                    ){
                        Column(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.padding(bottom = Dimens.SmallPadding)
                                    .clickable(indication = null, interactionSource = null) {
                                        navController.navigate(ScreenRoute.SellerShopScreen.route+"/${item.sellerData.id}")
                                    }
                            ) {
                                Image(
                                    painter = painterResource(R.drawable.ic_profile_picture),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(Dimens.ProductDetailSellerImageSize)
                                )

                                Column(
                                    modifier = Modifier
                                        .weight(4f)
                                ) {
                                    Text(
                                        text = item.sellerData.name,
                                        fontWeight = FontWeight.Bold,
                                    )

                                    Text(
                                        text = "Last Seen: ${item.sellerData.lastSeen}",
                                        fontSize = MaterialTheme.typography.bodySmall.fontSize
                                    )

                                    Text(
                                        text = "${item.sellerData.durationOnApp} on Aman Shop",
                                        fontSize = MaterialTheme.typography.bodySmall.fontSize
                                    )
                                }

                                Box(
                                    modifier = Modifier
                                        .padding(horizontal = Dimens.SmallPadding)
                                        .border(
                                            width = Dimens.OneDp.plus(Dimens.OneDp),
                                            color = customOnPrimaryContainerPink,
                                            shape = RoundedCornerShape(Dimens.MediumRoundRadius)
                                        )
                                        .clip(RoundedCornerShape(Dimens.MediumRoundRadius))
                                        .background(customPrimaryContainerPink.copy(0.6f))
                                        .padding(
                                            vertical = Dimens.MiniPadding,
                                            horizontal = Dimens.LargePadding
                                        )
                                    ,
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = "Shop",
                                        color = customOnPrimaryContainerPink,
                                        fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier
                                            .clickable {
                                                navController.navigate(ScreenRoute.SellerShopScreen.route+"/${item.sellerData.id}")
                                            }
                                    )
                                }
                            }

                            Row {
                                Box(
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(
                                            vertical = Dimens.MediumPadding,
                                            horizontal = Dimens.MiniPadding
                                        )
                                        .clip(RoundedCornerShape(Dimens.MediumRoundRadius))
                                        .border(
                                            width = Dimens.OneDp.plus(Dimens.OneDp),
                                            color = customOrangeColor,
                                            shape = RoundedCornerShape(Dimens.MediumRoundRadius)
                                        )
                                        .clickable {
                                            val sellerId = item.sellerData.id
                                            navController.navigate(ScreenRoute.FeedbackScreen.route+"/${sellerId}")
                                        }
                                        .padding(vertical = Dimens.MediumPadding),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = "Feedback",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                                        color = customOrangeColor
                                    )
                                }

                                Box(
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(
                                            vertical = Dimens.MediumPadding,
                                            horizontal = Dimens.MiniPadding
                                        )
                                        .clip(RoundedCornerShape(Dimens.MediumRoundRadius))
                                        .border(
                                            width = Dimens.OneDp.plus(Dimens.OneDp),
                                            color = customOnPrimaryContainerPink,
                                            shape = RoundedCornerShape(Dimens.MediumRoundRadius)
                                        )
                                        .clickable {
                                            val screenHint = ReportScreenHint.Product.hint
                                            val reportedName = item.title
                                            navController.navigate(
                                                ScreenRoute.ReportScreen.route + "/${screenHint} ${reportedName}"
                                            )
                                        }
                                        .padding(vertical = Dimens.MediumPadding),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = "Report this ad",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                                        color = customOnPrimaryContainerPink
                                    )
                                }
                            }
                        }

                    }

                    item(
                        span = StaggeredGridItemSpan.FullLine,
                        key = "spacer 4"
                    ){
                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = Dimens.MiniPadding, bottom = Dimens.SmallPadding)
                                .height(Dimens.MiniPadding)
                                .background(Color.LightGray.copy(0.6f))
                        )
                    }

                    item(
                        span = StaggeredGridItemSpan.FullLine,
                    ){
                        Text(
                            text = "Related Ads",
                            fontWeight = FontWeight.Bold,
                            fontSize = MaterialTheme.typography.displaySmall.fontSize,
                            color = Color.Black,
                            modifier = Modifier
                                .padding(top = Dimens.SmallPadding, start = Dimens.SmallPadding, bottom = Dimens.SmallPadding)
                        )
                    }

                    items(items = productStateListData, key = {it.id}){ item->
                        ProductGridDisplayItem(
                            item =item,
                            modifier = Modifier
                                .clickable {
                                    navController.navigate(ScreenRoute.ProductDetailScreen.route+"/${item.id}")
                                }
                        )
                    }

                    item(span = StaggeredGridItemSpan.FullLine){
                        LoadingIndicator()
                    }
                }
            }
        }

        AnimatedVisibility(visible = isImageViewVisible,
            enter = fadeIn()+ expandIn(expandFrom = Alignment.Center) ,
            exit = shrinkOut(shrinkTowards = Alignment.Center) + fadeOut(),
        ) {
            ProductViewComponent(
                imageList = item.image,
                initialPage = 0,
                onAddToCartClicked = {
                    val check = cartListData.find {
                        it.id == itemId
                    }
                    if(check==null){
                        cartListData.add(
                            CartListItemData(
                                id  = itemId,
                                title = item.title,
                                img = item.image[0],
                                descriptionWords = listOf(item.condition, item.deliveryType),
                                price  = item.price,
                                location = item.productLocation,
                            )
                        )
                        Toast.makeText(localContext, "Product is added to cart", Toast.LENGTH_LONG).show()
                    }
                    else{
                        Toast.makeText(localContext, "Product is already added to cart", Toast.LENGTH_LONG).show()
                    }
                },
                onShareClicked = {
                    shareAdInitializer.startChooser()
                },
                onDismiss = {
                    isImageViewVisible = false
                }
            )
        }

    }
}


@Preview(showBackground = true)
@Composable
private fun T() {
    ProductDetailScreen(navController = rememberNavController(), itemId = "")
}