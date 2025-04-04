package com.example.amanshop.presentation.searched_list_screen.components

data class BrandFilterDataItem(
    val name : String,
    var selected : Boolean = false
)

val brandFilterData = listOf(
    BrandFilterDataItem("HP"),
    BrandFilterDataItem("Dell"),
    BrandFilterDataItem("Acer"),
    BrandFilterDataItem("Apple"),
    BrandFilterDataItem("Toshiba"),
    BrandFilterDataItem("Samsung"),
    BrandFilterDataItem("Lenovo"),
)