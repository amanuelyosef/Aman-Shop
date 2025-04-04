package com.example.amanshop.presentation.message_list_screen.components

import com.example.amanshop.presentation.chat_screen.components.ConversationsDataList


data class MessageListItem(
    val productId : String ,
    val sellerName : String = ConversationsDataList.find{it.productId == productId}!!.sellerInfo.name,
    val productTitle : String = ConversationsDataList.find{it.productId == productId}!!.productTitle,
    val productImg : Int = ConversationsDataList.find{it.productId == productId}!!.productImg,
    var numberOfUnseen : Int = ConversationsDataList.find{it.productId == productId}!!.chat.count{!it.seen && it.seller},
    var latestMessage : String? =
        if(ConversationsDataList.find{it.productId == productId}!!.chat.size!=0) ConversationsDataList.find{it.productId == productId}!!.chat.last().message
        else "",
    val latestMessageDate : String =
        if(ConversationsDataList.find{it.productId == productId}!!.chat.size!=0) ConversationsDataList.find{it.productId == productId}!!.chat.last().date
        else ""
    )

val messageListData = mutableListOf(
    MessageListItem(ConversationsDataList[0].productId),
    MessageListItem(ConversationsDataList[1].productId),
    MessageListItem(ConversationsDataList[2].productId),
    MessageListItem(ConversationsDataList[3].productId),
    MessageListItem(ConversationsDataList[4].productId)
)

