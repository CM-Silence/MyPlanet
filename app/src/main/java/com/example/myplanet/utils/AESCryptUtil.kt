package com.example.myplanet.utils

import android.annotation.SuppressLint
import java.util.*
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec

/**
 * @ClassName AESCryptUtil
 * @author Silence~
 * @date 2022/4/30
 * @Description 用于加密解密字符串的工具类
 */
object AESCryptUtil {

    /**
     * @Description 加密字符串的方法
     * @return 加密后的字符串
     * @Param input 需要加密的字符串,如果传入null则返回null
     * @param password 解密所需的密钥(长度 >= 16)
     * @date 2022/4/30 20:36
     */
    @SuppressLint("GetInstance")
    fun encrypt(input:String?, password:String): String?{
        if(input == null){
            return null
        }
        //初始化cipher对象
        val cipher = Cipher.getInstance("AES")
        // 生成密钥
        val keySpec = SecretKeySpec(password.toByteArray(),"AES")
        cipher.init(Cipher.ENCRYPT_MODE,keySpec)
        //加密解密
        val encrypt = cipher.doFinal(input.toByteArray())
        val result = Base64.getEncoder().encode(encrypt)

        return String(result)
    }

    /**
     * @Description
     * @return 解密后的字符串
     * @Param input 需要解密的字符串,如果传入null则返回null
     * @param password 解密所需密钥,与加密时传入的密钥一致才能成功解密
     * @date 2022/4/30 20:41
     */
    @SuppressLint("GetInstance")
    fun decrypt(input: String?, password: String): String? {
        if(input == null){
            return null
        }
        //初始化cipher对象
        val cipher = Cipher.getInstance("AES")
        // 生成密钥
        val keySpec = SecretKeySpec(password.toByteArray(), "AES")
        cipher.init(Cipher.DECRYPT_MODE, keySpec)
        //加密解密
        val encrypt = cipher.doFinal(Base64.getDecoder().decode(input.toByteArray()))

        return String(encrypt)
    }
}