package com.example.myplanet.bean

import com.example.myplanet.utils.AESCryptUtil


/**
 * @ClassName UserBean
 * @author Silence~
 * @date 2022/4/27
 * @Description 用于储存用户的账号密码以及是否记住密码，是否自动登录
 */
data class UserBean(private val username : String, //账号
                    private var password : String, //密码
                    private var isRemember: Boolean = false, //是否记住密码
                    private var isAutoLogin : Boolean = false, //是否自动登录
                    private var name : String = "",  //昵称
                    private var signature: String = "",  //个性签名
                    private var headPortraitAddress : String = ""){ //头像地址

    init {
        //this.password = AESCryptUtil.encrypt(this.password, AES_KEY).toString()
    }

    companion object {

        //private const val AES_KEY = "1234987655552333" //解密的密钥,可以随时更换,不过长度要大于等于16位

        /**
         * @Description 将一段字符串转换为UserBean类对象的方法
         * @return 返回一个UserBean类型对象,如果传入的参数为null则返回null
         * @Param content 一个字符串,记录了账号的信息
         * @date 2022/4/30 17:27
         */
        fun getUserBeanFromString(content: String?): UserBean? {
            if (content == null) {
                return null
            }
            val split = content.split(",")
            return UserBean(
                split[0],
                split[1],
                java.lang.Boolean.parseBoolean(split[2]),
                java.lang.Boolean.parseBoolean(split[3]),
                split[4],
                split[5],
                split[6]
            )
        }
    }

    /**
     * @Description 将一个UserBean类对象转换为字符串的方法
     * @return 一个字符串,记录了账号的信息
     * @date 2022/4/30 17:30
     */
    fun getUserBeanString() = "${username},${password},${isRemember},${isAutoLogin},${name},${signature},${headPortraitAddress}"

    fun getUsername() = username
    fun getPassword() = password
    fun getName() = name
    fun getSignature() = signature
    fun getHeadPortraitAddress() = headPortraitAddress
    fun isRemember() = isRemember
    fun isAutoLogin() = isAutoLogin

    fun setPassword(password: String){
        this.password = password
    }

    fun setRemember(isRemember: Boolean){
        this.isRemember = isRemember
    }

    fun setAutoLogin(isAutoLogin: Boolean){
        this.isAutoLogin = isAutoLogin
    }

    fun setName(name : String){
        this.name = name
    }

    fun setSignature(signature : String){
        this.signature = signature
    }

}