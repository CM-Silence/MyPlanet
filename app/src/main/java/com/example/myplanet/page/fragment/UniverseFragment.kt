package com.example.myplanet.page.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myplanet.R
import com.example.myplanet.base.BaseFragment

/**
 * @ClassName UniverseFragment
 * @author Silence~
 * @date 2022/5/1
 * @Description
 */
class UniverseFragment(title : String = "") : BaseFragment(title) {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_universe, container, false)
    }
}