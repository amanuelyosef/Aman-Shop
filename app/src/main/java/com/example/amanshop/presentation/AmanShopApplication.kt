package com.example.amanshop.presentation

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.TaskStackBuilder
import androidx.core.content.getSystemService
import androidx.core.net.toUri
import com.example.amanshop.R
import com.example.amanshop.presentation.components.TopNotificationData
import com.example.amanshop.presentation.message_list_screen.components.messageListData
import com.example.amanshop.presentation.ui.theme.LocalString

class AmanShopApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        createMessageNotificationChannel()
        showGroupedNotifications()
    }

    private fun createMessageNotificationChannel(){

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel(
                LocalString.message_notification_channel_id,
                "Message Notifications",
                NotificationManager.IMPORTANCE_HIGH
            )
            channel.description = "This is for to notifying incoming message"

            val notificationManager = getSystemService<NotificationManager>()!!
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun showGroupedNotifications() {
        val notificationManager = getSystemService<NotificationManager>()!!
        val notificationSequence = mutableListOf<TopNotificationData>()

        messageListData.forEach{item->
            if(item.numberOfUnseen>0){
                notificationSequence.add(
                    TopNotificationData(
                        sellerName = item.sellerName,
                        message = item.latestMessage!!,
                        productImage = item.productImg,
                        deepLink = LocalString.app_url + LocalString.chat_screen_path + item.productId
                    )
                )
            }
        }

        notificationSequence.forEachIndexed {index, item ->
            val notification = NotificationCompat.Builder(this, LocalString.message_notification_channel_id)
                .setSmallIcon(R.drawable.ic_notification_foreground)
                .setContentTitle(item.sellerName)
                .setContentText(item.message)
                .setPriority(NotificationManager.IMPORTANCE_HIGH)
                .setAutoCancel(true)
                .setLargeIcon(loadImage(this, item.productImage))
                .setContentIntent(createPendingIntent(item.deepLink))
                .setGroup("my_group")
                .build()
            notificationManager.notify(index+1, notification)
        }

        val summaryNotification = NotificationCompat.Builder(this, LocalString.message_notification_channel_id)
            .setSmallIcon(R.drawable.ic_notification_foreground)
            .setPriority(NotificationManager.IMPORTANCE_HIGH)
            .setAutoCancel(true)
            .setContentIntent(createPendingIntent(LocalString.app_url + LocalString.message_notification_channel_id))
            .setGroup("my_group")
            .setGroupSummary(true)
            .build()
        notificationManager.notify(notificationSequence.size+1, summaryNotification)
    }

    private fun createPendingIntent(deepLink: String): PendingIntent? {
        val activityIntent = Intent(this, MainActivity::class.java).apply {
            data = deepLink.toUri()
        }
        return TaskStackBuilder.create(this).run {
            addNextIntentWithParentStack(activityIntent)
            getPendingIntent(0, PendingIntent.FLAG_IMMUTABLE)
        }
    }

    private fun loadImage(context: Context, imageResId: Int): Bitmap? {
        return BitmapFactory.decodeResource(context.resources, imageResId)
    }
}