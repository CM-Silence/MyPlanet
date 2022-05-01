package com.example.myplanet.page.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.myplanet.R
import com.example.myplanet.base.BaseFragment
import com.example.myplanet.base.MyApplication
import com.example.myplanet.bean.UserBean
import com.example.myplanet.model.LoginModel
import com.example.myplanet.page.dialog.RegisterDialog
import com.example.myplanet.page.dialog.TimerDialog
import com.example.myplanet.view.MyCircle
import com.example.myplanet.view.MyTimer

/**
 * @ClassName TimerFragment
 * @author Silence~
 * @date 2022/5/1
 * @Description
 */
class TimerFragment(title : String = "") : BaseFragment(title) {
    private lateinit var mTvTime: TextView
    private lateinit var mBtnPlanet: ImageButton
    private lateinit var mMyTimer: MyTimer
    private lateinit var mMyCircle: MyCircle
    private lateinit var mIvMoon: ImageView

    private var isCountDown = false //是否在倒计时

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_timer, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
        initEvent()
    }

    private fun initView(view: View) {
        mTvTime = view.findViewById(R.id.fragment_timer_tv_time)
        mBtnPlanet = view.findViewById(R.id.fragment_timer_btn_planet)
        mMyTimer = view.findViewById(R.id.fragment_timer_myTimer)
        mMyCircle = view.findViewById(R.id.view_mycircle_circle)
        mIvMoon = view.findViewById(R.id.view_iv_moon)
        mBtnPlanet.bringToFront()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun initEvent() {
        mBtnPlanet.setOnClickListener {
            if(!isCountDown) {
                Toast.makeText(MyApplication.getAppContext(), "planet", Toast.LENGTH_SHORT).show()
            }
        }

        mMyCircle.setOnLongClickListener {
            Toast.makeText(MyApplication.getAppContext(), "timer", Toast.LENGTH_SHORT).show()
            TimerDialog(this.requireContext() , object : TimerDialog.OnCloseListener{
                @SuppressLint("SetTextI18n")
                override fun onClose(minute : Int, second : Int) {
                    mTvTime.text = "${minute.toTimeString()}:${second.toTimeString()}"
                }
            }).show()
            return@setOnLongClickListener true
        }


    }

    /**
     * @Description 如果数字大于10则返回原数字,如果小于10则在数字前加"0"再返回
     * @return 一个字符串
     * @author Silence~
     * @date 2022/5/2 0:05
     */
    fun Int.toTimeString() : String{
        if(this < 10){
            return "0${this}"
        }
        return this.toString()
    }

    /**
     * @ClassName MyHandler
     * @author Silence~
     * @date 2022/5/1
     * @Description 用于处理计时器UI问题的静态内部类
     */
    private class MyHandler : Handler(Looper.getMainLooper()) {

        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when (msg.what) {
                1 -> return
            }
        }
    }
}