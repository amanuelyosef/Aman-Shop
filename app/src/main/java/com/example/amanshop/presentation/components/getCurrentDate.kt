package com.example.amanshop.presentation.components

import android.os.Build
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Date

fun getCurrentDate(format: String = "dd/MM/yyyy") : String{

    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
        val currentDateTime = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern(format)
        return currentDateTime.format(formatter)
    }else{
        val currentDate = Date()
        val formatter = SimpleDateFormat(format)
        return formatter.format(currentDate)
    }
}