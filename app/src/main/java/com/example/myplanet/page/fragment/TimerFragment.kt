package com.example.myplanet.page.fragment

import android.annotation.SuppressLint
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.*
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.example.myplanet.R
import com.example.myplanet.base.BaseFragment
import com.example.myplanet.bean.PlanetBean
import com.example.myplanet.bean.UserBean
import com.example.myplanet.model.LoginModel
import com.example.myplanet.page.activity.MainActivity
import com.example.myplanet.page.dialog.ChoosePlanetDialogFragment
import com.example.myplanet.page.dialog.TimerDialog
import com.example.myplanet.service.TimerService
import com.example.myplanet.utils.TimeUtil
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
    private lateinit var mTvName: TextView
    private lateinit var mBtnPlanet: ImageButton
    private lateinit var mMyTimer: MyTimer
    private lateinit var mMyCircle: MyCircle
    private lateinit var mIvMoon: ImageView

    private lateinit var userBean: UserBean

    private lateinit var timeBinder: TimerService.TimerBinder

    private lateinit var timerService : TimerService


    /**
     * @Description TimerService通过接口回调的方式来实现计时器的功能
     * @author Silence~
     * @date 2022/5/3 0:43
     */
    private val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            timeBinder = service as TimerService.TimerBinder
            timeBinder.getTimeFromActivity(minute,second)
            timerService = timeBinder.getService()
            timerService.setOnTimeChangeListener(object : TimerService.OnTimeChangeListener {
                override fun onTimeChange(minute: Int, second: Int) {
                    this@TimerFragment.minute = minute
                    this@TimerFragment.second = second
                    userBean.getPlanetList()[0].addTime(1) //星球专注时间+1
                    //用handler发消息把UI操作交给主线程
                    val msg = Message.obtain()
                    msg.what = 1
                    mHandler.sendMessage(msg)
                }

                override fun onOver() {
                    isCountDown = false
                    LoginModel.addNonPasswordUserBean(userBean)
                }
            })
        }

        override fun onServiceDisconnected(p0: ComponentName?) {}
    }

    //private val mMyHandler = MyHandler()
    private val mHandler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                1 -> timeChange()
            }
        }
    }

    @Volatile
    private var isCountDown = false //是否在倒计时
    @Volatile
    private var minute = 0
    @Volatile
    private var second = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_timer, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        initView(view)
        initEvent()

        val intent = Intent(this.context, TimerService::class.java)
        this.activity?.bindService(intent, connection, Context.BIND_AUTO_CREATE)
    }

    private fun initView(view: View) {
        mTvTime = view.findViewById(R.id.fragment_timer_tv_time)
        mTvName = view.findViewById(R.id.fragment_timer_tv_planetname)
        mBtnPlanet = view.findViewById(R.id.fragment_timer_btn_planet)
        mMyTimer = view.findViewById(R.id.fragment_timer_myTimer)
        mMyCircle = view.findViewById(R.id.view_mycircle_circle)
        mIvMoon = view.findViewById(R.id.view_iv_moon)
        mBtnPlanet.bringToFront()

        mTvName.text = userBean.getPlanetList()[0].getName()
        mBtnPlanet.setImageResource(userBean.getPlanetList()[0].getImageID())
    }

    private fun initData(){
        if(activity != null) {
            val activity = activity as MainActivity
            userBean = activity.getUserBean()
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun initEvent() {
        mBtnPlanet.setOnClickListener {
            if(!isCountDown) {
                ChoosePlanetDialogFragment(this.requireActivity(),userBean.getPlanetList(),object  : ChoosePlanetDialogFragment.OnCloseListener{
                    override fun onClose(planet : PlanetBean) {
                        mBtnPlanet.setImageResource(planet.getImageID())
                        mTvName.text = planet.getName()
                        userBean.setFirstPlanet(planet)
                    }
                }).show()
            }
        }

        mMyCircle.setOnLongClickListener {
            TimerDialog(this.requireContext() ,minute ,second ,isCountDown ,object : TimerDialog.OnCloseListener{
                override fun onClose(minute : Int, second : Int) {
                    timeChange()
                    this@TimerFragment.minute = minute
                    this@TimerFragment.second = second
                    countDown()
                }
            }).show()
            return@setOnLongClickListener true
        }
    }

    /**
     * @Description 时间改变时更改mTvTime和mMyCircle的方法
     * @date 2022/5/2 0:37
     */
    @SuppressLint("SetTextI18n")
    private fun timeChange(){
        mTvTime.text = TimeUtil.toTime(minute,second)
        mMyTimer.setProcess(minute,second)
    }

    /**
     * @Description 开始倒计时的方法
     * @author Silence~
     * @date 2022/5/2 0:23
     */
    private fun countDown(){
        mMyTimer.startCountDown(minute,second)
        timerService.startCountDown(minute,second)
        isCountDown = true
    }

    /**
     * @Description 退出后保存用户数据
     * @date 2022/5/2 21:29
     */
    override fun onStop() {
        super.onStop()
        LoginModel.addNonPasswordUserBean(userBean)
    }

    override fun onDestroy() {
        super.onDestroy()
        this.activity?.unbindService(connection)
    }

    /*
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

            }
        }
    }

    */
}