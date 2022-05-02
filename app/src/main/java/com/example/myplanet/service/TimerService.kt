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
import com.example.myplanet.utils.TimeUtil

/**
 * @ClassName TimerService
 * @author Silence~
 * @date 2022/5/2
 * @Description 前台服务,用于发出通知,进行倒计时
 */
class TimerService : Service() {
    private lateinit var manager : NotificationManager
    private lateinit var notification : Notification
    private lateinit var channel :NotificationChannel
    private lateinit var intent : Intent
    private lateinit var pendingIntent : PendingIntent

    private val mBinder = TimerBinder()

    private lateinit var timeChangeThread : Thread

    private lateinit var onTimeChangeListener: OnTimeChangeListener

    inner class TimerBinder : Binder() {
        private var minute : Int = 0
        private var second : Int = 0
        fun getTimeFromActivity(minute : Int,second : Int){
            this.minute = minute
            this.second = second
        }
        /**
         * @Description 获取当前服务
         * @date 2022/5/3 0:25
         */
        fun getService(): TimerService {
            return this@TimerService
        }
        fun getMinute() = minute
        fun getSecond() = second
    }


    @Volatile
    private var isCountDown = false //是否在倒计时
    @Volatile
    private var minute = 0
    @Volatile
    private var second = 0

    override fun onBind(intent: Intent): IBinder {
        return mBinder
    }

    override fun onCreate() {
        super.onCreate()
        initData()
        initNotification()
    }

    private fun initData(){
        minute = mBinder.getMinute()
        second = mBinder.getSecond()
    }

    private fun initNotification(){
        manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        channel = NotificationChannel("time","倒计时提醒",NotificationManager.IMPORTANCE_LOW)
        manager.createNotificationChannel(channel)
        intent = Intent(this, MainActivity::class.java)
        pendingIntent = PendingIntent.getActivity(this,0,intent,0)
    }

    /**
     * @Description 更新通知的方法
     * @date 2022/5/3 0:49
     */
    private fun upDateNotification(){
        notification = NotificationCompat.Builder(this, "time")
            .setContentTitle("倒计时提醒")
            .setContentText("距离倒计时结束还有${TimeUtil.toTime(minute,second)}")
            .setSmallIcon(R.drawable.drawable_earth)
            .setLargeIcon(
                BitmapFactory.decodeResource(
                    resources,
                    R.drawable.drawable_earth
                )
            )
            .setContentIntent(pendingIntent)
            .build()
        startForeground(1, notification)
    }

    /**
     * @Description 开始倒计时的方法
     * @date 2022/5/3 0:29
     */
    fun startCountDown(m: Int,s: Int) {
        minute = m
        second = s
        if (!isCountDown) {
            timeChangeThread = Thread {
                while (second != 0 || minute != 0) {
                    Thread.sleep(1000L) //等待一秒
                    //时间运算
                    if (second != 0) {
                        second--
                    } else if (second == 0 && minute != 0) {
                        minute--
                        second += 59
                    }
                    onTimeChangeListener.onTimeChange(minute, second)
                    upDateNotification()
                }
                isCountDown = false
                onTimeChangeListener.onOver()
            }
            timeChangeThread.start()
        } else { //如果已经在倒计时则停止原来的计时并重新开始
            Thread {
                timeChangeThread.interrupt()
                Thread.sleep(1000)
                timeChangeThread.start()
            }
        }
        isCountDown = true
    }

    fun setOnTimeChangeListener(onTimeChangeListener: OnTimeChangeListener) {
        this.onTimeChangeListener = onTimeChangeListener
    }


    /**
     * @ClassName OnTimeChangeListener
     * @author Silence~
     * @date 2022/5/3
     * @Description 用于监测时间变化的接口
     */
    interface OnTimeChangeListener{
        fun onTimeChange(minute: Int,second: Int)
        fun onOver()
    }

}