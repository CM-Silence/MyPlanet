package com.example.myplanet.view

import android.content.Context
import android.util.AttributeSet

/**
 * @ClassName MyBox
 * @author Silence~
 * @date 2022/4/28
 * @Description 自定义方框,组成自定义ProcessBar的一部分
 */
class MyBox(context: Context, attributeSet: AttributeSet) : androidx.appcompat.widget.AppCompatImageView(context, attributeSet) {
    private var directionX = 1
    private var directionY = 1

    fun getDirectionX() = directionX
    fun getDirectionY() = directionY

    fun setDirectionX(x : Int){
        directionX = x
    }
    fun setDirectionY(y : Int){
        directionY = y
    }
}