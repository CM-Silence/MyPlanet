package com.example.myplanet.page.dialog

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.*
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.Nullable
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.example.myplanet.R
import com.example.myplanet.bean.PlanetBean
import com.example.myplanet.utils.ToastUtil

/**
 * @ClassName PlanetInformationDialogFragment
 * @author Silence~
 * @date 2022/5/3
 * @Description
 */
open class PlanetInformationDialogFragment(private var mActivity: FragmentActivity,
                                      private val planetBean: PlanetBean,
                                      private val planetListSize: Int,
                                      private val isChangeable : Boolean,
                                      private val listener: OnCloseListener) : DialogFragment(){

    private lateinit var mIvPlanet : ImageView
    private lateinit var mTvName : TextView
    private lateinit var mTvPreviewTime : TextView
    private lateinit var mTvTime : TextView
    private lateinit var mTvRemarks : TextView
    private lateinit var mBtnChange : Button
    private lateinit var mBtnDelete : Button

    override fun getTheme(): Int {
        return R.style.style_dialogfragment
    }

    /**
     * @Description 弹出窗口的方法
     * @date 2022/5/3 10:08
     */
    fun show() {
        mActivity.runOnUiThread {
            if (isActivityAlive()) {
                val fm: FragmentManager = mActivity.supportFragmentManager
                val prev: Fragment? = fm.findFragmentByTag(javaClass.simpleName)
                if (prev != null) fm.beginTransaction().remove(prev)
                if (!this@PlanetInformationDialogFragment.isAdded) {
                    super@PlanetInformationDialogFragment.show(fm, javaClass.simpleName)
                }
            }
        }
    }

    override fun dismiss() {
        mActivity.runOnUiThread {
            if (isActivityAlive()) {
                super@PlanetInformationDialogFragment.dismissAllowingStateLoss()
            }
        }
    }

    /**
     * @Description
     * @return 返回true说明所在活动还在,反之则说明弹窗所在活动已挂
     * @date 2022/5/3 10:09
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
        return inflater.inflate(R.layout.dialogfragment_planetinformation, container, false)
    }

    override fun onCreateDialog(@Nullable savedInstanceState: Bundle?): Dialog {
        val dialog: Dialog = super.onCreateDialog(savedInstanceState)
        val window: Window? = dialog.window
        window?.setGravity(Gravity.BOTTOM) //设置从底部弹出
        window?.attributes?.windowAnimations = R.style.dialog_anim_style //设置动画
        return dialog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
        initClick()
    }


    private fun initView(view: View){
        mIvPlanet = view.findViewById(R.id.dialog_iv_planetinformation_planet)
        mTvName = view.findViewById(R.id.dialog_tv_planetinformation_name)
        mTvPreviewTime = view.findViewById(R.id.dialog_tv_planetinformation_previewtime)
        mTvTime = view.findViewById(R.id.dialog_tv_planetinformation_time)
        mTvRemarks = view.findViewById(R.id.dialog_tv_planetinformation_remarks)
        mBtnChange = view.findViewById(R.id.dialog_btn_planetinformation_change)
        mBtnDelete = view.findViewById(R.id.dialog_btn_planetinformation_delete)
        upDateView()

        if(!isChangeable){
            mBtnDelete.setBackgroundResource(R.drawable.general_background_full)
            mBtnChange.setBackgroundResource(R.drawable.general_background_full)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun upDateView(){
        mIvPlanet.setImageResource(planetBean.getImageID())
        mTvName.text = "名称:${planetBean.getName()}"
        mTvPreviewTime.text = "点亮日期:${planetBean.getPreviewTime()}"
        mTvTime.text = "时长:${planetBean.getTimeString()}"
        mTvRemarks.text = "备注:${planetBean.getRemarks()}"
        mTvRemarks.movementMethod = ScrollingMovementMethod.getInstance()
    }

    private fun initClick(){
        mBtnChange.setOnClickListener {
            if(isChangeable) {
                AddPlanetDialogFragment(
                    planetBean,
                    requireActivity(),
                    object : AddPlanetDialogFragment.OnCloseListener {
                        @SuppressLint("NotifyDataSetChanged")
                        override fun onAddPlanet(planet: PlanetBean) {
                            //这里不需要
                        }

                        override fun onChangePlanet(
                            name: String,
                            preViewTime: String,
                            remarks: String,
                            src: Int
                        ) {
                            planetBean.run {
                                setName(name)
                                setPreviewTime(preViewTime)
                                setRemarks(remarks)
                                setImageID(src)
                            }
                            upDateView()
                            listener.onChange(planetBean)
                            ToastUtil.show("星球信息已更改!")
                        }

                    }).show()
            }
            else{
                ToastUtil.show("你正在专注于这颗星球,无法对其进行更改!")
            }
        }

        mBtnDelete.setOnClickListener {
            if(isChangeable && planetListSize > 1) {
                AlertDialog.Builder(this.requireContext()).apply {
                    setTitle("注意")
                    setMessage("是否要永久删除此星球?")
                    setNegativeButton("否") { _, _ -> //点击"否"的话肯定什么事也没呀awa
                    }
                    setPositiveButton("是") { _, _ ->
                        run {
                            listener.onDelete(planetBean)
                            ToastUtil.show("已删除此星球!")
                            dismiss()
                        }
                    }
                    show()
                }
            }
            else{
                if(planetListSize > 1) {
                    ToastUtil.show("你正在专注于这颗星球,无法对其进行更改!")
                }
                else{
                    ToastUtil.show("你只有这一颗星球啦,无法再删除了哦!")
                }
            }
        }
    }

    interface OnCloseListener{
        fun onChange(planet: PlanetBean)
        fun onDelete(planet: PlanetBean)
    }
}