package com.example.myplanet.service

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Binder
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.example.myplanet.R
import com.example.myplanet.page.activity.MainActivity

/**
 * @ClassName NotificationService
 * @author Silence~
 * @date 2022/5/2
 * @Description 前台服务,用于通知计时器的消息
 */
class NotificationService : Service() {
    private lateinit var manager : NotificationManager
    private lateinit var notification : Notification
    private lateinit var channel :NotificationChannel
    private lateinit var intent : Intent
    private lateinit var pendingIntent : PendingIntent

    private val mBinder = TimerBinder()

    class TimerBinder : Binder() {
        private var time : String = "00:00"
        fun timeChange(time : String){
            this.time = time
        }
        fun getTime() = time
    }

    companion object{
        fun startService(context : Context){
            val intent = Intent(context, NotificationService::class.java)
            context.startService(intent)
        }

        fun stopService(context : Context){
            val intent = Intent(context, NotificationService::class.java)
            context.stopService(intent)
        }
    }

    override fun onBind(intent: Intent): IBinder {
        return mBinder
    }

    override fun onCreate() {
        super.onCreate()
        initNotification()
        notification = NotificationCompat.Builder(this,"time")
            .setContentTitle("倒计时提醒")
            .setContentText("距离倒计时结束还有:${mBinder.getTime()}")
            .setSmallIcon(R.drawable.drawable_earth)
            .setLargeIcon(BitmapFactory.decodeResource(resources,R.drawable.drawable_earth))
            .setContentIntent(pendingIntent)
            .build()
        startForeground(1,notification)
    }

    private fun initNotification(){
        manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        channel = NotificationChannel("time","倒计时提醒",NotificationManager.IMPORTANCE_DEFAULT)
        manager.createNotificationChannel(channel)
        intent = Intent(this, MainActivity::class.java)
        pendingIntent = PendingIntent.getActivity(this,0,intent,0)
    }

}