package com.example.ast.data


import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.support.v4.app.NotificationCompat
import android.util.Log
import com.example.ast.App
import com.example.ast.R
import com.example.ast.data.local.GlobalSettings
import com.example.ast.ui.login.LoginActivity

import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


/**
 * Created by dfgden on 12/12/17.
 */
class ASTNotificationService : FirebaseMessagingService() {

    @Inject
    lateinit var globalSetting: GlobalSettings

    @Inject
    lateinit var dataManager: DataManager

    override fun onCreate() {
        super.onCreate()
        App.getApplicationComponent()!!.inject(this)
    }


    override fun onMessageReceived(p0: RemoteMessage?) {
        if (globalSetting.token == null) {
            return
        }

        val serializableBody = p0?.notification?.body
        val serializableTitle = p0?.notification?.title
        if (serializableBody != null) {
            try {
                val intent = LoginActivity.newIntent(this)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                val pendingIntent = PendingIntent.getActivity(
                    this, 1010101, intent,
                    PendingIntent.FLAG_ONE_SHOT
                )

                val title = serializableTitle ?: "Авария"
                val text = serializableBody
                val channelId = "ast_message"
                val smallIconId = R.mipmap.ic_launcher
                val notificationManager = this.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    val mChannel: NotificationChannel =
                        NotificationChannel(channelId, title, NotificationManager.IMPORTANCE_HIGH)
                    mChannel.setShowBadge(true)
                    mChannel.enableLights(true)
                    mChannel.enableVibration(true)
                    mChannel.lightColor = Color.GREEN
                    mChannel.lockscreenVisibility = NotificationCompat.VISIBILITY_PUBLIC
                    mChannel.description = text
                    notificationManager.createNotificationChannel(mChannel)
                }

                val builder = NotificationCompat.Builder(this, channelId)
                builder.setContentTitle(title)
                    .setSmallIcon(smallIconId)
                    .setContentTitle(title)
                    .setAutoCancel(true)
                    .setContentText(text)
                    .setLights(Color.BLUE, 100, 50)
                    .setPriority(NotificationManager.IMPORTANCE_HIGH)
                    .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                    .setDefaults(Notification.DEFAULT_ALL)
                    .setContentIntent(pendingIntent)

                notificationManager.notify(44 /* ID of notification */, builder.build())

            } catch (e: Exception) {
                //nothing because bad json
            }
        }

    }

    override fun onNewToken(p0: String) {
        dataManager.updatePushToken(com.example.ast.data.remote.models.Notification(p0))
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                //                    Log.d("mym", "sendRegistrationToServer success: " + it)
            }, {
                Log.e("mym", "sendRegistrationToServer error " + it.message)
            })
    }


}
