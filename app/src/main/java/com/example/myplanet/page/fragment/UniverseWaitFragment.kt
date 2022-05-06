package com.example.myplanet.page.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.myplanet.R
import com.example.myplanet.base.BaseFragment
import com.example.myplanet.bean.PlanetBean
import com.example.myplanet.page.adapter.ChoosePlanetRvAdapter
import com.example.myplanet.page.adapter.UniverseRvAdapter
import com.example.myplanet.page.dialog.ChoosePlanetDialogFragment
import com.example.myplanet.page.dialog.PlanetInformationDialogFragment

/**
 * @ClassName UniverseWaitFragment
 * @author Silence~
 * @date 2022/5/3
 * @Description universe界面中的一个界面(套娃呢awa)
 */
class UniverseWaitFragment(private val planetList : ArrayList<PlanetBean>) : BaseFragment() {
    private lateinit var mRvPlanet : RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_universe_wait, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
        initRv()
    }

    private fun initView(view : View){
        mRvPlanet = view.findViewById(R.id.fragment_rv_universe_wait)
    }

    private fun initRv(){
        val layoutManager = GridLayoutManager(requireContext(),2)
        mRvPlanet.layoutManager = layoutManager
        val adapter = UniverseRvAdapter(planetList,object : UniverseRvAdapter.OnClickItemListener{
            @SuppressLint("SetTextI18n")
            override fun onClickItem(planet: PlanetBean) {
                PlanetInformationDialogFragment(requireActivity(),planet).show()
            }
        })
        mRvPlanet.adapter = adapter
    }
}