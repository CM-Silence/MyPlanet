package com.example.myplanet.utils

import android.annotation.SuppressLint
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*


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

    /**
     * @return 时间,格式为yyyy年mm月dd日
     * @param year 年
     * @Param month 月
     * @param day 日
     * @date 2022/5/2 23:19
     */
    fun toDate(year : Int, month : Int, day: Int) : String{
        return "${year}-${month}-${day}"
    }

    /**
     * 获取年
     * @return
     */
    fun getYear(): Int {
        val cd: Calendar = Calendar.getInstance()
        return cd.get(Calendar.YEAR)
    }

    /**
     * 获取月
     * @return
     */
    fun getMonth(): Int {
        val cd: Calendar = Calendar.getInstance()
        return cd.get(Calendar.MONTH) + 1
    }

    /**
     * 获取日
     * @return
     */
    fun getDay(): Int {
        val cd: Calendar = Calendar.getInstance()
        return cd.get(Calendar.DATE)
    }

    /**
     * 获取时
     * @return
     */
    fun getHour(): Int {
        val cd: Calendar = Calendar.getInstance()
        return cd.get(Calendar.HOUR)
    }

    /**
     * 获取分
     * @return
     */
    fun getMinute(): Int {
        val cd: Calendar = Calendar.getInstance()
        return cd.get(Calendar.MINUTE)
    }

    /**
     * 获取当前时间的时间戳(精确)
     * @return
     */
    fun getCurrentTimeMillis(): Long {
        return System.currentTimeMillis()
    }

    /**
     * 获取指定时间的时间戳(到日)
     * @return
     */
    fun getTimeMillisToDay(year : Int,month : Int,day : Int): Long {
        val cd: Calendar = Calendar.getInstance()
        cd.set(year,month,day,getHour() - 1,0)
        return cd.timeInMillis
    }

    /**
     * 获取当前时间
     */
    fun getCurrentTime(): String {
        return getFormatedDateTime(System.currentTimeMillis())
    }

    /**
     * 将long转换为日期（yyyy-MM-dd HH:mm）
     * @param dateTime
     * @return 到分
     */
    @SuppressLint("SimpleDateFormat")
    fun getFormatedDateTime(dateTime: Long): String {
        var time = ""
        try {
            val sDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm")
            time = sDateFormat.format(Date(dateTime + 0))
        } catch (e: Exception) {
        }
        return time
    }
}