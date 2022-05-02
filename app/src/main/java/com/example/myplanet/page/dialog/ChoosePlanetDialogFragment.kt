package com.example.myplanet.page.dialog

import android.annotation.SuppressLint
import android.app.Dialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

import android.os.Bundle

import android.view.*
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myplanet.R
import com.example.myplanet.base.MyApplication
import com.example.myplanet.bean.PlanetBean
import com.example.myplanet.page.adapter.ChoosePlanetRvAdapter


/**
 * @ClassName ChoosePlanetDialogFragment
 * @author Silence~
 * @date 2022/5/2
 * @Description 切换星球的下方弹窗
 */
open class ChoosePlanetDialogFragment(private var mActivity: FragmentActivity, private val listener: OnCloseListener) : DialogFragment(){

    private lateinit var mRvPlanet : RecyclerView
    private lateinit var mTvName : TextView
    private lateinit var mTvPreview : TextView
    private lateinit var mTvTime : TextView
    private lateinit var mBtnChange : Button

    private val planetList = ArrayList<PlanetBean>()

    private var mPlanetBean : PlanetBean? = null

    fun setActivity(mActivity: FragmentActivity) {
        this.mActivity = mActivity
    }

    override fun getTheme(): Int {
        return R.style.style_dialogfragment
    }

    /**
     * @Description 弹出窗口的方法
     * @date 2022/5/2 14:31
     */
    fun show() {
        mActivity.runOnUiThread {
            if (isActivityAlive()) {
                val fm: FragmentManager = mActivity.supportFragmentManager
                val prev: Fragment? = fm.findFragmentByTag(javaClass.simpleName)
                if (prev != null) fm.beginTransaction().remove(prev)
                if (!this@ChoosePlanetDialogFragment.isAdded) {
                    super@ChoosePlanetDialogFragment.show(fm, javaClass.simpleName)
                }
            }
        }
    }

    override fun dismiss() {
        mActivity.runOnUiThread {
            if (isActivityAlive()) {
                super@ChoosePlanetDialogFragment.dismissAllowingStateLoss()
            }
        }
    }

    /**
     * @Description
     * @return 返回true说明所在活动还在,反之则说明弹窗所在活动已挂
     * @date 2022/5/2 14:32
     */
    private fun isActivityAlive() : Boolean{
        return !mActivity.isFinishing && !mActivity.isDestroyed
    }

    @Nullable
    override fun onCreateView(
        inflater: LayoutInflater,
        @Nullable container: ViewGroup?,
        @Nullable savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialogfragment_chooseplanet, container, false)
    }

    override fun onCreateDialog(@Nullable savedInstanceState: Bundle?): Dialog {
        val dialog: Dialog = super.onCreateDialog(savedInstanceState)
        val window: Window? = dialog.window
        window?.setGravity(Gravity.BOTTOM) //设置从底部弹出
        return dialog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
        initRv()
        initClick()
    }

    private fun initView(view: View){
        mRvPlanet = view.findViewById(R.id.dialog_rv_changeplanet_planet)
        mTvName = view.findViewById(R.id.dialog_tv_changeplanet_name)
        mTvPreview = view.findViewById(R.id.dialog_tv_changeplanet_previewtime)
        mTvTime = view.findViewById(R.id.dialog_tv_changeplanet_time)
        mBtnChange = view.findViewById(R.id.dialog_btn_changeplanet_change)
    }

    private fun initRv(){
        planetList.add(PlanetBean("earth",0,0,R.drawable.drawable_earth))
        planetList.add(PlanetBean("mars",0,0,R.drawable.drawable_mars))
        planetList.add(PlanetBean("neptune",0,0,R.drawable.drawable_neptune))
        planetList.add(PlanetBean("saturn",0,0,R.drawable.drawable_saturn))
        planetList.add(PlanetBean("uranus",0,0,R.drawable.drawable_uranus))
        planetList.add(PlanetBean("venus",0,0,R.drawable.drawable_venus))

        val layoutManager = LinearLayoutManager(this.requireContext())
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        mRvPlanet.layoutManager = layoutManager
        val adapter = ChoosePlanetRvAdapter(planetList,object : ChoosePlanetRvAdapter.OnClickItemListener{
            @SuppressLint("SetTextI18n")
            override fun onClickItem(planet: PlanetBean) {
                mTvName.text = "名称:${planet.getName()}"
                mTvPreview.text = "预计点亮时长:${planet.getPreviewTime()}"
                mTvTime.text = "已专注:${planet.getTime()}min"
                mPlanetBean = planet
            }
        })
        mRvPlanet.adapter = adapter
    }

    private fun initClick(){
        mBtnChange.setOnClickListener {
            if(mPlanetBean != null){
                Toast.makeText(MyApplication.getAppContext(), "选择成功!", Toast.LENGTH_SHORT).show()
                listener.onClose(mPlanetBean!!)
                dismiss()
            }
            else{
                Toast.makeText(MyApplication.getAppContext(), "还没有选择星球哦!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    interface OnCloseListener {
        fun onClose(planet : PlanetBean)
    }

}