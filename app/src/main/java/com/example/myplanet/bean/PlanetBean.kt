package com.example.myplanet.bean

import com.example.myplanet.utils.TimeUtil
import java.io.Serializable

/**
 * @ClassName PlanetBean
 * @author Silence~
 * @date 2022/5/1
 * @Description 储存星球信息的数据类
 */
data class PlanetBean(private var name : String,
                      private var previewTime : String,
                      private var time: Int,
                      private var imageID : Int,
                      private var remarks : String = ""): Serializable {

    fun getName() = name
    fun getPreviewTime() = previewTime
    fun getTime() = time
    fun getImageID() = imageID
    fun getRemarks() = remarks

    fun getTimeString() : String{
        val minute : Int = time / 60
        val second : Int = time % 60
        return TimeUtil.toEnglishTime(minute,second)
    }

    fun setName(name : String){
        this.name = name
    }
    fun setPreviewTime(time: String){
        this.previewTime = time
    }
    fun setTime(time: Int){
        this.time = time
    }
    fun addTime(addTime: Int){
        this.time += addTime
    }
    fun setImageID(imageID: Int){
        this.imageID = imageID
    }
    fun setRemarks(remarks: String){
        this.remarks = remarks
    }

    companion object {
        private const val PLANET_BEAN_SPLIT_SYMBOL = "[PLANET_BEAN_SPLIT_SYMBOL]"
        const val PLANET_LIST_SPLIT_SYMBOL = "[PLANET_LIST_SPLIT_SYMBOL]"

        /**
         * @Description 将一段字符串转换为PlanetBean类对象的方法
         * @return 返回一个PlanetBean类型对象,如果传入的参数为null则返回null
         * @Param content 一个字符串,记录了星球的信息
         * @date 2022/5/2 20:52
         */
        private fun getPlanetBeanFromString(content: String?): PlanetBean? {
            if (content == null) {
                return null
            }
            val split = content.split(PLANET_BEAN_SPLIT_SYMBOL)
            return PlanetBean(
                split[0],
                split[1],
                split[2].toInt(),
                split[3].toInt(),
                split[4]
            )
        }

        /**
         * @Description 将一段字符串转换为PlanetBean类列表的方法
         * @return 返回一个PlanetBean类型列表,如果传入的参数为null则返回null
         * @Param content 一个字符串,记录了数个星球的信息
         * @date 2022/5/2 20:52
         */
        fun getPlanetListFromString(content: String?): ArrayList<PlanetBean>? {
            if (content == null) {
                return null
            }
            val planetList = ArrayList<PlanetBean>()
            val split = content.split(PLANET_LIST_SPLIT_SYMBOL)
            for (data in split){
                if (data == ""){
                    continue
                }
                planetList.add(getPlanetBeanFromString(data)!!)
            }
            return planetList
        }
    }

    /**
     * @Description 返回一个记录此对象信息的字符串
     * @date 2022/5/2 20:55
     */
    fun getPlanetBeanString() = "${name}$PLANET_BEAN_SPLIT_SYMBOL" +
            "${previewTime}$PLANET_BEAN_SPLIT_SYMBOL" +
            "${time}$PLANET_BEAN_SPLIT_SYMBOL" +
            "${imageID}$PLANET_BEAN_SPLIT_SYMBOL" +
            "${remarks}$PLANET_BEAN_SPLIT_SYMBOL"

}
