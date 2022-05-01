package com.example.myplanet.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View

/**
 * @ClassName MyCircle
 * @author Silence~
 * @date 2022/5/1
 * @Description 自定义圆环View,组成计时器的一部分
 */
class MyCircle(context: Context, attributeSet: AttributeSet) : View(context, attributeSet){
    private var paint = Paint()
    private var directionX = 1
    private var directionY = 1
    private val rect1 = RectF(70f, 70f, 800f, 800f)
    init {
        paint.run {
            color = Color.rgb(255, 193, 7)
            style = Paint.Style.STROKE
            strokeWidth = 25f
        }
    }

    /**
     * @Description 画一个圆环
     * @date 2022/5/1 17:18
     */
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawArc(rect1,360f,360f,false,paint)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(measureWidth(widthMeasureSpec),measureHeight(heightMeasureSpec))
    }

    private fun measureWidth(widthMeasureSpec: Int): Int {
        var result : Int
        val specMode = View.MeasureSpec.getMode(widthMeasureSpec)
        val specSize = View.MeasureSpec.getSize(widthMeasureSpec)
        if (specMode == View.MeasureSpec.EXACTLY) {
            result = specSize
        } else {
            //这样，当时用wrap_content时，View就得到一个默认值90px，而不是填充整个父布局。
            result = 100
            if (specMode == View.MeasureSpec.AT_MOST) {
                result = result.coerceAtMost(specSize)
            }
        }
        return result
    }

    private fun measureHeight(widthMeasureSpec: Int): Int {
        var result : Int
        val specMode = View.MeasureSpec.getMode(widthMeasureSpec)
        val specSize = View.MeasureSpec.getSize(widthMeasureSpec)
        if (specMode == View.MeasureSpec.EXACTLY) {
            result = specSize
        } else {
            //这样，当时用wrap_content时，View就得到一个默认值90px，而不是填充整个父布局。
            result = 100
            if (specMode == View.MeasureSpec.AT_MOST) {
                result = result.coerceAtMost(specSize)
            }
        }
        return result
    }

}