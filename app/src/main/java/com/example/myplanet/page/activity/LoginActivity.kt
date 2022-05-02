package com.example.myplanet.page.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast
import com.example.myplanet.R
import com.example.myplanet.base.BaseActivity
import com.example.myplanet.base.MyApplication
import com.example.myplanet.bean.UserBean
import com.example.myplanet.model.LoginModel
import com.example.myplanet.page.dialog.RegisterDialog
import com.example.myplanet.utils.ToastUtil
import com.google.android.material.textfield.TextInputEditText

/**
 * @ClassName LoginActivity
 * @author Silence~
 * @date 2022/4/27
 * @Description 登录界面
 */
class LoginActivity : BaseActivity() {
    private lateinit var mEtUsername : TextInputEditText
    private lateinit var mEtPassword : TextInputEditText
    private lateinit var mCbRemember : CheckBox
    private lateinit var mCbAutoLogin : CheckBox
    private lateinit var mBtnLogin : Button
    private lateinit var mBtnRegister : Button

    companion object{
        fun startActivity(context: Context,isLogout : Boolean = false){
            val intent = Intent(context, LoginActivity::class.java)
            intent.putExtra("param",isLogout)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(R.anim.anim_alpha_in, 0) //入场动画
        setContentView(R.layout.activity_login)
        initView()
        initClick()
        initUserBean()
    }

    /**
     * @Description 初始化View
     * @date 2022/4/27 11:04
     */
    private fun initView(){
        mEtUsername = findViewById(R.id.activity_et_login_username)
        mEtPassword = findViewById(R.id.activity_et_login_password)
        mCbRemember = findViewById(R.id.activity_cb_login_remember)
        mCbAutoLogin = findViewById(R.id.activity_cb_login_autologin)
        mBtnLogin = findViewById(R.id.activity_btn_login_login)
        mBtnRegister = findViewById(R.id.activity_btn_login_register)
    }

    /**
     * @Description 初始化ClickListener
     * @date 2022/4/27 11:08
     */
    private fun initClick(){
        mBtnLogin.setOnClickListener {
            login(mEtUsername.text.toString(),mEtPassword.text.toString())
        }

        mBtnRegister.setOnClickListener {
            register()
        }
    }

    /**
     * @Description 用于登录的方法,判断传入的账号密码是否匹配
     * @Param username 传入的账号
     * @Param password 传入的密码
     * @date 2022/4/27 16:18
     */
    private fun login(username : String,password : String){
        if(LoginModel.isSamePassword(username, password)){
            val userBean = UserBean(username, password)
            if(mCbAutoLogin.isChecked){ //如果勾选了自动登录
                userBean.setAutoLogin(true)
            }
            else{
                userBean.setAutoLogin(false)
            }
            if(mCbRemember.isChecked){ //如果勾选了记住密码
                userBean.setRemember(true)
                LoginModel.setRememberUser(userBean) //储存保存的账号密码
            }
            else{
                userBean.setRemember(false)
                LoginModel.setRememberUser(null) //清空储存的账号密码
            }
            LoginModel.addUserBean(userBean) //储存账号密码
            userBean.setPassword("") //清除密码后传入MainActivity中
            MainActivity.startActivity(this,userBean)
            finish()
        }
        else{
            ToastUtil.show("账号或密码错误!")
        }
    }

    /**
     * @Description 用于弹出注册窗口的方法
     * @date 2022/4/27 11:18
     */
    private fun register(){
        val username = mEtUsername.text.toString()
        val password = mEtPassword.text.toString()
        RegisterDialog(this ,username, password, object : RegisterDialog.OnCloseListener{
            override fun onClose(bean: UserBean) {
                mEtUsername.setText(bean.getUsername())
                mEtPassword.setText(bean.getPassword())
                LoginModel.addUserBean(bean)
                ToastUtil.show("注册成功!账号为:${bean.getUsername()}",true)
            }
        }).show()
    }

    /**
     * @Description 初始化用户信息
     * @date 2022/4/27 16:26
     */
    private fun initUserBean(){
        val userBean = LoginModel.getRememberUser()
        if(userBean != null){ //如果之前用户勾选了记住密码选项则保存用户之前的设置
            mEtUsername.setText(userBean.getUsername())
            mEtPassword.setText(userBean.getPassword())
            mCbRemember.isChecked = userBean.isRemember()
            mCbAutoLogin.isChecked = userBean.isAutoLogin()
            //如果用户勾选了记住密码则直接登录(除非是用户自己退出登录)
            if(mCbAutoLogin.isChecked && !intent.getBooleanExtra("param",false)){
                userBean.setPassword("") //清除密码后传入MainActivity
                MainActivity.startActivity(this,userBean)
                finish()
            }
        }
    }
}