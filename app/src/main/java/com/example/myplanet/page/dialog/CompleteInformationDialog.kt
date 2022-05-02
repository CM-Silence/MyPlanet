package com.example.myplanet.page.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Window
import androidx.annotation.NonNull
import com.example.myplanet.R

/**
 * @ClassName CompleteInformationDialog
 * @author Silence~
 * @date 2022/5/2
 * @Description 用于完善用户信息的窗口
 */
class CompleteInformationDialog(@NonNull context: Context, username: String, signature: String,private val listener: OnCloseListener) : Dialog(context){


    init {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //去掉系统的黑色矩形边框
        window!!.setBackgroundDrawableResource(android.R.color.transparent)
        requestWindowFeature(Window.FEATURE_NO_TITLE) // 加上这句后就可以在 xml 中自定义高和宽

        setContentView(R.layout.dialog_completeinformation)

    }

    interface OnCloseListener{
        fun onClose()
    }
}