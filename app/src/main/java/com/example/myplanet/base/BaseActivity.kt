package com.example.myplanet.base

import android.app.Activity
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity

/**
 * @ClassName BaseActivity
 * @author Silence~
 * @date 2022/4/27
 */
open class BaseActivity : AppCompatActivity() {

    /**
     * @Description 在Activity被创建时将其添加进ActivityManager类中的列表中
     * @date 2022/4/27 11:25
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT //锁定竖屏
        fullScreen(this)
        Log.d("BaseActivity","${javaClass.simpleName} onCreate") //打印当前活动的类名
        AppManager.addActivity(this)
    }

    /**
     * @Description 在Activity被销毁时将其从ActivityManager类中的列表中移除
     * @date 2022/4/27 11:25
     */
    override fun onDestroy() {
        super.onDestroy()
        Log.d("BaseActivity","${javaClass.simpleName} onDestroy") //打印当前活动的类名
        AppManager.removeActivity(this)
    }
    private fun fullScreen(activity: Activity) {
        val decorView = activity.window.decorView
        decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
    }

    //防止按回退键后直接退出程序
    override fun onBackPressed() {
        moveTaskToBack(true)
    }
}
