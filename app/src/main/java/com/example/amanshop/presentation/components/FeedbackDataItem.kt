package com.example.amanshop.presentation.components

import java.util.UUID

data class FeedbackDataItem(
    val id:String = UUID.randomUUID().toString(),
    val customerName:String,
    val customerImage : Int,
    val feedback: String,
    val date : String,
    var numberOfLike : Int,
    var numberOfDislike : Int,
    var customerReaction : Boolean? = null
)
