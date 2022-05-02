package com.example.myplanet.bean

import java.io.Serializable

/**
 * @ClassName PlanetBean
 * @author Silence~
 * @date 2022/5/1
 * @Description 储存星球信息的数据类
 */
data class PlanetBean(private var name : String,
                      private var previewTime : Int,
                      private var time: Int,
                      private var imageID : Int): Serializable {

    private var isChoose = false
    fun isChoose() = isChoose
    fun setChoose(isChoose : Boolean){
        this.isChoose = isChoose
    }

    fun getName() = name
    fun getPreviewTime() = previewTime
    fun getTime() = time
    fun getImageID() = imageID

    fun setName(name : String){
        this.name = name
    }
    fun setPreviewTime(time: Int){
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

    companion object {

        /**
         * @Description 将一段字符串转换为PlanetBean类对象的方法
         * @return 返回一个PlanetBean类型对象,如果传入的参数为null则返回null
         * @Param content 一个字符串,记录了星球的信息
         * @date 2022/5/2 20:52
         */
        fun getPlanetBeanFromString(content: String?): PlanetBean? {
            if (content == null) {
                return null
            }
            val split = content.split("-")
            return PlanetBean(
                split[0],
                split[1].toInt(),
                split[2].toInt(),
                split[3].toInt(),
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
            val split = content.split("@")
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
    fun getPlanetBeanString() = "${name}-${previewTime}-${time}-${imageID}"

}
