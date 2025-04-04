package com.example.amanshop.presentation.home_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.amanshop.R
import com.example.amanshop.presentation.components.ScreenRoute
import com.example.amanshop.presentation.components.TrendCategoryListItemData
import com.example.amanshop.presentation.components.TrendTechProductListData
import com.example.amanshop.presentation.ui.theme.DimenHolder
import com.example.amanshop.presentation.ui.theme.Dimens
import com.example.amanshop.presentation.ui.theme.customPrimaryPink
import com.example.amanshop.presentation.ui.theme.trendCategoryColor1

@Composable
fun TrendCategoryList(
    modifier: Modifier = Modifier,
    trendTitle:String,
    productList:List<TrendCategoryListItemData>,
    containerColor:Color,
    navController: NavController
) {
    Column(
        modifier = Modifier
            .background(containerColor)
            .clickable {
                navController.navigate(ScreenRoute.TrendProductListScreen.route + "/${trendTitle}")
            }
            .padding(vertical = Dimens.SmallPadding)
    ) {

        Text(
            text = trendTitle,
            fontWeight = FontWeight.ExtraBold,
            fontSize = MaterialTheme.typography.displaySmall.fontSize,
            color = Color.Black,
            modifier = Modifier.padding(start = Dimens.SmallPadding)
        )

        LazyRow(
            verticalAlignment = Alignment.CenterVertically
        ){
            itemsIndexed(items = productList, key ={ _, item -> item.id}){ index, item->
                Column(
                    modifier = modifier
                        .padding(horizontal = Dimens.SmallPadding)
                        .clip(RoundedCornerShape(Dimens.MediumRoundRadius))
                        .background(Color.White)
                        .size(
                            width = Dimens.TrendCategoryListItemWidth,
                            height = Dimens.TrendCategoryListItemHeight
                        )
                        .clickable {
                            navController.navigate(ScreenRoute.ProductDetailScreen.route+"/${item.id}")
                        },
                ) {
                    Image(
                        painter = painterResource(item.img),
                        contentDescription = null,
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = Dimens.OneDp, vertical = Dimens.MiniPadding)
                            .align(Alignment.CenterHorizontally)
                    )

                    Text(
                        text = "ETB ${item.price}",
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                        color = customPrimaryPink,
                        modifier = Modifier.padding(horizontal = Dimens.MiniPadding)
                    )
                }

                if(productList.size==index+1){
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .padding(end = Dimens.MediumPadding)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(Dimens.IconSizeLarge)
                                .clip(CircleShape)
                                .background(containerColor.copy(red = 0.9f))
                                .clickable {
                                    navController.navigate(ScreenRoute.TrendProductListScreen.route + "/${trendTitle}")
                                },
                            contentAlignment = Alignment.Center
                        ){
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
                        )
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun TrendCategoryListPreview(modifier: Modifier = Modifier) {

    TrendCategoryList(
        trendTitle = "Tech Products",
        productList = TrendTechProductListData,
        containerColor = trendCategoryColor1,
        navController = rememberNavController()
    )
}

@Composable
fun Ts(modifier: Modifier = Modifier) {
    val MediumCompactScreenDimens = DimenHolder(
        MiniPadding = 5.dp,
        SmallPadding = 8.dp,
        MediumPadding = 12.dp,
        LargePadding = 24.dp,

        MediumRoundRadius = 10.dp,
        SmallRoundRadius  = 3.dp,

        IconSizeSmall = 18.dp,
        IconSizeMedium = 26.dp,
        IconSizeLarge = 48.dp,

        ZeroDp = 0.dp,
        OneDp = 1.dp,

        TopAppBarHeight = 48.dp,
        BottomNavigationBarHeight = 56.dp,
        DisplayImageHeight = 240.dp,

        HomeCategoryListHeight= 100.dp,
        HomeCategoryItemWidth = 86.dp,
        HomeCategoryImageSize = 72.dp,

        TrendCategoryListItemWidth = 150.dp,
        TrendCategoryListItemHeight = 165.dp,
        TrendCategoryListImageSize = 145.dp,

        CartItemHeight = 136.dp,
        EmptyCartIconHeight = 200.dp,

        subCategoryListItemHeight  = 80.dp,

        ProfileInfoDisplayHeight = 130.dp,

        ProductDetailImageHeight  = 300.dp,
        ProductDetailSellerImageSize  = 100.dp,

        ShopBackgroundImageHeight  = 160.dp,
        ShopProfilePictureHeight  = 120.dp,

        TextFieldMaxHeight  = 140.dp,
        TextFieldMinHeight  = 34.dp,

        ChatTopNameDisplayHeight = 58.dp,

        NoMessageIconSize  = 140.dp,

        MessageListItemHeight  = 85.dp,
        MessageItemImageSize  = 70.dp,

        NotificationListItemHeight  = 85.dp,

        RecentViewListImageSize  = 120.dp,

        ExitAlertDialogWidth  = 360.dp,

        ProductDisplayShimmerHeight  = 200.dp,
        ProductDisplayTextShimmerHeight  = 16.dp,
    )


}