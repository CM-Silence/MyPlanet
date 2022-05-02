package com.example.myplanet.utils

import android.widget.Toast
import com.example.myplanet.base.MyApplication

/**
 * @ClassName ToastUtil
 * @author Silence~
 * @date 2022/5/2
 * @Description 我原来不想写的,但是复制粘贴搞得我好累...
 */
object ToastUtil {
    fun show(message : String,isLong : Boolean = false){
        if (!isLong) {
            Toast.makeText(MyApplication.getAppContext(), message, Toast.LENGTH_SHORT).show()
        }
        else{
            Toast.makeText(MyApplication.getAppContext(), message, Toast.LENGTH_LONG).show()
        }
    }
}