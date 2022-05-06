package com.example.myplanet.page.dialog

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.*
import android.widget.Button
import androidx.annotation.Nullable
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myplanet.R
import com.example.myplanet.bean.PlanetBean
import com.example.myplanet.page.adapter.AddPlanetRvAdapter
import com.example.myplanet.utils.ToastUtil
import com.google.android.material.textfield.TextInputEditText
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

/**
 * @ClassName AddPlanetDialogFragment
 * @author Silence~
 * @date 2022/5/6
 * @Description 用于添加星球的DialogFragment
 */
open class AddPlanetDialogFragment(private val planet: PlanetBean? = null,
                                      private var mActivity: FragmentActivity,
                                      private val listener: OnCloseListener) : DialogFragment() {

    private lateinit var mRvPlanet : RecyclerView
    private lateinit var mTieName : TextInputEditText
    private lateinit var mBtnPreview : Button
    private lateinit var mTieRemark : TextInputEditText
    private lateinit var mBtnAdd : Button

    private val srcList = ArrayList<Int>()

    private lateinit var mPlanetBean : PlanetBean

    private var planetPreViewTime : String = ""
    private var planetSrc : Int? = null
    private var isChooseSrc = false

    init {
        //获取当前时间
        val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        planetPreViewTime = format.format(Date())
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
                if (!this@AddPlanetDialogFragment.isAdded) {
                    super@AddPlanetDialogFragment.show(fm, javaClass.simpleName)
                }
            }
        }
    }

    override fun dismiss() {
        mActivity.runOnUiThread {
            if (isActivityAlive()) {
                super@AddPlanetDialogFragment.dismissAllowingStateLoss()
            }
        }
    }

    /**
     * @Description
     * @return 返回true说明所在活动还在,反之则说明弹窗所在活动已挂
     * @date 2022/5/2 14:32
     */
    private fun isActivityAlive(): Boolean {
        return !mActivity.isFinishing && !mActivity.isDestroyed
    }

    @Nullable
    override fun onCreateView(
        inflater: LayoutInflater,
        @Nullable container: ViewGroup?,
        @Nullable savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialogfragment_addplanet, container, false)
    }

    override fun onCreateDialog(@Nullable savedInstanceState: Bundle?): Dialog {
        val dialog: Dialog = super.onCreateDialog(savedInstanceState)
        val window: Window? = dialog.window
        window?.setGravity(Gravity.BOTTOM) //设置从底部弹出
        return dialog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        initView(view)
        initRv()
        initClick()
    }

    /**
     * @Description 添加星球图片数据
     * @date 2022/5/6 21:46
     */
    private fun initData(){
        srcList.run{
            add(R.drawable.drawable_earth)
            add(R.drawable.drawable_mars)
            add(R.drawable.drawable_neptune)
            add(R.drawable.drawable_saturn)
            add(R.drawable.drawable_uranus)
            add(R.drawable.drawable_venus)
        }
    }

    private fun initView(view: View) {
        mRvPlanet = view.findViewById(R.id.dialog_rv_addplanet_planet)
        mTieName = view.findViewById(R.id.dialog_tie_addplanet_name)
        mBtnPreview = view.findViewById(R.id.dialog_btn_addplanet_date)
        mTieRemark = view.findViewById(R.id.dialog_tie_addplanet_remarks)
        mBtnAdd = view.findViewById(R.id.dialog_btn_addplanet_add)

        if(planet != null){
            mTieName.setText(planet.getName())
            mBtnPreview.text = planet.getPreviewTime()
            mTieRemark.setText(planet.getRemarks())
        }
    }

    private fun initRv() {

        val layoutManager = LinearLayoutManager(this.requireContext())
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        mRvPlanet.layoutManager = layoutManager
        val adapter =
            AddPlanetRvAdapter(srcList, object : AddPlanetRvAdapter.OnClickItemListener {
                @SuppressLint("SetTextI18n")
                override fun onClickItem(src : Int) {
                    planetSrc = src
                    isChooseSrc = true
                }
            })
        mRvPlanet.adapter = adapter
    }

    private fun initClick() {
        mBtnPreview.setOnClickListener {
            //选择日期
        }
        mBtnAdd.setOnClickListener {
            AlertDialog.Builder(this.requireContext()).apply {
                setTitle("提醒")
                setMessage("是否要创建/更改星球?")
                setNegativeButton("否"){
                        _, _ -> //点击"否"的话肯定什么事也没呀awa
                }
                setPositiveButton("是"){
                        _, _ ->
                    run {
                        addPlanet()
                    }
                }
                show()
            }
        }
    }

    /**
     * @Description 新建/修改星球的方法
     * @date 2022/5/6 20:49
     */
    private fun addPlanet(){
        if(isCompete()) {
            if (planet == null) {
                listener.onAddPlanet(mPlanetBean)
            } else {
                listener.onChangePlanet(
                    mPlanetBean.getName(),
                    mPlanetBean.getPreviewTime(),
                    mPlanetBean.getRemarks()
                )
            }
        }
        else{
            ToastUtil.show("星球信息未完善!")
        }
    }

    private fun isCompete() : Boolean{
        if(!mTieName.text.isNullOrEmpty() && !mTieRemark.text.isNullOrEmpty() && isChooseSrc){
            mPlanetBean = PlanetBean(mTieName.text.toString(),planetPreViewTime,0,planetSrc!!)
            return true
        }
        return false
    }

    interface OnCloseListener {
        fun onAddPlanet(planet: PlanetBean)
        fun onChangePlanet(name : String,preViewTime : String,remarks : String)
    }
}

