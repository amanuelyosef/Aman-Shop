package com.example.amanshop.presentation.chat_screen.components

import com.example.amanshop.presentation.components.SellerDataItem
import com.example.amanshop.presentation.components.ProductDetailDataList

data class ChatScreenData(
    val productId : String,
    val sellerInfo : SellerDataItem = ProductDetailDataList.find{it.id==productId}!!.sellerData,
    val productImg : Int = ProductDetailDataList.find{it.id==productId}!!.image[0],
    val productTitle : String = ProductDetailDataList.find{it.id==productId}!!.title,
    val productPrice : String= ProductDetailDataList.find{it.id==productId}!!.price,
    val chat : MutableList<ChatDataItem>,
)

val ConversationsDataList = mutableListOf(
    ChatScreenData(
        productId = ProductDetailDataList[0].id,
        chat = mutableListOf(
            ChatDataItem(
                seller = true,
                message = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when",
                date = "13/02/2025",
                timestamp = "12:00 AM",
                seen = false
            ),
            ChatDataItem(
                seller = true,
                message = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when",
                date = "13/02/2025",
                timestamp = "12:00 AM",
                seen = false
            ),
            ChatDataItem(
                seller = false,
                message = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when",
                date = "13/02/2025",
                timestamp = "12:00 AM"
            ),
            ChatDataItem(
                seller = true,
                message = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when",
                date = "12/02/2025",
                timestamp = "12:00 AM",
            ),
            ChatDataItem(
                seller = false,
                message = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when",
                date = "12/02/2025",
                timestamp = "12:00 AM",
            ),
        )
    ),
    ChatScreenData(
        productId = ProductDetailDataList[1].id,
        chat = mutableListOf(

            ChatDataItem(
                seller = true,
                message = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when",
                date = "13/02/2025",
                timestamp = "12:00 AM",
                seen = false
            ),
            ChatDataItem(
                seller = true,
                message = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when",
                date = "13/02/2025",
                timestamp = "12:00 AM",
                seen = false
            ),
            ChatDataItem(
                seller = false,
                message = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when",
                date = "13/02/2025",
                timestamp = "12:00 AM"
            ),
            ChatDataItem(
                seller = true,
                message = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when",
                date = "12/02/2025",
                timestamp = "12:00 AM",
            ),
            ChatDataItem(
                seller = false,
                message = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when",
                date = "12/02/2025",
                timestamp = "12:00 AM",
            ),
        )
    ),
    ChatScreenData(
        productId = ProductDetailDataList[2].id,
        chat = mutableListOf(

            ChatDataItem(
                seller = false,
                message = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when",
                date = "13/02/2025",
                timestamp = "12:00 AM"
            ),
            ChatDataItem(
                seller = true,
                message = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when",
                date = "13/02/2025",
                timestamp = "12:00 AM"
            ),
            ChatDataItem(
                seller = true,
                message = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when",
                date = "13/02/2025",
                timestamp = "12:00 AM"
            ),
            ChatDataItem(
                seller = false,
                message = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when",
                date = "12/02/2025",
                timestamp = "12:00 AM",
                seen = true

            ),
            ChatDataItem(
                seller = false,
                message = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when",
                date = "12/02/2025",
                timestamp = "12:00 AM",
                seen = false
            ),
        )
    ),
    ChatScreenData(
        productId = ProductDetailDataList[3].id,
        chat = mutableListOf(

            ChatDataItem(
                seller = false,
                message = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when",
                date = "13/02/2025",
                timestamp = "12:00 AM"
            ),
            ChatDataItem(
                seller = true,
                message = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when",
                date = "13/02/2025",
                timestamp = "12:00 AM"
            ),
            ChatDataItem(
                seller = true,
                message = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when",
                date = "13/02/2025",
                timestamp = "12:00 AM"
            ),
            ChatDataItem(
                seller = false,
                message = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when",
                date = "12/02/2025",
                timestamp = "12:00 AM",
                seen = true

            ),
            ChatDataItem(
                seller = false,
                message = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when",
                date = "12/02/2025",
                timestamp = "12:00 AM",
                seen = false
            ),
        )
    ),
    ChatScreenData(
        productId = ProductDetailDataList[4].id,
        chat = mutableListOf(
            ChatDataItem(
                seller = true,
                message = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when",
                date = "13/02/2025",
                timestamp = "12:00 AM"
            ),
            ChatDataItem(
                seller = true,
                message = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when",
                date = "13/02/2025",
                timestamp = "12:00 AM"
            ),
            ChatDataItem(
                seller = false,
                message = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when",
                date = "13/02/2025",
                timestamp = "12:00 AM"
            ),
            ChatDataItem(
                seller = true,
                message = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when",
                date = "12/02/2025",
                timestamp = "12:00 AM"
            ),
            ChatDataItem(
                seller = false,
                message = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when",
                date = "12/02/2025",
                timestamp = "12:00 AM",
            ),
        )
    )
)