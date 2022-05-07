package com.example.myplanet.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import com.example.myplanet.R
import com.example.myplanet.utils.ToastUtil

/**
 * @ClassName MyProcessBar
 * @author Silence~
 * @date 2022/4/30
 * @Description 自定义ProcessBar
 */
class MyProcessBar(context: Context, attributeSet: AttributeSet) : RelativeLayout(context, attributeSet) {
    private lateinit var box1: MyBox
    private lateinit var box2: MyBox
    private lateinit var box3: MyBox
    private lateinit var box4: MyBox
    private var move : Float = 60f

    init {
        LayoutInflater.from(context).inflate(R.layout.view_myprocessbar, this)
        initView()
        initAnim()
    }

    private fun initView(){
        box1 = findViewById(R.id.box1)
        box2 = findViewById(R.id.box2)
        box3 = findViewById(R.id.box3)
        box4 = findViewById(R.id.box4)

        box2.setDirectionX(-1)
        box3.setDirectionY(-1)
        box4.setDirectionX(-1)
        box4.setDirectionY(-1)

    }

    private fun initAnim(){
        move(box1)
        move(box2)
        move(box3)
        move(box4)
    }

    private fun move(box: MyBox){
        box.animate().translationXBy(box.getDirectionX() * move).translationYBy(box.getDirectionY() * move).scaleX(1.5f).scaleY(1.5f).rotationBy(45f).setDuration(800).withEndAction {
            back(box)
        }
    }

    private fun back(box: MyBox){
        box.animate().translationXBy(-1 * box.getDirectionX() * move).translationYBy(-1 * box.getDirectionY() * move).scaleX(1f).scaleY(1f).rotationBy(45f).setDuration(1200).withEndAction {
            move(box)
        }
    }

}