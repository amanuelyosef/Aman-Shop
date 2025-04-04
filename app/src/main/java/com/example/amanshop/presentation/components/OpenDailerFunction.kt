package com.example.amanshop.presentation.components

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast


fun openDialer(context: Context, phoneNumber: String) {
    try{
        val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
        context.startActivity(intent)
    }catch (e:Exception){
        Toast.makeText(context, "Unable to open dialer",Toast.LENGTH_SHORT).show()
    }
}

fun monthNumberToName(month: Int): String {
    val months = listOf(
        "January", "February", "March", "April", "May", "June",
        "July", "August", "September", "October", "November", "December"
    )
    return months.getOrNull(month - 1) ?: "Invalid month"
}