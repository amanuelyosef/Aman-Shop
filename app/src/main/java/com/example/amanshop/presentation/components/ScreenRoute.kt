package com.example.amanshop.presentation.components

sealed class ScreenRoute(val route:String) {

    data object HomeScreen : ScreenRoute("home_screen")
    data object CategoryScreen: ScreenRoute("category_screen")
    data object SellFormScreen : ScreenRoute("sell_screen")
    data object CartScreen : ScreenRoute("cart_screen")
    data object ProfileScreen : ScreenRoute("profile_screen")
    data object SubCategoryScreen : ScreenRoute("sub_category_screen")
    data object NotificationScreen : ScreenRoute("notification_screen")
    data object SearchScreen : ScreenRoute("search_screen")
    data object ProductDetailScreen : ScreenRoute("product_detail_screen")
    data object ImagePreviewScreen : ScreenRoute("image_preview_screen")
    data object SellerShopScreen : ScreenRoute("seller_shop_screen")
    data object FeedbackScreen : ScreenRoute("feedback_screen")
    data object ChatScreen : ScreenRoute("chat_screen")
    data object SearchedListScreen : ScreenRoute("search_list_screen")
    data object MessageListScreen : ScreenRoute("message_list_screen")
    data object FilterSearchedListScreen : ScreenRoute("filter_searched_list_screen")
    data object ReportScreen : ScreenRoute("report_screen")
    data object CreateNewAccountScreen : ScreenRoute("create_new_account_screen")
    data object LoginScreen : ScreenRoute("login_screen")
    data object TrendProductListScreen : ScreenRoute("trend_product_list_screen")

}