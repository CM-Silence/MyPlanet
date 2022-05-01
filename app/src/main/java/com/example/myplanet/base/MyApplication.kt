package com.example.myplanet.base

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

/**
 * @ClassName MyApplication
 * @author Silence~
 * @date 2022/4/27
 * @Description 用于获取全局Context
 */
class MyApplication : Application() {

    companion object {
        @SuppressLint("StaticFieldLeak")
        private lateinit var context : Context
        fun getAppContext(): Context {
            return context
        }
    }

    override fun onCreate(){
        super.onCreate()
        context = applicationContext
    }

}
