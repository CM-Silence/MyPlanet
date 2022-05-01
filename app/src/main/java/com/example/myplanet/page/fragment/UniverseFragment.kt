package com.example.myplanet.page.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myplanet.R

/**
 * @ClassName UniverseFragment
 * @author Silence~
 * @date 2022/5/1
 * @Description
 */
class UniverseFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_universe, container, false)
    }
}