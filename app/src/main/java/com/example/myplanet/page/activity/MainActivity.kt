package com.example.myplanet.page.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.myplanet.R
import com.example.myplanet.base.BaseActivity

/**
 * @ClassName MainActivity
 * @author Silence~
 * @date 2022/4/28
 * @Description 主活动
 */
class MainActivity : BaseActivity() {

    companion object{
        fun startActivity(context: Context){
            val intent = Intent(context, MainActivity::class.java)

            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}