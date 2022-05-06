package com.example.myplanet.view

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import com.example.myplanet.R

/**
 * @ClassName MyTimer
 * @author Silence~
 * @date 2022/5/1
 * @Description 计时器
 */
class MyTimer(context: Context, attributeSet: AttributeSet) : RelativeLayout(context, attributeSet){
    private var process = 0f //倒计时进度(到0代表倒计时结束)

    @Volatile
    private var changeSecond = 0 //由于1/7200度太小,导致其转不动,所以等待数秒后再进行一次转动

    init {
        LayoutInflater.from(context).inflate(R.layout.view_mytimer, this)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
    }

    fun getProcess() = process
    fun setProcess(process : Float){
        this.process = process
    }

    /**
     * @Description 通过传入的分秒计算出倒计时的进度并据此来转动Timer
     * @Param minute 分
     * @param second 秒
     * @date 2022/5/2 12:41
     */
    @SuppressLint("SetTextI18n")
    fun setProcess(minute : Int, second : Int){
        process = (second + 60 * minute) / 7200f
        changeSecond++
        if (changeSecond >= 20) {
            ObjectAnimator.ofFloat(this, "rotation", this.rotation, this.rotation - 1f)
                .setDuration(19000).start()
            changeSecond = 0
        }
    }

    /**
     * @Description 开始计时的方法
     * @Param minute 分
     * @param second 秒
     * @date 2022/5/2 13:57
     */
    fun startCountDown(minute : Int, second : Int){
        process = (second + 60 * minute) / 7200f
        this.clearAnimation()
        ObjectAnimator.ofFloat(this, "rotation", this.rotation, process * 360)
            .setDuration(1000).start()
        changeSecond = 0
    }
}