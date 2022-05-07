package com.example.myplanet.page.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myplanet.R
import com.example.myplanet.base.BaseFragment
import com.example.myplanet.bean.PlanetBean
import com.example.myplanet.bean.UserBean
import com.example.myplanet.page.activity.MainActivity
import com.example.myplanet.page.adapter.UniverseRvAdapter
import com.example.myplanet.page.dialog.AddPlanetDialogFragment
import com.example.myplanet.page.dialog.PlanetInformationDialogFragment

/**
 * @ClassName UniverseWaitFragment
 * @author Silence~
 * @date 2022/5/3
 * @Description universe界面中的一个界面(套娃呢awa)
 */
class UniverseWaitFragment(private val userBean: UserBean) : BaseFragment() {
    private lateinit var mRvPlanet : RecyclerView

    private lateinit var adapter : UniverseRvAdapter
    private lateinit var planetList : ArrayList<PlanetBean>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_universe_wait, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        initView(view)
        initRv()
    }

    private fun initData(){
        planetList = userBean.getPlanetList()
    }
    private fun initView(view : View){
        mRvPlanet = view.findViewById(R.id.fragment_rv_universe_wait)
    }

    private fun initRv(){
        val layoutManager = GridLayoutManager(requireContext(),2)
        mRvPlanet.layoutManager = layoutManager
        adapter = UniverseRvAdapter(planetList,object : UniverseRvAdapter.OnClickItemListener{
            @SuppressLint("SetTextI18n")
            override fun onClickItem(planet: PlanetBean) {
                PlanetInformationDialogFragment(requireActivity(),planet,!(planetList.indexOf(planet) == 0 && MainActivity.isCountDown),object : PlanetInformationDialogFragment.OnCloseListener{
                    override fun onChange(planet : PlanetBean) {
                        val position = planetList.indexOf(planet)
                        adapter.notifyItemChanged(position)
                    }

                    override fun onDelete(planet: PlanetBean) {
                        val position = planetList.indexOf(planet)
                        planetList.remove(planet)
                        adapter.notifyItemRemoved(position)
                    }
                }
                ).show()
            }

            override fun onAddButtonClick() {
                AddPlanetDialogFragment(planet = null,requireActivity(),object : AddPlanetDialogFragment.OnCloseListener{
                    @SuppressLint("NotifyDataSetChanged")
                    override fun onAddPlanet(planet: PlanetBean) {
                        planetList.add(planet)
                        adapter.notifyItemInserted(planetList.size)
                    }
                    override fun onChangePlanet(
                        name: String,
                        preViewTime: String,
                        remarks: String,
                        src : Int
                    ) {
                        //这里不需要
                    }

                }).show()
            }
        })
        mRvPlanet.adapter = adapter
    }
}