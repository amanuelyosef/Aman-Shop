package com.example.amanshop.presentation.search_screen.components

import com.example.amanshop.presentation.components.ProductDetailDataList
import com.example.amanshop.presentation.components.TrendCategoryListItemData

val recentlyViewedListData = mutableListOf(
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