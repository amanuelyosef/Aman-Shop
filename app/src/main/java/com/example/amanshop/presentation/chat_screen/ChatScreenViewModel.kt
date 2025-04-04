package com.example.amanshop.presentation.chat_screen

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class ChatScreenViewModel:ViewModel() {

    val visiblePermissionDialogQueue = mutableStateListOf<String>()

    fun dismissDialog() {
        visiblePermissionDialogQueue.removeAt(0)
    }

    fun onPermissionResult(
        permission: String,
        isGranted: Boolean
    ) {
        if(!isGranted && !visiblePermissionDialogQueue.contains(permission)) {
            visiblePermissionDialogQueue.add(permission)
        }
    }

}