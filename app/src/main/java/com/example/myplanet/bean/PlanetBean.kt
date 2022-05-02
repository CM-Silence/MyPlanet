package com.example.myplanet.bean

/**
 * @ClassName PlanetBean
 * @author Silence~
 * @date 2022/5/1
 * @Description 储存星球信息的数据类
 */
data class PlanetBean(private var name : String,
                      private var previewTime : Int,
                      private var time: Int,
                      private var imageID : Int){

    fun getName() = name
    fun getPreviewTime() = previewTime
    fun getTime() = time
    fun getImageID() = imageID
}
