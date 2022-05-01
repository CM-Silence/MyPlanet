package com.example.myplanet.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import android.view.View.MeasureSpec

/**
 * @ClassName GreenBox
 * @author Silence~
 * @date 2022/4/28
 * @Description 自定义方框,组成自定义ProcessBar的一部分
 */
class MyBox(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {
    private var paint = Paint()
    private var directionX = 1
    private var directionY = 1
    private val rect1 = RectF(15f, 15f, 85f, 85f)
    init {
        paint.run {
            color = Color.rgb(255, 193, 7)
            style = Paint.Style.STROKE
            strokeWidth = 10f
        }
    }

    /**
     * @Description 重写onDraw方法,画一个淡绿色方框
     * @date 2022/4/30 22:17
     */
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawRect(rect1,paint)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(measureWidth(widthMeasureSpec),measureHeight(heightMeasureSpec))
    }

    private fun measureWidth(widthMeasureSpec: Int): Int {
        var result : Int
        val specMode = MeasureSpec.getMode(widthMeasureSpec)
        val specSize = MeasureSpec.getSize(widthMeasureSpec)
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize
        } else {
            //这样，当时用wrap_content时，View就得到一个默认值90px，而不是填充整个父布局。
            result = 100
            if (specMode == MeasureSpec.AT_MOST) {
                result = result.coerceAtMost(specSize)
            }
        }
        return result
    }

    private fun measureHeight(widthMeasureSpec: Int): Int {
        var result : Int
        val specMode = MeasureSpec.getMode(widthMeasureSpec)
        val specSize = MeasureSpec.getSize(widthMeasureSpec)
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize
        } else {
            //这样，当时用wrap_content时，View就得到一个默认值90px，而不是填充整个父布局。
            result = 100
            if (specMode == MeasureSpec.AT_MOST) {
                result = result.coerceAtMost(specSize)
            }
        }
        return result
    }

    fun getDirectionX() = directionX
    fun getDirectionY() = directionY

    fun setDirectionX(x : Int){
        directionX = x
    }
    fun setDirectionY(y : Int){
        directionY = y
    }
}