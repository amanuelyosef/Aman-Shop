package com.example.amanshop.presentation.chat_screen.components

import android.net.Uri
import java.io.File
import java.util.UUID


data class ChatDataItem(
    val id : String = UUID.randomUUID().toString(),
    val type : String = "text",
    val seller : Boolean,
    val message: String? = null,
    val file : File? = null,
    val uri : Uri? = null,
    val timestamp : String,
    val date : String,
    var seen : Boolean = true
)
