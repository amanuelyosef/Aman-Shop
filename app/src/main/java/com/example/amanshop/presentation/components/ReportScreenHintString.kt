package com.example.amanshop.presentation.components

sealed class ReportScreenHint(val hint:String){
    data object Product : ReportScreenHint("product")
    data object Seller : ReportScreenHint("seller")
    data object Feedback : ReportScreenHint("feedback")
}