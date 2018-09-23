package com.shardbytes.notificationhello

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.NotificationCompat
import kotlinx.android.synthetic.main.activity_main.*
import android.app.NotificationManager
import android.app.NotificationChannel
import android.media.RingtoneManager
import android.os.Build
import android.support.v4.app.NotificationManagerCompat


class MainActivity : AppCompatActivity() {
    
    private lateinit var notificationBuilder : NotificationCompat.Builder
    private lateinit var notificationManager: NotificationManagerCompat
    
    val CHANNEL_ID = "NotificationHello"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        createNotificationChannel()
        
        notificationManager = NotificationManagerCompat.from(this)
        
        notificationBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("ContentTitle")
                .setContentText("ContentText")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setAutoCancel(true)
        
        
        buttonNotify.setOnClickListener { 
            it.postDelayed({
                notificationManager.notify(1, notificationBuilder.build())
            }, 2000)
        }
        
    }

    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = CHANNEL_ID
            val description = "some weird channel"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance)
            channel.description = description
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager!!.createNotificationChannel(channel)
        }
    }
}
