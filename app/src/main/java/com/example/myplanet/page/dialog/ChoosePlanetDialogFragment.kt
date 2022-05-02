package com.example.myplanet.page.dialog

import android.app.Dialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

import android.os.Bundle

import android.view.*
import androidx.annotation.Nullable
import com.example.myplanet.R


/**
 * @ClassName ChoosePlanetDialogFragment
 * @author Silence~
 * @date 2022/5/2
 * @Description 切换星球的下方弹窗
 */
open class ChoosePlanetDialogFragment(private var mActivity: FragmentActivity) : DialogFragment(){

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


}