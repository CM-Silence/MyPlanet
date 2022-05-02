package com.example.myplanet.page.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.myplanet.R
import com.example.myplanet.base.AppManager
import com.example.myplanet.base.BaseFragment
import com.example.myplanet.bean.UserBean
import com.example.myplanet.model.LoginModel
import com.example.myplanet.page.activity.LoginActivity
import com.example.myplanet.page.activity.MainActivity
import com.example.myplanet.page.dialog.CompleteInformationDialog
import com.example.myplanet.utils.ToastUtil
import com.google.android.material.imageview.ShapeableImageView

/**
 * @ClassName MineFragment
 * @author Silence~
 * @date 2022/5/1
 * @Description
 */
class MineFragment(title : String = "") : BaseFragment(title) {
    private lateinit var mTvName: TextView
    private lateinit var mTvSignature: TextView
    private lateinit var mBtnExperience: Button
    private lateinit var mBtnAchievement: Button
    private lateinit var mBtnSuggest: Button
    private lateinit var mBtnSetting: Button
    private lateinit var mBtnLogout: Button
    private lateinit var mIvHead: ShapeableImageView

    private lateinit var userBean: UserBean

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_mine, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
        initClick()
        initData()
    }

    private fun initView(view : View){
        mTvName = view.findViewById(R.id.fragment_tv_mine_name)
        mTvSignature = view.findViewById(R.id.fragment_tv_mine_signature)
        mBtnExperience = view.findViewById(R.id.fragment_btn_mine_experience)
        mBtnAchievement = view.findViewById(R.id.fragment_btn_mine_achievement)
        mBtnSuggest = view.findViewById(R.id.fragment_btn_mine_suggest)
        mBtnSetting = view.findViewById(R.id.fragment_btn_mine_setting)
        mBtnLogout = view.findViewById(R.id.fragment_btn_mine_logout)
        mIvHead = view.findViewById(R.id.fragment_shapeableimageview_mine_head)
    }

    private fun initData(){
        if(activity != null) {
            val activity = activity as MainActivity
            userBean = activity.getUserBean()
            upDateTextView()
        }
    }

    /**
     * @Description 用于更新TextView上显示的用户信息
     * @author Silence~
     * @date 2022/5/2 20:09
     */
    private fun upDateTextView(){
        mTvName.text = userBean.getName()
        mTvSignature.text = userBean.getSignature()
    }

    private fun initClick(){
        mBtnLogout.setOnClickListener {
            AlertDialog.Builder(this.requireContext()).apply {
                setTitle("注意")
                setMessage("是否要退出登录?")
                setPositiveButton("是"){
                        _, _ ->
                    run {
                        AppManager.finishAll()
                        LoginActivity.startActivity(this.context,true)
                    }
                }
                setNegativeButton("否"){
                        _, _ -> //点击"否"的话肯定什么事也没呀awa
                }
                show()
            }
        }
        mIvHead.setOnClickListener {
            CompleteInformationDialog(this.requireContext(),userBean.getName(),userBean.getSignature(),
                object : CompleteInformationDialog.OnCloseListener{
                    override fun onClose(name: String, signature: String) {
                        userBean.setName(name)
                        userBean.setSignature(signature)
                        LoginModel.addNonPasswordUserBean(userBean)
                        upDateTextView()
                        if(userBean.isRemember()){
                            LoginModel.setNonPasswordRememberUser(userBean)
                        }
                    }
                }).show()
        }
        mBtnExperience.setOnClickListener {
            ToastUtil.show("还在爆肝哦,再等一阵子就能使用啦qwq")
        }
        mBtnAchievement.setOnClickListener {
            ToastUtil.show("还在爆肝哦,再等一阵子就能使用啦qwq")
        }
        mBtnSuggest.setOnClickListener {
            ToastUtil.show("还在爆肝哦,再等一阵子就能使用啦qwq")
        }
        mBtnSetting.setOnClickListener {
            ToastUtil.show("还在爆肝哦,再等一阵子就能使用啦qwq")
        }
    }
}