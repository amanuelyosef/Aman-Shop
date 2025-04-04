package com.example.amanshop.presentation.components

import com.example.amanshop.R
import java.util.UUID

data class SellerDataItem(
    val id: String = UUID.randomUUID().toString(),
    val name : String,
    val shopName : String,
    val image : Int,
    val lastSeen : String,
    val durationOnApp : String,
    val location : String,
    val phoneNumber : String,
    val feedbackList : MutableList<FeedbackDataItem>
)

val SellersInfoList = listOf(
    SellerDataItem(
        name = "Sophia Bekele",
        shopName = "Sophia Bekele Shop",
        image = R.drawable.shop_profile_picture,
        lastSeen = "5 hours ago",
        durationOnApp = "2y 6m",
        location = "Nifas Silk, Addis Ababa",
        phoneNumber = "0912345678",
        feedbackList = mutableListOf(
            FeedbackDataItem(
                customerName = "Abebe Kebede",
                customerImage = R.drawable.ic_profile_picture,
                feedback = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when",
                date = "12/12/2024",
                numberOfLike = 5,
                numberOfDislike = 5
            ),
            FeedbackDataItem(
                customerName = "Abebe Kebede",
                customerImage = R.drawable.ic_profile_picture,
                feedback = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when",
                date = "12/12/2024",
                numberOfLike = 5,
                numberOfDislike = 5
            )
        )
    ),
    SellerDataItem(
        name = "Hana Tesfaye",
        shopName = "Hana Tesfaye Shop",
        image = R.drawable.shop_profile_picture,
        lastSeen = "2 weeks ago",
        durationOnApp = "1y 6m",
        location = "Kirkos, Addis Ababa",
        phoneNumber = "0912345678",
        feedbackList = mutableListOf(
            FeedbackDataItem(
                customerName = "Abebe Kebede",
                customerImage = R.drawable.ic_profile_picture,
                feedback = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when",
                date = "12/12/2024",
                numberOfLike = 5,
                numberOfDislike = 5
            ),
            FeedbackDataItem(
                customerName = "Abebe Kebede",
                customerImage = R.drawable.ic_profile_picture,
                feedback = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when",
                date = "12/12/2024",
                numberOfLike = 5,
                numberOfDislike = 5
            )
        )
    ),
    SellerDataItem(
        name = "Samuel Girma",
        shopName = "Samuel Girma Shop",
        image = R.drawable.shop_profile_picture,
        lastSeen = "2 days ago",
        durationOnApp = "5y 6m",
        location = "Sebeta, Addis Ababa",
        phoneNumber = "0912345678",
        feedbackList = mutableListOf(
            FeedbackDataItem(
                customerName = "Alemu Haile",
                customerImage = R.drawable.ic_profile_picture,
                feedback = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when",
                date = "1/12/2024",
                numberOfLike = 5,
                numberOfDislike = 5
            ),
            FeedbackDataItem(
                customerName = "Blen Yared",
                customerImage = R.drawable.ic_profile_picture,
                feedback = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when",
                date = "2/12/2024",
                numberOfLike = 5,
                numberOfDislike = 5
            )
        )
    )
)