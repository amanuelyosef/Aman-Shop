package com.example.amanshop.presentation.notification_screen.components

import com.example.amanshop.presentation.components.ProductDetailDataList
import com.example.amanshop.presentation.components.ScreenRoute

data class NotificationListItem(
    val productId : String,
    val notification : String,
    val date : String,
    val productImg : Int,
    val timeStamp : String,
    var checked : Boolean,
    var seen : Boolean,
    val navigationRoute : String =ScreenRoute.ChatScreen.route+"/${productId}"
)

val notificationList = listOf(
    NotificationListItem(
        productId = ProductDetailDataList[0].id,
        notification = "Abebe Chala Kebede viewed your phone number",
        date = "12/12/2024",
        timeStamp = "12:00 PM",
        productImg =  ProductDetailDataList[0].image[0],
        checked = false,
        seen = false,
    ),
    NotificationListItem(
        productId = ProductDetailDataList[1].id,
        notification = "Abebe Chala Kebede viewed your phone number",
        date = "12/12/2024",
        timeStamp = "12:00 PM",
        productImg =  ProductDetailDataList[1].image[0],
        checked = false,
        seen = false,
    ),
    NotificationListItem(
        productId = ProductDetailDataList[2].id,
        notification = "Abebe Chala Kebede viewed your phone number",
        date = "12/12/2024",
        timeStamp = "12:00 PM",
        productImg =  ProductDetailDataList[2].image[0],
        checked = false,
        seen = true,
    ),
    NotificationListItem(
        productId = ProductDetailDataList[8].id,
        notification = "Abebe Chala Kebede viewed your phone number",
        date = "12/12/2024",
        timeStamp = "12:00 PM",
        productImg =  ProductDetailDataList[8].image[0],
        checked = true,
        seen = true,
    )
)