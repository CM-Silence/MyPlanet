package com.example.myplanet.page.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.LifecycleRegistry
import androidx.viewpager2.widget.ViewPager2
import com.example.myplanet.R
import com.example.myplanet.base.BaseActivity
import com.example.myplanet.base.BaseFragment
import com.example.myplanet.bean.UserBean
import com.example.myplanet.page.adapter.MainVp2Adapter
import com.example.myplanet.page.fragment.MineFragment
import com.example.myplanet.page.fragment.TimerFragment
import com.example.myplanet.page.fragment.UniverseFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

/**
 * @ClassName MainActivity
 * @author Silence~
 * @date 2022/4/28
 * @Description 主活动
 */
class MainActivity : BaseActivity() {
    private lateinit var mUserBean: UserBean
    private val frgList = ArrayList<BaseFragment>()

    private lateinit var mVp2Page : ViewPager2
    private lateinit var mTlPage : TabLayout

    //private var pressTime = 0L //按压计时器的时间,用于检测计时器的长按事件
    //private var px = 0f
    //private var py = 0f

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
        initView()
        initFragmentList()
        initViewPager2()
    }

    private fun initView(){
        mVp2Page = findViewById(R.id.activity_main_vp2)
        mTlPage = findViewById(R.id.activity_main_tl)
    }

    private fun initFragmentList(){
        frgList.run{
            add(TimerFragment("计时器"))
            add(UniverseFragment("universe"))
            add(MineFragment("我的"))
        }
    }

    private fun initViewPager2(){
        val mainVp2Adapter = MainVp2Adapter(this.supportFragmentManager,frgList,LifecycleRegistry(this))
        mVp2Page.adapter = mainVp2Adapter

        TabLayoutMediator(mTlPage, mVp2Page
        ) { tab, position -> //在这里给Tab设置Text
            tab.text = frgList[position].getTitle()
        }.attach()
    }

}