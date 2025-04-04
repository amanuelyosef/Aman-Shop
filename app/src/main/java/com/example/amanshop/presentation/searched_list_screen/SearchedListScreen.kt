package com.example.amanshop.presentation.searched_list_screen

import androidx.compose.animation.core.InfiniteRepeatableSpec
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
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
import com.example.amanshop.presentation.components.ProductDetailDataList
import com.example.amanshop.presentation.components.ProductGridDisplayItem
import com.example.amanshop.presentation.components.ProductDisplayItemShimmer
import com.example.amanshop.presentation.components.ProductDisplayListData
import com.example.amanshop.presentation.components.ProductListDisplayItem
import com.example.amanshop.presentation.components.ScreenRoute
import com.example.amanshop.presentation.components.SearchBarComponent
import com.example.amanshop.presentation.components.shimmerEffect
import com.example.amanshop.presentation.searched_list_screen.components.brandFilterData
import com.example.amanshop.presentation.ui.theme.Dimens
import com.example.amanshop.presentation.ui.theme.customOnPrimaryContainerPink
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchedListScreen(
    navController:NavController,
    searchedText : String
) {
    var isSortDropDownVisible by remember { mutableStateOf(false) }
    var isList by remember { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current
    val lazyListState = rememberLazyStaggeredGridState()
    var internetConnection by remember { mutableStateOf(true) }

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
    val productStateListData = remember { mutableStateListOf(*(ProductDisplayListData).toTypedArray()) }
    //productStateListData.sortByDescending {item -> ProductDetailDataList.find{it.id==item.id}!!.numberOfView }
    var isRefreshing by remember{ mutableStateOf(false) }
    val pullToRefreshState = rememberPullToRefreshState()
    val coroutineScope = rememberCoroutineScope()

    val shimmerModifier = Modifier
        .height(Dimens.ProductDisplayTextShimmerHeight)
        .padding(bottom = Dimens.MiniPadding)
        .shimmerEffect(size, startOffset)
        .onGloballyPositioned { size = it.size }

    LaunchedEffect(Unit) {
            isLoading = false
            delay(2000)
            isLoading = false
        }

    LaunchedEffect( lazyListState.firstVisibleItemIndex > productStateListData.size-4) {
        if(lazyListState.firstVisibleItemIndex > productStateListData.size-4){
            delay(5000L)
            productStateListData.addAll(ProductDisplayListData.map { it.copy(id = UUID.randomUUID().toString()) })
        }
    }


    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                navController = navController,
                selected = 5
            )
        }
    ) {

        Column(
            modifier = Modifier.fillMaxSize()
                .padding(it)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                IconButton(
                    modifier = Modifier.weight(1f),
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

                SearchBarComponent(
                    modifier = Modifier
                        .weight(7f)
                        .padding(end = Dimens.MediumPadding),
                    enabled = true,
                    navController = navController,
                    searchText = searchedText,
                    onSearchNavigate = {
                        keyboardController?.hide()
                    },
                )
            }

            PullToRefreshBox(
                isRefreshing = isRefreshing,
                onRefresh = {
                    coroutineScope.launch {
                        isRefreshing = true
                        isLoading = true
                        delay(5000L)
                        isRefreshing = false
                        isLoading = false
                    }
                },
                indicator = {
                    Indicator(
                        modifier = Modifier.align(Alignment.TopCenter),
                        isRefreshing = isRefreshing,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        state = pullToRefreshState,
                    )
                },
                state = pullToRefreshState
            ) {
                if(!internetConnection){
                    NoInternetIconComponent{
                        internetConnection = true
                        isLoading = true
                    }
                }else{
                    LazyVerticalStaggeredGrid(
                        columns = StaggeredGridCells.Fixed(2),
                        state = lazyListState
                    ) {

                        item(
                            span = StaggeredGridItemSpan.FullLine,
                            key = "filter and sort options"
                        ) {

                            Column {

                                LazyRow(
                                    modifier = Modifier.padding(vertical = Dimens.SmallPadding),
                                ) {
                                    items(brandFilterData) {

                                        Text(
                                            text = it.name,
                                            fontWeight = FontWeight.Medium,
                                            fontSize = MaterialTheme.typography.bodySmall.fontSize,
                                            modifier = Modifier
                                                .padding(horizontal = Dimens.MiniPadding)
                                                .clip(RoundedCornerShape(Dimens.MediumRoundRadius))
                                                .background(Color.LightGray)
                                                .padding(
                                                    horizontal = Dimens.MediumPadding,
                                                    vertical = Dimens.MiniPadding
                                                )
                                        )

                                    }
                                }

                                Row(
                                    modifier = Modifier.align(Alignment.End)
                                        .padding(vertical = Dimens.SmallPadding)
                                ) {

                                    var selectedDropDownItem by remember { mutableStateOf(0) }

                                    Row(
                                        modifier = Modifier.clickable(
                                            interactionSource = null,
                                            indication = null
                                        ) {
                                            isSortDropDownVisible = true

                                        }
                                    ) {


                                        Text(
                                            text = "Sort",
                                            color = customOnPrimaryContainerPink,
                                            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                                            fontWeight = FontWeight.Bold,
                                            modifier = Modifier.padding(end = Dimens.MiniPadding)
                                        )

                                        Icon(
                                            imageVector = ImageVector.vectorResource(R.drawable.ic_sort_product),
                                            contentDescription = null,
                                            tint = customOnPrimaryContainerPink,
                                            modifier = Modifier.padding(end = Dimens.MediumPadding)
                                        )
                                    }

                                    DropdownMenu(
                                        expanded = isSortDropDownVisible,
                                        onDismissRequest = { isSortDropDownVisible = false }
                                    ) {
                                        Column {
                                            Row(
                                                modifier = Modifier
                                                    .clickable(
                                                        indication = null,
                                                        interactionSource = null
                                                    ) {
                                                        selectedDropDownItem = 0
                                                        isSortDropDownVisible = false

                                                        productStateListData.sortByDescending { item ->
                                                            ProductDetailDataList.find { it.id == item.id }!!.numberOfView
                                                        }
                                                    }
                                                    .padding(
                                                        vertical = Dimens.SmallPadding,
                                                        horizontal = Dimens.MediumPadding
                                                    )

                                            ) {
                                                Text(
                                                    text = "Most Viewed",
                                                    fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                                                    color = if (selectedDropDownItem == 0) customOnPrimaryContainerPink else Color.Black
                                                )
                                            }

                                            Row(
                                                modifier = Modifier
                                                    .clickable(
                                                        indication = null,
                                                        interactionSource = null
                                                    ) {
                                                        selectedDropDownItem = 1
                                                        isSortDropDownVisible = false

                                                        productStateListData.sortByDescending { item ->
                                                            ProductDetailDataList.find { it.id == item.id }!!.postedDate.reversed()
                                                        }
                                                    }
                                                    .padding(
                                                        vertical = Dimens.SmallPadding,
                                                        horizontal = Dimens.MediumPadding
                                                    )

                                            ) {
                                                Text(
                                                    text = "Newest",
                                                    fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                                                    color = if (selectedDropDownItem == 1) customOnPrimaryContainerPink else Color.Black
                                                )
                                            }

                                            Row(
                                                modifier = Modifier
                                                    .clickable(
                                                        indication = null,
                                                        interactionSource = null
                                                    ) {
                                                        selectedDropDownItem = 2
                                                        isSortDropDownVisible = false
                                                        productStateListData.sortBy {
                                                            it.price
                                                        }
                                                    }
                                                    .padding(
                                                        vertical = Dimens.SmallPadding,
                                                        horizontal = Dimens.MediumPadding
                                                    )

                                            ) {
                                                Text(
                                                    text = "Lowest Price",
                                                    fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                                                    color = if (selectedDropDownItem == 2) customOnPrimaryContainerPink else Color.Black
                                                )
                                            }
                                        }
                                    }

                                    Row(
                                        modifier = Modifier
                                            .clickable(
                                                interactionSource = null,
                                                indication = null
                                            ) {
                                                navController.navigate(ScreenRoute.FilterSearchedListScreen.route)
                                            }
                                    ) {

                                        Text(
                                            text = "Filter",
                                            color = customOnPrimaryContainerPink,
                                            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                                            fontWeight = FontWeight.Bold,
                                            modifier = Modifier.padding(end = Dimens.MiniPadding)
                                        )

                                        Icon(
                                            imageVector = ImageVector.vectorResource(R.drawable.ic_filter_product),
                                            contentDescription = null,
                                            tint = customOnPrimaryContainerPink,
                                            modifier = Modifier.padding(end = Dimens.SmallPadding)
                                        )
                                    }

                                }
                            }
                        }

                        item(
                            span = StaggeredGridItemSpan.FullLine,
                            key = "number of found ads"
                        ) {
                            Column {

                                Spacer(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(Dimens.OneDp)
                                        .background(Color.Gray)
                                )

                                Row(
                                    modifier = Modifier.fillMaxWidth()
                                        .padding(Dimens.SmallPadding),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {

                                    Text(
                                        text = "Found 12,345 ads",
                                        fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.Gray
                                    )

                                    Icon(
                                        imageVector = ImageVector.vectorResource(
                                            if (isList) {
                                                R.drawable.ic_grid_indicator
                                            } else {
                                                R.drawable.ic_row_list_selector
                                            }

                                        ),
                                        contentDescription = null,
                                        modifier = Modifier.size(Dimens.IconSizeMedium)
                                            .clickable {
                                                isList = !isList
                                            }
                                    )
                                }
                            }
                        }



                        if (isLoading) {
                            items(6) {
                                ProductDisplayItemShimmer(modifier = shimmerModifier)
                            }
                        }
                        else if (isList) {
                            items(
                                items = productStateListData,
                                key = { it.id },
                                span = { StaggeredGridItemSpan.FullLine }
                            ) { item ->
                                ProductListDisplayItem(
                                    item = item,
                                    modifier = Modifier
                                        .clickable {
                                            navController.navigate(ScreenRoute.ProductDetailScreen.route + "/${item.id}")
                                        }
                                )
                            }
                        }
                        else {
                            items(items = productStateListData, key = { it.id }) { item ->
                                ProductGridDisplayItem(
                                    item = item,
                                    modifier = Modifier
                                        .clickable {
                                            navController.navigate(ScreenRoute.ProductDetailScreen.route + "/${item.id}")
                                        }
                                )
                            }
                        }

                        item(span = StaggeredGridItemSpan.FullLine) {
                            LoadingIndicator()
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

    SearchedListScreen(
        navController = rememberNavController(),
        searchedText = "Laptop"
    )
    
}