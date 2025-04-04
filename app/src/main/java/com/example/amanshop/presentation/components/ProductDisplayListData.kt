package com.example.amanshop.presentation.components

import androidx.compose.runtime.Immutable

@Immutable
data class ProductDisplayListItemData(
    val id: String,
    val img:Int,
    val title:String = "Brand new Lamborghini 2025 made in USA high speed and durability ",
    val price:String = "1,000",
    val descriptionWords: String = "Addis Ababa • Brand New",
)


val ProductDisplayListData = mutableListOf(
    ProductDisplayListItemData(
        id = ProductDetailDataList[0].id,
        img = ProductDetailDataList[0].image[0],
    ),
    ProductDisplayListItemData(
        id = ProductDetailDataList[1].id,
        img = ProductDetailDataList[1].image[0],
    ),
    ProductDisplayListItemData(
        id = ProductDetailDataList[2].id,
        img = ProductDetailDataList[2].image[0],
        price = "11,000",
    ),
    ProductDisplayListItemData(
        id = ProductDetailDataList[3].id,
        img = ProductDetailDataList[3].image[0],
        price = "9,000",
    ),
    ProductDisplayListItemData(
        id = ProductDetailDataList[4].id,
        img = ProductDetailDataList[4].image[0],
    ),
    ProductDisplayListItemData(
        id = ProductDetailDataList[5].id,
        img = ProductDetailDataList[5].image[0],
    ),
    ProductDisplayListItemData(
        id = ProductDetailDataList[6].id,
        img = ProductDetailDataList[6].image[0],
    ),
    ProductDisplayListItemData(
        id = ProductDetailDataList[7].id,
        img = ProductDetailDataList[7].image[0],
    )
)