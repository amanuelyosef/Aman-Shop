package com.example.amanshop.presentation.components

import androidx.compose.runtime.Immutable
import com.example.amanshop.R

@Immutable
data class BottomNavigationBarDataItem(
    val title: String,
    val route: String,
    val selectedIcon: Int,
    val unselectedIcon: Int,
    val contentDescription:String,
    var badgeNumber: Int = 0,
)

val BottomNavigationBarListData = listOf(
    BottomNavigationBarDataItem(
        title = "Home",
        route = ScreenRoute.HomeScreen.route,
        selectedIcon = R.drawable.ic_filled_home,
        unselectedIcon = R.drawable.ic_outlined_home,
        contentDescription = "home button"
    ),
    BottomNavigationBarDataItem(
        title = "Cart",
        route = ScreenRoute.CartScreen.route,
        selectedIcon = R.drawable.ic_filled_cart,
        unselectedIcon = R.drawable.ic_outlined_cart,
        contentDescription = "cart button"
    ),
    BottomNavigationBarDataItem(
        title = "Sell",
        route = ScreenRoute.SellFormScreen.route,
        selectedIcon = R.drawable.ic_filled_category,
        unselectedIcon = R.drawable.ic_outlined_category,
        contentDescription = "sell button"
    ),
    BottomNavigationBarDataItem(
        title = "Chat",
        route = ScreenRoute.MessageListScreen.route,
        selectedIcon = R.drawable.ic_filled_chat,
        unselectedIcon = R.drawable.ic_outlined_chat,
        contentDescription = "chat button",
    ),
    BottomNavigationBarDataItem(
        title = "Profile",
        route = ScreenRoute.ProfileScreen.route,
        selectedIcon = R.drawable.ic_filled_profile,
        unselectedIcon = R.drawable.ic_outlined_profile,
        contentDescription = "profile button"
    )
)
