package com.example.myplanet.utils

/**
 * @ClassName TimeUtil
 * @author Silence~
 * @date 2022/5/3
 */
object TimeUtil {

    /**
     * @Description 如果数字大于10则返回原数字,如果小于10则在数字前加"0"再返回
     * @return 一个字符串
     * @author Silence~
     * @date 2022/5/2 0:05
     */
    private fun Int.toTimeString() : String{
        if(this < 10){
            return "0${this}"
        }
        return this.toString()
    }

    /**
     * @return 时间,格式为mm:ss
     * @Param minute 分
     * @param second 秒
     * @date 2022/5/2 23:19
     */
    fun toTime(minute : Int, second : Int) : String{
        return "${minute.toTimeString()}:${second.toTimeString()}"
    }

    /**
     * @return 时间,格式为_min_s
     * @Param minute 分
     * @param second 秒
     * @date 2022/5/2 23:19
     */
    fun toEnglishTime(minute : Int, second : Int) : String{
        return "${minute}min ${second}s"
    }
}