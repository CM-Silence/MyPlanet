package com.example.myplanet.bean

import com.example.myplanet.utils.AESCryptUtil
import java.io.Serializable


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
                    private var isNew : Boolean = true, //是否是新用户
                    private var name : String = "",  //昵称
                    private var signature: String = "",  //个性签名
                    private var headPortraitAddress : String = "",  //头像地址
                    private val planetList: ArrayList<PlanetBean> = ArrayList<PlanetBean>() //用户的星球
                    ) : Serializable{

    fun getPlanetList() = planetList

    fun addPlanet(planetBean: PlanetBean){
        planetList.add(planetBean)
    }

    fun removePlanet(planetBean: PlanetBean){
        planetList.remove(planetBean)
    }

    /**
     * @Description 用于将星球置顶的方法
     * @Param planetBean 需要置顶的星球
     * @date 2022/5/2 21:37
     */
    fun setFirstPlanet(planetBean: PlanetBean){
        for (i in 0 until planetList.size){
            if(planetList[i] == planetBean){
                val temp = planetList[0]
                planetList[0] = planetBean
                planetList[i] = temp
            }
        }
    }



    companion object {

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
            return PlanetBean.getPlanetListFromString(split[8])?.let {
                UserBean(
                    split[0],
                    split[1],
                    java.lang.Boolean.parseBoolean(split[2]),
                    java.lang.Boolean.parseBoolean(split[3]),
                    java.lang.Boolean.parseBoolean(split[4]),
                    split[5],
                    split[6],
                    split[7],
                    it
                )
            }
        }

        /**
         * @Description 从记录账号信息的字符串中获取其密码的方法
         * @return
         * @Param
         * @author Silence~
         * @date 2022/5/2 17:14
         */
        fun getPasswordFromString(content: String?): String? {
            if (content == null) {
                return null
            }
            val split = content.split(",")
            return split[1]
        }
    }


    /**
     * @Description 将一个UserBean类对象转换为字符串的方法
     * @return 一个字符串,记录了账号的信息
     * @date 2022/4/30 17:30
     */
    fun getUserBeanString() : String{
        var data = "${username},${password},${isRemember},${isAutoLogin},${isNew},${name},${signature},${headPortraitAddress},"
        for (planet in planetList){
            data += (planet.getPlanetBeanString() + "@")
        }
        return data
    }

    fun getUsername() = username
    fun getPassword() = password
    fun getName() = name
    fun getSignature() = signature
    fun getHeadPortraitAddress() = headPortraitAddress
    fun isRemember() = isRemember
    fun isAutoLogin() = isAutoLogin
    fun isNew() = isNew

    fun setPassword(password: String){
        this.password = password
    }

    fun setRemember(isRemember: Boolean){
        this.isRemember = isRemember
    }

    fun setAutoLogin(isAutoLogin: Boolean){
        this.isAutoLogin = isAutoLogin
    }

    fun setNew(isNew : Boolean){
        this.isNew = isNew
    }

    fun setName(name : String){
        this.name = name
    }

    fun setSignature(signature : String){
        this.signature = signature
    }

}