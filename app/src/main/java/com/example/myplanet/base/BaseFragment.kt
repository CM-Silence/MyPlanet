package com.example.myplanet.base

import androidx.fragment.app.Fragment

/**
 * @ClassName BaseFragment
 * @author Silence~
 * @date 2022/5/1
 * @Description 主要的用处是添加一些需要的属性以及方便管理
 */
open class BaseFragment(private var title: String = "") : Fragment(){

    fun setTitle(title : String){
        this.title = title
    }

    fun getTitle() = title
}