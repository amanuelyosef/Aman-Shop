package com.example.amanshop.presentation.home_screen.components

import com.example.amanshop.R

data class DisplayImageItemData(
    val title:String,
    val image:Int
)

val DisplayImageListData = listOf(
    DisplayImageItemData("Electronics", R.drawable.display_electronics_image),
    DisplayImageItemData("Fashion", R.drawable.display_fashion_image),
    DisplayImageItemData("Furniture", R.drawable.display_funiture_image),
)