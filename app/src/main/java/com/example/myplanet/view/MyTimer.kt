package com.example.myplanet.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.RelativeLayout
import com.example.myplanet.R

/**
 * @ClassName MyTimer
 * @author Silence~
 * @date 2022/5/1
 * @Description 组成计时器的圆环
 */
class MyTimer(context: Context, attributeSet: AttributeSet) : RelativeLayout(context, attributeSet){
    private lateinit var mIvMoon : ImageView

    init {
        LayoutInflater.from(context).inflate(R.layout.view_mytimer, this)
        mIvMoon = findViewById(R.id.moon)
    }

}