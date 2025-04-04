package com.example.amanshop.presentation.ui.theme

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class DimenHolder(
    val MiniPadding: Dp,
    val SmallPadding: Dp,
    val MediumPadding: Dp,
    val LargePadding: Dp,

    val MediumRoundRadius: Dp = 10.dp,
    val SmallRoundRadius: Dp  = 3.dp,

    val IconSizeSmall: Dp,
    val IconSizeMedium: Dp,
    val IconSizeLarge: Dp,

    val ZeroDp: Dp = 0.dp,
    val OneDp: Dp = 1.dp,

    val TopAppBarHeight: Dp,
    val BottomNavigationBarHeight: Dp,
    val DisplayImageHeight: Dp,

    val HomeCategoryListHeight: Dp  = 100.dp,
    val HomeCategoryItemWidth: Dp = 86.dp,
    val HomeCategoryImageSize: Dp = 72.dp,

    val TrendCategoryListItemWidth: Dp,
    val TrendCategoryListItemHeight: Dp,
    val TrendCategoryListImageSize: Dp,

    val CartItemHeight : Dp = 134.dp,
    val SearchedListItemHeight : Dp = 110.dp,

    val EmptyCartIconHeight: Dp = 200.dp,

    val subCategoryListItemHeight : Dp = 80.dp,

    val ProfileInfoDisplayHeight:Dp = 130.dp,

    val ProductDetailImageHeight : Dp = 300.dp,
    val ProductDetailSellerImageSize : Dp = 100.dp,

    val ShopBackgroundImageHeight : Dp = 180.dp,
    val ShopProfilePictureHeight : Dp = 120.dp,

    val TextFieldMaxHeight : Dp = 140.dp,
    val TextFieldMinHeight: Dp = 34.dp,

    val ChatTopNameDisplayHeight : Dp= 58.dp,
    val ChatMaximumImageHeight : Dp = 250.dp,

    val NoMessageIconSize :Dp = 140.dp,

    val NoInternetIconSize :Dp = 140.dp,

    val MessageListItemHeight :Dp = 80.dp,
    val MessageItemImageSize : Dp = 70.dp,

    val NotificationListItemHeight : Dp = 85.dp,

    val RecentViewListImageSize : Dp = 120.dp,

    val ExitAlertDialogWidth : Dp = 360.dp,

    val ProductDisplayShimmerHeight : Dp = 200.dp,
    val ProductDisplayTextShimmerHeight : Dp = 16.dp,

    val ChatScreenRecordingSign : Dp = 190.dp,

)


object ScreensDimen{

    val OtherScreenDimens = DimenHolder(
        MiniPadding = 5.dp,
        SmallPadding = 8.dp,
        MediumPadding = 12.dp,
        LargePadding = 24.dp,

        MediumRoundRadius = 10.dp,

        IconSizeSmall = 18.dp,
        IconSizeMedium = 32.dp,
        IconSizeLarge = 48.dp,

        ZeroDp = 0.dp,
        OneDp = 1.dp,

        TopAppBarHeight = 48.dp,
        BottomNavigationBarHeight = 54.dp,
        DisplayImageHeight = 380.dp,

        TrendCategoryListItemWidth = 170.dp,
        TrendCategoryListItemHeight = 188.dp,
        TrendCategoryListImageSize = 165.dp,

    )

    val LargeCompactScreenDimens = DimenHolder(
        MiniPadding = 5.dp,
        SmallPadding = 8.dp,
        MediumPadding = 12.dp,
        LargePadding = 24.dp,

        MediumRoundRadius = 10.dp,

        IconSizeSmall = 18.dp,
        IconSizeMedium = 32.dp,
        IconSizeLarge = 48.dp,

        ZeroDp = 0.dp,
        OneDp = 1.dp,

        TopAppBarHeight = 48.dp,
        BottomNavigationBarHeight = 54.dp,
        DisplayImageHeight = 380.dp,

        TrendCategoryListItemWidth = 170.dp,
        TrendCategoryListItemHeight = 188.dp,
        TrendCategoryListImageSize = 165.dp,

    )

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

    val SmallCompactScreenDimens = DimenHolder(
        MiniPadding = 5.dp,
        SmallPadding = 8.dp,
        MediumPadding = 12.dp,
        LargePadding = 24.dp,

        MediumRoundRadius = 10.dp,
        SmallRoundRadius = 3.dp,

        IconSizeSmall = 18.dp,
        IconSizeMedium = 24.dp,
        IconSizeLarge = 48.dp,

        ZeroDp = 0.dp,
        OneDp = 1.dp,

        TopAppBarHeight = 48.dp,
        BottomNavigationBarHeight = 56.dp,
        DisplayImageHeight = 200.dp,

        HomeCategoryListHeight= 100.dp,
        HomeCategoryItemWidth = 86.dp,
        HomeCategoryImageSize = 72.dp,

        TrendCategoryListItemWidth = 150.dp,
        TrendCategoryListItemHeight = 165.dp,
        TrendCategoryListImageSize = 145.dp,

        EmptyCartIconHeight = 200.dp,

        subCategoryListItemHeight  = 80.dp,

        ProfileInfoDisplayHeight = 120.dp,

        ProductDetailSellerImageSize = 72.dp,
        ProductDetailImageHeight = 230.dp,

        ShopBackgroundImageHeight  = 140.dp,
        ShopProfilePictureHeight  = 100.dp,

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