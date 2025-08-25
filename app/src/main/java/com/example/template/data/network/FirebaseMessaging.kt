package com.example.template.data.network

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class FirebaseMessaging : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        println("onMessageReceived :$remoteMessage ")
        println("onMessageReceived : ${remoteMessage.data?.get("notification_type")}")
        remoteMessage?.run {
            this.notification?.title?.let { title ->
                this.notification?.body.let { body ->
                    sendNotification(title, body, this.data)
                }
            }
        }
    }

    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
    }


    private fun sendNotification(title: String, body: String?, data: Map<String, String>) {

        createNotificationChannel()

        var builder = NotificationCompat.Builder(this, CHANNEL_ID)
           // .setSmallIcon(R.drawable.ic_notification)
        //    .setColor(resources.getColor(R.color.colorPrimaryDark,null))
            .setContentTitle(title)
            .setContentText(body?:"")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)


        with(NotificationManagerCompat.from(this)) {
            notify(1, builder.build())
        }
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Default"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance)
            // Register the channel with the system
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

   /* private fun getDeepLink(data: Map<String, String>): PendingIntent {

        Timber.d("getDeepLink :${data?.get("notification_type")}")
        when (data?.get("notification_type")) {

            TYPE_ORDER -> {

                return NavDeepLinkBuilder(appCtx)
                    .setComponentName(MainActivity::class.java)
                    .setGraph(R.navigation.home_nav)
                    .setDestination(R.id.orderDetailFragment)
                    .setArguments(bundleOf("orderId" to data[ORDER_KEY]?.toInt()))
                    .createPendingIntent()
            }


            TYPE_COMMENT -> {
                return NavDeepLinkBuilder(appCtx).setGraph(R.navigation.home_nav)
                    .setComponentName(CommentActivity::class.java)
                    .setDestination(R.id.exploreFragment)
                    .setArguments(bundleOf("orderId" to data["order_id"]?.toInt()))
                    .createPendingIntent()
            }
            TYPE_LIKE -> {
                return NavDeepLinkBuilder(appCtx).setGraph(R.navigation.home_nav)
                    .setComponentName(MainActivity::class.java)
                    .setDestination(R.id.otherUserProfileFragment)
                    .setArguments(bundleOf("id" to data["user_id"]?.toInt()))
                    .createPendingIntent()
            }

            TYPE_FOLLOW -> {
                return NavDeepLinkBuilder(appCtx).setGraph(R.navigation.home_nav)
                    .setComponentName(MainActivity::class.java)
                    .setDestination(R.id.otherUserProfileFragment)
                    .setArguments(bundleOf("id" to data["user_id"]?.toInt()))
                    .createPendingIntent()
            }


            else -> {
                return NavDeepLinkBuilder(appCtx).setGraph(R.navigation.home_nav)
                    .setComponentName(MainActivity::class.java)
                    .setDestination(R.id.orderDetailFragment)
                    .setArguments(bundleOf("orderId" to data["order_id"]?.toInt()))
                    .createPendingIntent()
            }

        }
*/


    companion object {
        const val CHANNEL_ID = "com.example.delightapp"
        const val TYPE_ORDER = "1"
        const val TYPE_FOLLOW = "9"
        const val TYPE_COMMENT = "7"
        const val TYPE_LIKE = "8"
        const val ORDER_KEY = "order_id"
    }

}