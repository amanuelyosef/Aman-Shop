package com.example.amanshop.presentation.cart_screen.components

import com.example.amanshop.R
import com.example.amanshop.presentation.components.ProductDisplayListData
import com.example.amanshop.presentation.components.TrendSuperDealListData
import com.example.amanshop.presentation.components.TrendTechProductListData

data class CartListItemData(
    val id : String,
    val title:String,
    val img:Int,
    val descriptionWords : List<String>,
    val price : String,
    val location : String,

)
/*
val TrendTechProductListData = listOf(
    TrendCategoryLIstItemData("1,000", R.drawable.trd_spark_phone),
    TrendCategoryLIstItemData("1,000", R.drawable.trd_ps4),
    TrendCategoryLIstItemData("1,000", R.drawable.trd_canon_camera),
)
 */
val cartListData = mutableListOf(
    CartListItemData(
        id = TrendTechProductListData[0].id,
        title = "Brand new Lamborghini 2025 made in USA high speed and durability ",
        img = R.drawable.trd_spark_phone,
        descriptionWords = listOf("Brand New", "Free Shipping"),
        price = "1,000",
        location = "Addis Ababa"
    ),
    CartListItemData(
        id = TrendSuperDealListData[1].id,
        title = "Brand new Lamborghini 2025 made in USA high speed and durability ",
        img = R.drawable.trd_hand_watch,
        descriptionWords = listOf("Brand New", "Free Shipping"),
        price = "1,000",
        location = "Addis Ababa"
    ),
    CartListItemData(
        id = ProductDisplayListData[3].id,
        title = "Brand new Lamborghini 2025 made in USA high speed and durability ",
        img = R.drawable.rcd_home,
        descriptionWords = listOf("Brand New", "Free Shipping"),
        price = "1,000",
        location = "Addis Ababa"
    )
)