package com.example.myplanet.page.dialog

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.Nullable
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.example.myplanet.R
import com.example.myplanet.bean.PlanetBean

/**
 * @ClassName PlanetInformationDialogFragment
 * @author Silence~
 * @date 2022/5/3
 * @Description
 */
open class PlanetInformationDialogFragment(private var mActivity: FragmentActivity,
                                      private val planetBean: PlanetBean) : DialogFragment(){

    private lateinit var mIvPlanet : ImageView
    private lateinit var mTvName : TextView
    private lateinit var mTvPreviewTime : TextView
    private lateinit var mTvTime : TextView
    private lateinit var mTvRemarks : TextView


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
        return dialog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
    }

    @SuppressLint("SetTextI18n")
    private fun initView(view: View){
        mIvPlanet = view.findViewById(R.id.dialog_iv_planetinformation_planet)
        mTvName = view.findViewById(R.id.dialog_tv_planetinformation_name)
        mTvPreviewTime = view.findViewById(R.id.dialog_tv_planetinformation_previewtime)
        mTvTime = view.findViewById(R.id.dialog_tv_planetinformation_time)
        mTvRemarks = view.findViewById(R.id.dialog_tv_planetinformation_remarks)

        mIvPlanet.setImageResource(planetBean.getImageID())
        mTvName.text = "名称:${planetBean.getName()}"
        mTvPreviewTime.text = "点亮日期:${planetBean.getPreviewTime()}"
        mTvTime.text = "时长:${planetBean.getTime()}"
        mTvRemarks.text = "备注:${planetBean.getRemarks()}"
    }

}