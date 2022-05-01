package com.example.myplanet.utils
/*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody


/**
 * @ClassName HttpUtil
 * @author Silence~
 * @date 2022/4/27
 * @Description 网络请求工具类
 */
object HttpUtil {

    /**
     * @Description 用okHttp发送get请求的方法
     * @Param address 网络地址
     * @param callback OkHttp自带的回调接口
     * @date 2022/4/27 22:50
     */
    fun sendGetHttpRequest(address : String, callback : okhttp3.Callback){
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(address)
            .build()
        client.newCall(request).enqueue(callback)
    }

    /**
     * @Description 用okHttp发送post请求的方法
     * @Param address 网络地址
     * @param requestBody 要提交的参数
     * @param callback OkHttp自带的回调接口
     * @date 2022/4/27 22:58
     */
    fun sendPostHttpRequest(address : String, requestBody : RequestBody,callback : okhttp3.Callback){
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(address)
            .post(requestBody)
            .build()
        client.newCall(request).enqueue(callback)
    }

    /**
     * @Description 用gson解析json数据的方法
     * @return 返回解析后的数据
     * @Param jsonData 需要解析的Json数据
     * @param T 将json数据转换成的泛型对象
     * @date 2022/4/27 23:03
     */
    fun <T> parseJSONWithGSON(jsonData: String): T {
        val gson = Gson()
        val typeOf = object : TypeToken<T>() {}.type
        return gson.fromJson(jsonData, typeOf)
    }
}

 */