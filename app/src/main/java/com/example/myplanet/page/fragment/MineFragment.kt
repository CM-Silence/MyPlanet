package com.example.myplanet.page.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myplanet.R
import com.example.myplanet.base.BaseFragment

/**
 * @ClassName MineFragment
 * @author Silence~
 * @date 2022/5/1
 * @Description
 */
class MineFragment(title : String = "") : BaseFragment(title) {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_mine, container, false)
    }
}