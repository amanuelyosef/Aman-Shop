package com.example.amanshop.presentation.category_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.amanshop.R
import com.example.amanshop.presentation.components.BottomNavigationBar
import com.example.amanshop.presentation.components.CategoryListData
import com.example.amanshop.presentation.components.LoadingIndicator
import com.example.amanshop.presentation.components.NoInternetIconComponent
import com.example.amanshop.presentation.components.ProductGridDisplayItem
import com.example.amanshop.presentation.components.ProductDisplayListData
import com.example.amanshop.presentation.components.ScreenRoute
import com.example.amanshop.presentation.components.SearchBarComponent
import com.example.amanshop.presentation.ui.theme.Dimens
import com.example.amanshop.presentation.ui.theme.LocalString
import com.example.amanshop.presentation.ui.theme.homeHeadlineTitle
import com.example.amanshop.presentation.ui.theme.nunitoFontFamily
import kotlinx.coroutines.delay
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryScreen(
    navController: NavController
) {

    val topAppBarState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(state = topAppBarState )
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
        modifier = Modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = LocalString.category,
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
                scrollBehavior = scrollBehavior,
                colors = TopAppBarDefaults.topAppBarColors(
                    scrolledContainerColor = Color.White
                )
            )
        },
        bottomBar = {
            BottomNavigationBar(navController = navController, selected = 5)
        }
    ) {
        
        Column(
            modifier = Modifier
                .padding(top = it.calculateTopPadding().coerceIn(Dimens.ZeroDp, Dimens.TopAppBarHeight), bottom = it.calculateBottomPadding())
        ) {
            SearchBarComponent(
                modifier = Modifier
                    .padding(horizontal = Dimens.MediumPadding)
                    ,
                enabled = false,
                navController = navController,
            )

            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Fixed(2),
                state = lazyListState
            ) {
                item(
                    span = StaggeredGridItemSpan.FullLine,
                    key = "Category Screen Category List"
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth()
                            .padding(Dimens.SmallPadding)
                    ) {
                        CategoryListData.chunked(3).forEach{chunkedItems->
                            Row {
                                chunkedItems.forEach{item ->
                                    Column(
                                        modifier = Modifier.weight(1f)
                                            .height(100.dp)
                                            .clip(RoundedCornerShape(Dimens.MediumRoundRadius))
                                            .clickable {
                                                navController.navigate(ScreenRoute.SubCategoryScreen.route+"/${item.title}")
                                            }
                                        ,
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.Center,
                                    ) {
                                        Image(
                                            painter = painterResource(item.img),
                                            contentDescription = null,
                                            modifier = Modifier.weight(1f)
                                                .fillMaxWidth(0.8f)

                                        )
                                        Text(
                                            text = item.title,
                                            textAlign = TextAlign.Center,
                                            fontWeight = FontWeight.Medium,
                                            fontSize = MaterialTheme.typography.bodySmall.fontSize,
                                            lineHeight = MaterialTheme.typography.bodySmall.fontSize,
                                            overflow = TextOverflow.Clip,
                                            modifier = Modifier
                                                .padding(horizontal = Dimens.MiniPadding)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

                item(
                    span = StaggeredGridItemSpan.FullLine,
                    key = "Category Screen Spacer"
                ) {
                    Column {

                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = Dimens.LargePadding)
                                .background(Color.Gray)
                                .height(Dimens.OneDp)
                        )

                        Text(
                            text = "Discover More",
                            fontWeight = FontWeight.Bold,
                            fontSize = MaterialTheme.typography.headlineMedium.fontSize,
                            color = Color.Black,
                            modifier = Modifier
                                .padding(top = Dimens.SmallPadding, start = Dimens.SmallPadding, bottom = Dimens.SmallPadding)
                        )
                    }
                }

                if(!internetConnection){
                    item(
                        span = StaggeredGridItemSpan.FullLine
                    ) {
                        NoInternetIconComponent{
                            internetConnection = true
                        }
                    }
                }else{

                    items(
                        items = productStateListData,
                    ){item->
                        ProductGridDisplayItem(
                            item = item,
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
    }
}



@Preview
@Composable
fun CategoryScreenPreview(modifier: Modifier = Modifier) {
    CategoryScreen(rememberNavController())
}