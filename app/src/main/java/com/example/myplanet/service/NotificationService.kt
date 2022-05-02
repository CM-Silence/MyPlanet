package com.example.myplanet.service

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import com.example.myplanet.page.activity.MainActivity

/**
 * @ClassName NotificationService
 * @author Silence~
 * @date 2022/5/2
 * @Description 前台服务,用于通知计时器的消息
 */
class NotificationService : Service() {

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    companion object{
        fun startService(context : Context){
            val intent = Intent(context, NotificationService::class.java)
            context.startService(intent)
        }
    }
}