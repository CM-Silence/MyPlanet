package com.example.myplanet.page.activity

import android.os.Bundle
import com.example.myplanet.R
import com.example.myplanet.base.BaseActivity

/**
 * @ClassName StartActivity
 * @author Silence~
 * @date 2022/4/28
 * @Description 启动界面,等待片刻后进入LoginActivity
 */
class StartActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        Thread {
            try {
                Thread.sleep(5000L)
                LoginActivity.startActivity(this)
                finish()
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }.start()
    }
}