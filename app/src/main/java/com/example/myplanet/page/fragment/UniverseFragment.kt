package com.example.myplanet.page.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import androidx.fragment.app.FragmentManager
import com.example.myplanet.R
import com.example.myplanet.base.BaseFragment
import com.example.myplanet.bean.UserBean
import com.example.myplanet.page.activity.MainActivity

/**
 * @ClassName UniverseFragment
 * @author Silence~
 * @date 2022/5/1
 * @Description
 */
class UniverseFragment(title : String = "") : BaseFragment(title) {
    private lateinit var mBtnWait : Button
    private lateinit var mBtnUniverse : Button
    private lateinit var mFrameLayout: FrameLayout

    private var mMainFragment: UniverseMainFragment? = null
    private var mWaitFragment: UniverseWaitFragment? = null

    private var mFragmentManager: FragmentManager? = null

    private var currentFragment: BaseFragment? = null //用于记录当前显示的界面

    private lateinit var userBean: UserBean

    /**
     * @Description 刷新数据
     * @date 2022/5/7 23:19
     */
    fun upDateData(){
        mWaitFragment?.upDateData()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_universe, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        initView(view)
        initFragment()
        initClick()
    }

    private fun initData(){
        if(activity != null) {
            val activity = activity as MainActivity
            userBean = activity.getUserBean()
        }
    }

    private fun initView(view: View){
        mBtnWait = view.findViewById(R.id.fragment_btn_universe_wait)
        mBtnUniverse = view.findViewById(R.id.fragment_btn_universe_universe)
        mFrameLayout = view.findViewById(R.id.fragment_framelayout_universe)

        mBtnUniverse.setBackgroundResource(R.drawable.general_background_press)
    }

    private fun initFragment(){
        if(mMainFragment == null){
            mMainFragment = UniverseMainFragment()
        }
        if(mWaitFragment == null){
            mWaitFragment = UniverseWaitFragment(userBean,object : UniverseWaitFragment.OnChangePlanetListListener{
                override fun onPlanetListChange() {
                    if(activity != null) {
                        val activity = activity as MainActivity
                        activity.upDateData(1)
                    }
                }
            })
        }
        changeFragment(mMainFragment)
    }

    private fun initClick(){
        mBtnWait.setOnClickListener {
            changeFragment(mWaitFragment)
            mBtnWait.setBackgroundResource(R.drawable.general_background_press)
            mBtnUniverse.setBackgroundResource(R.drawable.general_background)
        }
        mBtnUniverse.setOnClickListener {
            changeFragment(mMainFragment)
            mBtnWait.setBackgroundResource(R.drawable.general_background)
            mBtnUniverse.setBackgroundResource(R.drawable.general_background_press)
        }
    }

    /**
     * @Description 切换fragment的方法
     * @Param fragment 需要切换的碎片
     * @date 2022/5/3 9:56
     */
    private fun changeFragment(fragment : BaseFragment?){
        if(mMainFragment == null && fragment is UniverseMainFragment){
            mMainFragment = UniverseMainFragment()
        }
        if(mWaitFragment == null && fragment is UniverseWaitFragment){
            mWaitFragment = UniverseWaitFragment(userBean,object : UniverseWaitFragment.OnChangePlanetListListener{
                override fun onPlanetListChange() {
                    if(activity != null) {
                        val activity = activity as MainActivity
                        activity.upDateData(1)
                    }
                }
            })
        }
        if(fragment != currentFragment) {
            if (mFragmentManager == null) {
                mFragmentManager = this.childFragmentManager //不要用activity的
            }
            val transaction = mFragmentManager!!.beginTransaction()
            if (fragment!!.isAdded) {
                transaction.show(fragment)
            } else {
                transaction.add(R.id.fragment_framelayout_universe, fragment)
            }
            if (currentFragment != null) {
                transaction.hide(currentFragment!!)
            }
            currentFragment = fragment
            transaction.commit()
        }
    }
}