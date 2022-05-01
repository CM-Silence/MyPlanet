package com.example.myplanet.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import com.example.myplanet.R

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

    init {
        LayoutInflater.from(context).inflate(R.layout.view_myprocessbar, this)
        initView()
        initAnim()
    }

    companion object{
        private const val MOVE = 50f
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
        val x = (box.left + box.right) / 2f
        val y = (box.top + box.bottom) / 2f
        box.animate().translationXBy(box.getDirectionX() * MOVE).translationYBy(box.getDirectionY() * MOVE).scaleX(1.5f).scaleY(1.5f).rotationBy(45f).setDuration(800).withEndAction {
            back(box)
        }
    }

    private fun back(box: MyBox){
        box.animate().translationXBy(-1 * box.getDirectionX() * MOVE).translationYBy(-1 * box.getDirectionY() * MOVE).scaleX(1f).scaleY(1f).rotationBy(45f).setDuration(1200).withEndAction {
            move(box)
        }
    }

}