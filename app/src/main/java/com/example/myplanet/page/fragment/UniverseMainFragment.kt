package com.example.myplanet.page.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myplanet.R
import com.example.myplanet.base.BaseFragment

/**
 * @ClassName UniverseMainFragment
 * @author Silence~
 * @date 2022/5/3
 * @Description universe界面中的一个界面(套娃呢awa)
 */
class UniverseMainFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_universe_universe, container, false)
    }
}