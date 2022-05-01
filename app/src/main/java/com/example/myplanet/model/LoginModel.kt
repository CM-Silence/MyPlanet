package com.example.myplanet.model

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import com.example.myplanet.base.MyApplication
import com.example.myplanet.bean.UserBean
import com.example.myplanet.utils.AESCryptUtil

/**
 * @ClassName LoginModel
 * @author Silence~
 * @date 2022/4/27
 * @Description 登录模板类,用于保存、获取、加密账号密码,判断用户输入的账号密码是否匹配
 */
object LoginModel {
    private var sharedPreferences : SharedPreferences? = null
    private const val SP_NAME = "Login"
    private const val SP_REMEMBER_NAME = "remember"
    private const val AES_KEY = "1234987655552333" //解密的密钥,可以随时更换,不过长度要大于等于16位

    /**
     * @Description 通过sharedPreferences获取记住的UserBean(如果找不到则返回null)
     * @return UserBean
     * @date 2022/4/27 12:31
     */
    fun getRememberUser() : UserBean?{
        val sp = getSp()
        return UserBean.getUserBeanFromString(AESCryptUtil.decrypt(sp?.getString(SP_REMEMBER_NAME,null), AES_KEY) )
    }

    /**
     * @Description 通过sharedPreferences设置记住的UserBean并将其加密
     * @Param bean 要储存的UserBean,传入null则删除之前保存的密码账号
     * @date 2022/4/27 12:43
     */
    @SuppressLint("CommitPrefEdits")
    fun setRememberUser(bean : UserBean?){
        val sp = getSp()
        val edit = sp?.edit()
        if(bean != null){
            edit?.putString(SP_REMEMBER_NAME,AESCryptUtil.encrypt(bean.getUserBeanString(), AES_KEY)) //储存记住的账号密码
            edit?.putString(bean.getUsername(),AESCryptUtil.encrypt(bean.getPassword(), AES_KEY)) //储存账号密码
        }
        else{
            edit?.remove(SP_REMEMBER_NAME)
        }
        edit?.apply()
    }

    /**
     * @Description 用于储存账号密码或更改账号设置
     * @Param bean 要添加的UserBean
     * @date 2022/4/27 12:48
     */
    fun addUserBean(bean: UserBean) {
        val sp = getSp()
        val edit = sp?.edit()
        edit?.putString(bean.getUsername(), AESCryptUtil.encrypt(bean.getUserBeanString(), AES_KEY) )
        edit?.apply()
    }

    /**
     * @return 返回true则账号密码正确,反之则错误
     * @Param username 传入的账号
     * @param password 传入的密码
     * @date 2022/4/27 12:51
     */
    fun isSamePassword(username: String, password: String): Boolean {
        val sp = getSp()
        val beanString = AESCryptUtil.decrypt(sp?.getString(username, null), AES_KEY)
        val bean = UserBean.getUserBeanFromString(beanString)
        return if (bean != null) {
            password == bean.getPassword()
        } else false
    }

    /**
     * @Description 延迟初始化SharedPreferences的方法
     * @return SharedPreferences
     * @date 2022/4/27 11:20
     */
    private fun getSp(): SharedPreferences? {
        if (sharedPreferences == null) {
            sharedPreferences = MyApplication.getAppContext().getSharedPreferences(SP_NAME, Context.MODE_PRIVATE)
        }
        return sharedPreferences
    }

}