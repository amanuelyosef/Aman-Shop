package com.example.amanshop.presentation.cart_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.amanshop.R
import com.example.amanshop.presentation.cart_screen.components.CartListItemData
import com.example.amanshop.presentation.cart_screen.components.cartListData
import com.example.amanshop.presentation.components.BottomNavigationBar
import com.example.amanshop.presentation.components.LoadingIndicator
import com.example.amanshop.presentation.components.NoInternetIconComponent
import com.example.amanshop.presentation.components.ProductGridDisplayItem
import com.example.amanshop.presentation.components.ProductDisplayListData
import com.example.amanshop.presentation.components.ScreenRoute
import com.example.amanshop.presentation.ui.theme.Dimens
import com.example.amanshop.presentation.ui.theme.LocalString
import com.example.amanshop.presentation.ui.theme.customPrimaryPink
import com.example.amanshop.presentation.ui.theme.homeHeadlineTitle
import com.example.amanshop.presentation.ui.theme.nunitoFontFamily
import kotlinx.coroutines.delay
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(
    navController: NavController
) {

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val cartListDataState = remember { mutableStateListOf(*cartListData.toTypedArray()) }
    val productStateListData = remember { mutableStateListOf(*(ProductDisplayListData).toTypedArray()) }
    var internetConnection by remember { mutableStateOf(true) }

    val lazyListState = rememberLazyStaggeredGridState()

    LaunchedEffect(lazyListState.firstVisibleItemIndex > productStateListData.size-4) {
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
                        text = "Cart",
                        fontSize = MaterialTheme.typography.headlineLarge.fontSize,
                        fontWeight = FontWeight.Bold,
                        color = homeHeadlineTitle,
                        fontFamily = nunitoFontFamily
                    )
                },

                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.navigate(ScreenRoute.HomeScreen.route)
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
                scrollBehavior = scrollBehavior,
                colors = TopAppBarDefaults.topAppBarColors(
                    scrolledContainerColor = Color.White
                )
            )
        },
        bottomBar = {
            BottomNavigationBar(
                navController = navController,
                selected = 1
            )
        }
    ) {

        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            modifier = Modifier.padding(it).nestedScroll(scrollBehavior.nestedScrollConnection),
            state = lazyListState
        ) {

            if(cartListDataState.isEmpty()){
                item(
                    span = StaggeredGridItemSpan.FullLine,
                    key = "Empty Cart Icon Display"
                )  {
                    Column(
                        modifier = Modifier.fillMaxWidth()
                            .height(Dimens.CartItemHeight),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painter = painterResource(R.drawable.ic_empty_cart_img),
                            contentDescription = null
                        )

                        Text(
                            text = "Your cart is empty",
                            fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            else{
                item(
                    span = StaggeredGridItemSpan.FullLine,
                    key = "Cart List"
                ) {

                    Column {
                        Text(
                            text = "Clear All",
                            color = Color.Red,
                            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                            modifier = Modifier
                                .align(Alignment.End)
                                .padding(horizontal = Dimens.MediumPadding)
                                .clip(RoundedCornerShape(Dimens.MediumRoundRadius))
                                .clickable {
                                    cartListDataState.clear()
                                    cartListData.clear()                                }
                                .padding(Dimens.MiniPadding)
                        )

                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = Dimens.MiniPadding)
                                .height(Dimens.OneDp)
                                .background(Color.LightGray)
                        )

                        cartListDataState.forEach {
                            Column(
                                modifier = Modifier
                                    .clickable {
                                        navController.navigate(ScreenRoute.ProductDetailScreen.route+"/${it.id}")
                                    }
                            ) {
                                CartListItem(
                                    item = it,
                                    onRemoveClicked = {
                                        cartListDataState.remove(it)
                                        cartListData.remove(it)
                                    }
                                )
                                Spacer(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(Dimens.OneDp)
                                        .background(Color.LightGray)
                                )
                            }
                        }
                    }
                }
            }

            item(
                span = StaggeredGridItemSpan.FullLine,
                key = "Discover More"
            ){
                Text(
                    text = "Discover More",
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = MaterialTheme.typography.displaySmall.fontSize,
                    color = Color.Black,
                    modifier = Modifier
                        .padding(top = Dimens.SmallPadding, start = Dimens.SmallPadding, bottom = Dimens.SmallPadding)
                )
            }

            if(!internetConnection){
                item(
                    span = StaggeredGridItemSpan.FullLine
                ) {
                    NoInternetIconComponent {
                        internetConnection = true
                    }
                }
            }else {
                items(
                    items = productStateListData,
                    key = { it.id }
                ) { item ->
                    ProductGridDisplayItem(
                        item = item,
                        modifier = Modifier
                            .clickable { navController.navigate(ScreenRoute.ProductDetailScreen.route + "/${item.id}") }
                    )
                }

                item(span = StaggeredGridItemSpan.FullLine) {
                    LoadingIndicator()
                }
            }
        }

    }
}

@Composable
fun CartListItem(
    item : CartListItemData,
    onRemoveClicked : ()->Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(Dimens.CartItemHeight)
            .padding( horizontal = Dimens.MiniPadding)
            .padding(top = Dimens.SmallPadding)
        ,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(item.img),
            contentDescription = null,
            modifier = Modifier
                .weight(3.3f)
                .fillMaxHeight()
        )

        Column(
            modifier = Modifier.weight(7f)
                .padding(horizontal = Dimens.MiniPadding)
        ) {

            Text(
                text = item.title,
                fontWeight = FontWeight.Medium,
                fontSize = MaterialTheme.typography.bodySmall.fontSize,
                overflow = TextOverflow.Ellipsis,
                maxLines = 2,
                modifier = Modifier
                    .padding(bottom = Dimens.MiniPadding)
                    ,
                style = TextStyle(
                    lineBreak = LineBreak(strategy = LineBreak.Strategy.Balanced, strictness = LineBreak.Strictness.Default,wordBreak = LineBreak.WordBreak.Phrase)
                )
            )

            Row{
                item.descriptionWords.forEach {
                    Text(
                        text = it,
                        modifier = Modifier
                            .padding(vertical = Dimens.MiniPadding)
                            .padding(end = Dimens.MiniPadding)
                            .clip(RoundedCornerShape(Dimens.SmallRoundRadius))
                            .background(Color.LightGray.copy(0.8f))
                            .padding(
                                horizontal = Dimens.MiniPadding,
                                vertical = Dimens.OneDp
                            )
                        ,
                        fontSize = MaterialTheme.typography.bodySmall.fontSize,
                    )
                }
            }

            Text(
                text = "ETB " + item.price,
                fontWeight = FontWeight.Bold,
                color = customPrimaryPink,
                modifier = Modifier
                    .padding(top = Dimens.OneDp, bottom = Dimens.MiniPadding)
            )

            Text(
                text = "Location: ${item.location}",
                fontSize = MaterialTheme.typography.labelMedium.fontSize,
                modifier = Modifier
                    .padding(bottom = Dimens.MiniPadding.minus(Dimens.OneDp))
            )
        }

        IconButton(
            onClick = {
                onRemoveClicked()
            },
            modifier = Modifier.weight(1f)
        ) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = null
            )
        }
    }
    
}

@Preview(showBackground = true)
@Composable
private fun t() {
   // CartListItem(item = cartListData[0] )
    CartScreen(
        navController = rememberNavController()
    )
}