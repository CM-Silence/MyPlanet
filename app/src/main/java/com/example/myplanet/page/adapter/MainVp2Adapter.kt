package com.example.myplanet.page.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.myplanet.page.fragment.MineFragment
import com.example.myplanet.page.fragment.TimerFragment
import com.example.myplanet.page.fragment.UniverseFragment

/**
 * @ClassName MainVp2Adapter
 * @author Silence~
 * @date 2022/5/1
 * @Description 主活动上的ViewPage2的适配器
 */
class MainVp2Adapter(frgManager: FragmentManager,
                     private val frgList: MutableList<Fragment>,
                     lifecycle: Lifecycle) :
    FragmentStateAdapter(frgManager, lifecycle){


    /**
     * @Description 用于给ViewPage2添加Fragment的方法
     * @Param fragment 需要传入的Fragment
     * @date 2022/5/1 18:13
     */
    fun addFragment(fragment : Fragment){
        frgList.add(fragment)
    }

    override fun getItemCount(): Int {
        return frgList.size
    }

    override fun createFragment(position : Int) : Fragment {
        return frgList[position]
    }
}