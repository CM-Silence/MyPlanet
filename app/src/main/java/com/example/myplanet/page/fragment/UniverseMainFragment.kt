package com.example.myplanet.page.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.myplanet.R
import com.example.myplanet.base.BaseFragment
import com.example.myplanet.utils.ToastUtil

/**
 * @ClassName UniverseMainFragment
 * @author Silence~
 * @date 2022/5/3
 * @Description universe界面中的一个界面(套娃呢awa)
 */
class UniverseMainFragment : BaseFragment() {
    private lateinit var mIvPlanet : ImageView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_universe_universe, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
        initClick()
    }

    private fun initView(view : View){
        mIvPlanet = view.findViewById(R.id.fragment_iv_universe_planet)
    }

    private fun initClick(){
        mIvPlanet.setOnClickListener {
            ToastUtil.show("还在爆肝哦,很快就能使用啦!")
        }
    }
}