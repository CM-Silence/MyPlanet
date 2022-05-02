package com.example.myplanet.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.example.myplanet.R

/**
 * @ClassName MyPlanetShow
 * @author Silence~
 * @date 2022/5/2
 * @Description 绑定星球图片和名字
 */
class MyPlanetShow(context: Context, attributeSet: AttributeSet) : LinearLayout(context,attributeSet){
    init {
        LayoutInflater.from(context).inflate(R.layout.view_myplanetshow, this)
    }
}