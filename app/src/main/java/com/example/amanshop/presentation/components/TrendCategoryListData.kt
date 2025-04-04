package com.example.amanshop.presentation.components

import androidx.compose.runtime.Immutable

@Immutable
data class TrendCategoryListItemData(
    val id : String,
    val price:String = "1,000",
    val img:Int,
)

val TrendTechProductListData = listOf(
    TrendCategoryListItemData(
        id = ProductDetailDataList[8].id,
        price = ProductDetailDataList[8].price,
        img =  ProductDetailDataList[8].image[0]
    ),
    TrendCategoryListItemData(
        id = ProductDetailDataList[9].id,
        price = ProductDetailDataList[9].price,
        img =  ProductDetailDataList[9].image[0]
    ),
    TrendCategoryListItemData(
        id = ProductDetailDataList[10].id,
        price = ProductDetailDataList[10].price,
        img =  ProductDetailDataList[10].image[0]
    ),
)

val TrendFashionListData = listOf(
    TrendCategoryListItemData(
        id = ProductDetailDataList[11].id,
        price = ProductDetailDataList[11].price,
        img =  ProductDetailDataList[11].image[0]
    ),
    TrendCategoryListItemData(
        id = ProductDetailDataList[12].id,
        price = ProductDetailDataList[12].price,
        img =  ProductDetailDataList[12].image[0]
    ),
    TrendCategoryListItemData(
        id = ProductDetailDataList[13].id,
        price = ProductDetailDataList[13].price,
        img =  ProductDetailDataList[13].image[0]
    ),
)

val TrendSuperDealListData = listOf(
    TrendCategoryListItemData(
        id = ProductDetailDataList[14].id,
        price = ProductDetailDataList[14].price,
        img =  ProductDetailDataList[14].image[0]
    ),
    TrendCategoryListItemData(
        id = ProductDetailDataList[15].id,
        price = ProductDetailDataList[15].price,
        img =  ProductDetailDataList[15].image[0]
    ),
    TrendCategoryListItemData(
        id = ProductDetailDataList[16].id,
        price = ProductDetailDataList[16].price,
        img =  ProductDetailDataList[16].image[0]
    ),
)

val TrendUsedListData = listOf(
    TrendCategoryListItemData(
        id = ProductDetailDataList[17].id,
        price = ProductDetailDataList[17].price,
        img =  ProductDetailDataList[17].image[0]
    ),
    TrendCategoryListItemData(
        id = ProductDetailDataList[18].id,
        price = ProductDetailDataList[18].price,
        img =  ProductDetailDataList[18].image[0]
    ),
    TrendCategoryListItemData(
        id = ProductDetailDataList[19].id,
        price = ProductDetailDataList[19].price,
        img =  ProductDetailDataList[19].image[0]
    ),
)