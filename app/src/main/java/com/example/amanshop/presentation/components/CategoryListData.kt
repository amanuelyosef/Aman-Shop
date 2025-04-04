package com.example.amanshop.presentation.components

import androidx.compose.runtime.Immutable
import com.example.amanshop.R

@Immutable
data class HomeCategoryListItemData(
    val title:String,
    val img:Int,
)

val CategoryListData = listOf(
    HomeCategoryListItemData("Agriculture and Food", R.drawable.cg_agriculture_and_food),
    HomeCategoryListItemData("Babies and Toys", R.drawable.cg_babies_and_toys),
    HomeCategoryListItemData("Business Equipment", R.drawable.cg_business_equipment),
    HomeCategoryListItemData("Construction", R.drawable.cg_construction),
    HomeCategoryListItemData("Electronics", R.drawable.cg_electronics),
    HomeCategoryListItemData("Fashion", R.drawable.cg_fashion),
    HomeCategoryListItemData("Health and Beauty", R.drawable.cg_health_and_beauty),
    HomeCategoryListItemData("Home Appliances", R.drawable.cg_home_appliances),
    HomeCategoryListItemData("Jobs", R.drawable.cg_jobs),
    HomeCategoryListItemData("Pets", R.drawable.cg_pets),
    HomeCategoryListItemData("Phone and Tablets", R.drawable.cg_phones_and_tablets),
    HomeCategoryListItemData("Property", R.drawable.cg_property),
    HomeCategoryListItemData("Seek Work", R.drawable.cg_seek_work),
    HomeCategoryListItemData("Service", R.drawable.cg_services),
    HomeCategoryListItemData("Vehicle", R.drawable.cg_vehicles),
)
