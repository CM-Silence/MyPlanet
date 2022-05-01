package com.example.myplanet.page.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.myplanet.R
import com.example.myplanet.base.BaseActivity
import com.example.myplanet.bean.UserBean

/**
 * @ClassName MainActivity
 * @author Silence~
 * @date 2022/4/28
 * @Description 主活动
 */
class MainActivity : BaseActivity() {
    private lateinit var mUserBean: UserBean

    companion object{
        /**
         * @Description 启动MainActivity的方法
         * @Param userBean 从LoginActivity传入的UserBean
         * @date 2022/5/1 15:07
         */
        fun startActivity(context: Context,userBean: UserBean){
            val intent = Intent(context, MainActivity::class.java)
            intent.putExtra("param1",userBean)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mUserBean = intent.getSerializableExtra("param1") as UserBean
    }
}