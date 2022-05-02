package com.example.myplanet.page.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Window
import androidx.annotation.NonNull
import androidx.core.widget.addTextChangedListener
import com.example.myplanet.R
import com.example.myplanet.bean.UserBean
import com.example.myplanet.model.LoginModel
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

/**
 * @ClassName RegisterDialog
 * @author Silence~
 * @date 2022/4/27
 * @Description 用于注册的窗口
 */
class RegisterDialog(@NonNull context: Context, username: String, password: String, listener: OnCloseListener) : Dialog(context){

    private lateinit var mEtUsername: TextInputEditText
    private lateinit var mEtPassword1: TextInputEditText
    private lateinit var mEtPassword2: TextInputEditText
    private lateinit var mBtnRegister: MaterialButton
    private val mOnCloseListener:OnCloseListener = listener
    private var mUserBean: UserBean

    init {
        mUserBean = UserBean(username, password)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //去掉系统的黑色矩形边框
        window!!.setBackgroundDrawableResource(android.R.color.transparent)
        requestWindowFeature(Window.FEATURE_NO_TITLE) // 加上这句后就可以在 xml 中自定义高和宽

        setContentView(R.layout.dialog_register)

        setCanceledOnTouchOutside(false) // 设置窗口边界外触摸时不关闭 dialog

        initView()
        initClick()
        initEvent()
    }

    private fun initView(){
        mEtUsername = findViewById(R.id.dialog_et_register_username)
        mEtPassword1 = findViewById(R.id.dialog_et_register_password1)
        mEtPassword2 = findViewById(R.id.dialog_et_register_password2)
        mBtnRegister = findViewById(R.id.dialog_btn_register_register)

        mEtUsername.setText(mUserBean.getUsername())
        mEtPassword1.setText(mUserBean.getPassword())
    }

    private fun initClick(){
        mBtnRegister.setOnClickListener {
            register()
        }
    }

    /**
     * @Description 初始化事件,主要是用户输入错误时的提醒
     * @date 2022/4/30 21:26
     */
    private fun initEvent(){
        mEtUsername.addTextChangedListener {
            if (mEtUsername.text.toString().length < 8 ||
                mEtUsername.text.toString().length > 12) {
                mEtUsername.error = "用户名长度为8-12!"
            } else if(LoginModel.isAlreadyRegister(mEtUsername.text.toString())){
                mEtUsername.error = "该用户名已被注册!"
            }
            else {
                mEtUsername.error = null
            }
        }
        mEtPassword1.addTextChangedListener {
            if (mEtPassword1.text.toString().length < 6 ||
                mEtPassword1.text.toString().length > 16) {
                mEtPassword1.error = "密码长度为6-16!"
            }
            else if (!it.toString().matches(".*[a-zA-Z]+.*".toRegex())) {
                mEtPassword1.error = "密码必须包含字母!"
            }
            else {
                mEtPassword1.error = null
            }
        }
        mEtPassword2.addTextChangedListener {
            if (mEtPassword2.text.toString() != mEtPassword1.text.toString()) {
                mEtPassword2.error = "两次输入的密码不一致!"
            } else {
                mEtPassword2.error = null
            }
        }
    }

    private fun register(){
        val username = mEtUsername.text.toString()
        val password1 = mEtPassword1.text.toString()
        val password2 = mEtPassword2.text.toString()
        var isOk = true
        if (isOk && (username.length < 8 ||
                    username.length > 12 ||
                    mEtUsername.error != null)) {
            mEtUsername.error = "用户名长度为8-12!"
            isOk = false
        }
        if (isOk && (password1.length < 6 ||
                    password1.length > 16 ||
                    mEtPassword1.error != null)) {
            mEtPassword1.error = "密码长度为6-16!"
            isOk = false
        }
        if (isOk && (password2 != password1 || mEtPassword2.error != null)) {
            mEtPassword2.error = "两次输入的密码不一致!"
            isOk = false
        }
        if (isOk && LoginModel.isAlreadyRegister(username) ) {
            mEtUsername.error = "该用户名已被注册!"
            isOk = false
        }
        if (isOk){
            mUserBean = UserBean(username,password1)
            mOnCloseListener.onClose(mUserBean)
            dismiss()
        }
    }

    interface OnCloseListener {
        fun onClose(bean: UserBean)
    }
}