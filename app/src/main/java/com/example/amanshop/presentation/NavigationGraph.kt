package com.example.amanshop.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.example.amanshop.presentation.cart_screen.CartScreen
import com.example.amanshop.presentation.category_screen.CategoryScreen
import com.example.amanshop.presentation.chat_screen.ChatScreen
import com.example.amanshop.presentation.components.ScreenRoute
import com.example.amanshop.presentation.create_new_account_screen.CreateNewAccountScreen
import com.example.amanshop.presentation.feedback_screen.FeedbackScreen
import com.example.amanshop.presentation.home_screen.HomeScreen
import com.example.amanshop.presentation.image_preview_screen.ImagePreviewScreen
import com.example.amanshop.presentation.login_screen.LoginScreen
import com.example.amanshop.presentation.message_list_screen.MessageListScreen
import com.example.amanshop.presentation.notification_screen.NotificationScreen
import com.example.amanshop.presentation.product_detail_screen.ProductDetailScreen
import com.example.amanshop.presentation.profile_screen.ProfileScreen
import com.example.amanshop.presentation.report_screen.ReportScreen
import com.example.amanshop.presentation.search_screen.SearchScreen
import com.example.amanshop.presentation.searched_list_screen.SearchedListScreen
import com.example.amanshop.presentation.searched_list_screen.components.FilterSearchedListScreen
import com.example.amanshop.presentation.seller_shop_screen.SellerShopScreen
import com.example.amanshop.presentation.sub_category_screen.SubCategoryScreen
import com.example.amanshop.presentation.trend_product_list_screen.TrendProductListScreen
import com.example.amanshop.presentation.ui.theme.LocalString

@Composable
fun NavigationGraph() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = ScreenRoute.HomeScreen.route
    ) {

        composable(ScreenRoute.HomeScreen.route) {
            HomeScreen(
                navController = navController
            )
        }

        composable(ScreenRoute.CategoryScreen.route) {
            CategoryScreen(
                navController = navController
            )
        }

        composable(ScreenRoute.SubCategoryScreen.route+"/{title}") {
            val title = it.arguments?.getString("title")!!
            SubCategoryScreen(
                navController = navController,
                title = title
            )
        }

        composable(ScreenRoute.SearchScreen.route) {
            SearchScreen(
                navController = navController
            )
        }

        composable(ScreenRoute.CartScreen.route) {
            CartScreen(
                navController = navController
            )
        }

        composable(ScreenRoute.ProfileScreen.route) {
            ProfileScreen(
                navController = navController
            )
        }

        composable(ScreenRoute.ProductDetailScreen.route+"/{itemId}") {
            val itemId = it.arguments?.getString("itemId")!!
            ProductDetailScreen(
                navController = navController,
                itemId = itemId
            )
        }

        composable(ScreenRoute.ImagePreviewScreen.route+"/{imageList}") {
            val imageList = it.arguments?.getString("imageList")!!.split(",").map { it.toInt() }

            ImagePreviewScreen(
                imageList = imageList,
                navController = navController
            )
        }

        composable(ScreenRoute.SellerShopScreen.route+"/{sellerId}") {
            val sellerId = it.arguments?.getString("sellerId")!!
            SellerShopScreen(
                navController = navController,
                sellerId = sellerId
            )
        }

        composable(ScreenRoute.FeedbackScreen.route+"/{sellerId}") {
            val sellerId = it.arguments?.getString("sellerId")!!
            FeedbackScreen(
                navController = navController,
                sellerId = sellerId
            )
        }

        composable(ScreenRoute.ChatScreen.route+"/{productId}",
            arguments = listOf(navArgument("productId") { type = NavType.StringType }),
            deepLinks = listOf(
                navDeepLink { uriPattern = "${LocalString.app_url}${LocalString.chat_screen_path}{productId}" }
            )
        ) {

            val productId = it.arguments?.getString("productId")!!

            ChatScreen(
                navController = navController,
                productId = productId
            )
        }

        composable(ScreenRoute.SearchedListScreen.route+"/{searchedText}") {
            val searched = it.arguments?.getString("searchedText")!!
            SearchedListScreen(
                navController = navController,
                searchedText = searched
            )
        }

        composable(ScreenRoute.MessageListScreen.route,
            deepLinks = listOf(
                navDeepLink { uriPattern = LocalString.app_url + LocalString.message_list_screen_path }
            )
        ) {
            MessageListScreen(
                navController = navController
            )
        }

        composable(ScreenRoute.NotificationScreen.route) {
            NotificationScreen(
                navController = navController
            )
        }

        composable(ScreenRoute.FilterSearchedListScreen.route) {
            FilterSearchedListScreen(
                navController = navController
            )
        }

        composable(ScreenRoute.ReportScreen.route+"/{screenHint} {reportedName}") {
            val hint = it.arguments?.getString("screenHint")!!
            val reportedName = it.arguments?.getString("reportedName")!!

            ReportScreen(
                prevScreenHint = hint,
                navController = navController,
                reportedName = reportedName
            )
        }

        composable(ScreenRoute.CreateNewAccountScreen.route) {
            CreateNewAccountScreen(
                navController = navController,
            )
        }

        composable(ScreenRoute.LoginScreen.route) {
            LoginScreen(
                navController = navController,
            )
        }

        composable(ScreenRoute.TrendProductListScreen.route+"/{trendTitle}") {
            val title = it.arguments?.getString("trendTitle")!!
            TrendProductListScreen(
                navController = navController,
                title = title
            )
        }
    }
}