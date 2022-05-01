package com.example.myplanet.view

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import com.example.myplanet.R
import java.util.*

/**
 * @ClassName MyTimer
 * @author Silence~
 * @date 2022/5/1
 * @Description 计时器
 */
class MyTimer(context: Context, attributeSet: AttributeSet) : RelativeLayout(context, attributeSet){
    private lateinit var mIvMoon : ImageView
    private var second = 0 //剩余秒数

    init {
        LayoutInflater.from(context).inflate(R.layout.view_mytimer, this)
        mIvMoon = findViewById(R.id.view_iv_moon)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
    }

    fun getSecond() = second
    fun setSecond(second : Int){
        this.second = second
    }

}