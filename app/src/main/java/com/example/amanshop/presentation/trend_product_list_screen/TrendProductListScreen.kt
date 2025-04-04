package com.example.amanshop.presentation.trend_product_list_screen

import androidx.compose.animation.core.InfiniteRepeatableSpec
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.amanshop.R
import com.example.amanshop.presentation.components.BottomNavigationBar
import com.example.amanshop.presentation.components.LoadingIndicator
import com.example.amanshop.presentation.components.NoInternetIconComponent
import com.example.amanshop.presentation.components.ProductGridDisplayItem
import com.example.amanshop.presentation.components.ProductDisplayItemShimmer
import com.example.amanshop.presentation.components.ProductDisplayListData
import com.example.amanshop.presentation.components.ScreenRoute
import com.example.amanshop.presentation.components.shimmerEffect
import com.example.amanshop.presentation.ui.theme.Dimens
import com.example.amanshop.presentation.ui.theme.homeHeadlineTitle
import kotlinx.coroutines.delay
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrendProductListScreen(
    title : String,
    navController: NavController
) {

    var isSortDropDownVisible by remember { mutableStateOf(false) }
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val lazyListState = rememberLazyStaggeredGridState()
    val productStateListData = remember { mutableStateListOf(*(ProductDisplayListData).toTypedArray()) }
    var internetConnection by remember { mutableStateOf(false) }

    var isLoading by remember { mutableStateOf(false) }
    var size by remember { mutableStateOf(IntSize.Zero) }
    val transition = rememberInfiniteTransition()
    val startOffset by transition.animateFloat(
        initialValue = -2 * size.width.toFloat(),
        targetValue = 2 * size.width.toFloat(),
        animationSpec = InfiniteRepeatableSpec(
            animation = tween(1500)
        )
    )
    val shimmerModifier = Modifier
        .height(Dimens.ProductDisplayTextShimmerHeight)
        .padding(bottom = Dimens.MiniPadding)
        .shimmerEffect(size, startOffset)
        .onGloballyPositioned { size = it.size }

    LaunchedEffect(Unit) {
        isLoading = true
        delay(2000)
        isLoading = false
    }

    LaunchedEffect( lazyListState.firstVisibleItemIndex > productStateListData.size-6) {
        if(lazyListState.firstVisibleItemIndex > productStateListData.size-6){
            delay(5000L)
            productStateListData.addAll(ProductDisplayListData.map { it.copy(id = UUID.randomUUID().toString()) })
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = title,
                        fontSize = MaterialTheme.typography.headlineLarge.fontSize,
                        fontWeight = FontWeight.Bold,
                        color = homeHeadlineTitle
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
                            contentDescription = com.example.amanshop.presentation.ui.theme.LocalString.notificationIcCd,
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

        }else {
            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
                state = lazyListState
            ) {

                if (isLoading) {
                    items(6) {
                        ProductDisplayItemShimmer(modifier = shimmerModifier)
                    }
                } else {
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
}

@Preview
@Composable
private fun T() {
    TrendProductListScreen(
        title = "Tech Products",
        navController = rememberNavController()
    )
}