package com.example.myplanet.page.dialog

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.core.widget.addTextChangedListener
import com.example.myplanet.R
import com.example.myplanet.bean.UserBean
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

/**
 * @ClassName TimerDialog
 * @author Silence~
 * @date 2022/5/1
 * @Description 计时器的窗口,用于调整倒计时的时间
 */
class TimerDialog(@NonNull context: Context, private var minute: Int, private var second: Int ,private val isCountDown : Boolean, private val listener: OnCloseListener) : Dialog(context) {
    private lateinit var mSbTime : SeekBar
    private lateinit var mTvTime : TextView
    private lateinit var mBtnCountDown : Button
    private lateinit var mBtnCancel : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //去掉系统的黑色矩形边框
        window!!.setBackgroundDrawableResource(android.R.color.transparent)
        requestWindowFeature(Window.FEATURE_NO_TITLE) // 加上这句后就可以在 xml 中自定义高和宽

        setContentView(R.layout.dialog_timer)

        setCanceledOnTouchOutside(false) // 设置窗口边界外触摸时不关闭 dialog

        initView()
        initClick()
        initEvent()
    }

    @SuppressLint("SetTextI18n")
    private fun initView() {
        mSbTime = findViewById(R.id.dialog_sb_timer_time)
        mTvTime = findViewById(R.id.dialog_tv_timer_time)
        mBtnCountDown = findViewById(R.id.dialog_btn_timer_countdown)
        mBtnCancel = findViewById(R.id.dialog_btn_timer_cancel)

        if(isCountDown){
            mBtnCountDown.text = "更改计时"
            mSbTime.setProgress((minute * 60 + second) / 72, true)
            mTvTime.text = "${minute.toTimeString()}:${second.toTimeString()}"
        }
    }

    private fun initClick() {
        mBtnCancel.setOnClickListener {
            dismiss()
        }
        mBtnCountDown.setOnClickListener {
            countDown()
        }
    }

    /**
     * @Description 主要是处理SeekBar的事件
     * @date 2022/5/1 23:54
     */
    private fun initEvent() {
        mSbTime.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {

            @SuppressLint("SetTextI18n")
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, b: Boolean) {
                second = progress * 72
                minute = 0
                while(second >= 60){
                    minute++
                    second -= 60
                }
                mTvTime.text = "${minute.toTimeString()}:${second.toTimeString()}"
            }
            override fun onStartTrackingTouch(seekBar: SeekBar) {

            }
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })
    }

    private fun countDown() {
        listener.onClose(minute,second)
        dismiss()
    }

    interface OnCloseListener {
        fun onClose(minute : Int, second : Int)
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
}