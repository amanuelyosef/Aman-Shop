package com.example.amanshop.presentation.home_screen

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.core.InfiniteRepeatableSpec
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.material3.rememberTopAppBarState
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.amanshop.R
import com.example.amanshop.presentation.components.BottomNavigationBar
import com.example.amanshop.presentation.components.LoadingIndicator
import com.example.amanshop.presentation.components.LogInRequestBottomSheet
import com.example.amanshop.presentation.components.ProductGridDisplayItem
import com.example.amanshop.presentation.components.ProductDisplayItemShimmer
import com.example.amanshop.presentation.components.ProductDisplayListData
import com.example.amanshop.presentation.components.ScreenRoute
import com.example.amanshop.presentation.home_screen.components.DisplayImageHolder
import com.example.amanshop.presentation.components.SearchBarComponent
import com.example.amanshop.presentation.home_screen.components.HomeCategoryList
import com.example.amanshop.presentation.home_screen.components.TrendCategoryList
import com.example.amanshop.presentation.components.TrendFashionListData
import com.example.amanshop.presentation.components.TrendSuperDealListData
import com.example.amanshop.presentation.components.TrendTechProductListData
import com.example.amanshop.presentation.components.TrendUsedListData
import com.example.amanshop.presentation.components.shimmerEffect
import com.example.amanshop.presentation.home_screen.components.ExitAlertDialog
import com.example.amanshop.presentation.notification_screen.components.notificationList
import com.example.amanshop.presentation.ui.theme.Dimens
import com.example.amanshop.presentation.ui.theme.LocalString
import com.example.amanshop.presentation.ui.theme.customOnPrimaryContainerPink
import com.example.amanshop.presentation.ui.theme.trendCategoryColor1
import com.example.amanshop.presentation.ui.theme.trendCategoryColor2
import com.example.amanshop.presentation.ui.theme.trendCategoryColor3
import com.example.amanshop.presentation.ui.theme.trendCategoryColor4
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    val viewModel = viewModel<HomeScreenViewModel>()

    val notificationPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->

        if (!isGranted && Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            viewModel.onPermissionResult(
                permission = Manifest.permission.POST_NOTIFICATIONS,
                isGranted = isGranted
            )
        }

    }

    val topAppBarState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(state = topAppBarState )
    val badgeCount = remember { notificationList.count{!it.seen}}

    var showExitDialog by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val activity = context as? ComponentActivity
    var initialLoading by remember { mutableStateOf(false) }
    var isBottomSheetExpanded by remember { mutableStateOf(false) }
    val lazyListState = rememberLazyStaggeredGridState()
    val productStateListData = remember { mutableStateListOf(*(ProductDisplayListData).toTypedArray()) }

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
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            val checkNotificationPermission = checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED

            if(!checkNotificationPermission){
                notificationPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }

        }
        initialLoading = true
        delay(2000)
        initialLoading = false

    }

    LaunchedEffect( lazyListState.firstVisibleItemIndex > productStateListData.size-4) {
        if(lazyListState.firstVisibleItemIndex > productStateListData.size-4){
            delay(5000L)
            productStateListData.addAll(ProductDisplayListData.map { it.copy(id = UUID.randomUUID().toString()) })
        }
    }

    BackHandler(enabled = true) {
        showExitDialog = true
    }

    if(showExitDialog){
        ExitAlertDialog(
            onConfirm = {
                showExitDialog = false
                activity?.finish()
                        },
            onDismiss = {showExitDialog = false}
        )
    }

    var isRefreshing by remember{ mutableStateOf(false) }
    val pullToRefreshState = rememberPullToRefreshState()
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = LocalString.appName,
                        fontSize = MaterialTheme.typography.headlineLarge.fontSize,
                        fontWeight = FontWeight.ExtraBold,
                        color = customOnPrimaryContainerPink,
                        modifier = Modifier.clickable {
                            isBottomSheetExpanded = true
                        }
                    )
                },
                actions = {
                    BadgedBox(
                        badge = {
                            if(badgeCount>0){
                                Badge(
                                    containerColor = Color.Red,
                                    modifier = Modifier
                                        .border(
                                            width = Dimens.OneDp,
                                            color = Color.White,
                                            shape = CircleShape
                                        )
                                ){
                                    Text(
                                        text = badgeCount.toString(),
                                        color = Color.White,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = MaterialTheme.typography.labelSmall.fontSize,
                                    )
                                }
                            }
                        },
                        modifier = Modifier
                            .clickable(
                                indication = null,
                                interactionSource = null
                            ) {
                                navController.navigate(ScreenRoute.NotificationScreen.route)
                            }
                            .padding(horizontal = Dimens.MiniPadding)
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.ic_outlined_notification),
                            contentDescription = LocalString.notificationIcCd,
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .size(Dimens.IconSizeMedium)
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
            BottomNavigationBar(navController =navController, selected = 0)
        }
    ){

        Column(
            modifier = modifier
                .padding(top = it.calculateTopPadding().coerceIn(Dimens.ZeroDp, Dimens.TopAppBarHeight), bottom = it.calculateBottomPadding())
            ) {

            SearchBarComponent(
                modifier = Modifier
                    .background(Color.LightGray.copy(0.6f))
                    .padding(bottom = Dimens.OneDp)
                    .background(Color.White)
                    .padding(horizontal = Dimens.MediumPadding),
                enabled = false,
                navController = navController
            )

            PullToRefreshBox(
                isRefreshing = isRefreshing,
                onRefresh = {
                    isRefreshing = true
                    coroutineScope.launch {
                        isRefreshing = true
                        delay(5000L)
                        isRefreshing = false
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
                LazyVerticalStaggeredGrid (
                    columns = StaggeredGridCells.Fixed(2),
                    state = lazyListState,
                    modifier = Modifier
                ) {
                    item(
                        span = StaggeredGridItemSpan.FullLine,
                        key = "Display Image Holder"
                    ) {
                        DisplayImageHolder()
                    }

                    item (
                        span = StaggeredGridItemSpan.FullLine,
                        key = "Home Category List"
                    ) {
                        HomeCategoryList(navController = navController)
                    }

                    item(
                        span = StaggeredGridItemSpan.FullLine,
                        key = "Tech Products"
                    )  {
                        TrendCategoryList(
                            trendTitle = "Tech Products",
                            productList = TrendTechProductListData,
                            containerColor = trendCategoryColor1,
                            navController = navController
                        )
                    }

                    item(
                        span = StaggeredGridItemSpan.FullLine,
                        key = "Fashion"
                    )  {
                        TrendCategoryList(
                            trendTitle = "Fashion",
                            productList = TrendFashionListData,
                            containerColor = trendCategoryColor2,
                            navController = navController
                        )
                    }

                    item(
                        span = StaggeredGridItemSpan.FullLine,
                        key = "Super Deals"
                    )  {
                        TrendCategoryList(
                            trendTitle = "Super Deals",
                            productList = TrendSuperDealListData,
                            containerColor = trendCategoryColor3,
                            navController = navController
                        )
                    }

                    item(
                        span = StaggeredGridItemSpan.FullLine,
                        key = "Used"
                    )  {
                        TrendCategoryList(
                            trendTitle = "Used",
                            productList = TrendUsedListData,
                            containerColor = trendCategoryColor4,
                            navController = navController
                        )
                    }

                    item(
                        span = StaggeredGridItemSpan.FullLine,
                    ){
                        Text(
                            text = "For You",
                            fontWeight = FontWeight.Bold,
                            fontSize = MaterialTheme.typography.displaySmall.fontSize,
                            color = Color.Black,
                            modifier = Modifier
                                .padding(top = Dimens.SmallPadding, start = Dimens.SmallPadding, bottom = Dimens.SmallPadding)
                        )
                    }

                    if (initialLoading){
                        items(6){
                            ProductDisplayItemShimmer(modifier = shimmerModifier)
                        }
                    }
                    else{
                        items(items = productStateListData, key = {it.id}){item->
                            ProductGridDisplayItem(
                                item =item,
                                modifier = Modifier
                                    .clickable {
                                        navController.navigate(ScreenRoute.ProductDetailScreen.route+"/${item.id}")
                                    }
                            )
                        }
                    }

                    item(span = StaggeredGridItemSpan.FullLine){
                        LoadingIndicator()
                    }
                }
            }
        }

        if (isBottomSheetExpanded){
            LogInRequestBottomSheet(
                onDismissRequest = {isBottomSheetExpanded = false},
                navController = navController,
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(navController = rememberNavController())
}