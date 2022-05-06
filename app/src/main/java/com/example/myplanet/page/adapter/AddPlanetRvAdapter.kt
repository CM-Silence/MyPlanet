package com.example.myplanet.page.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myplanet.R
import java.lang.Exception

/**
 * @ClassName AddPlanetRvAdapter
 * @author Silence~
 * @date 2022/5/6
 * @Description 添加星球中选择外观的RecycleView的适配器
 */
class AddPlanetRvAdapter(private val srcList : List<Int>, private val listener : OnClickItemListener)
    : RecyclerView.Adapter<AddPlanetRvAdapter.InnerPlanetHolder>(){

    private val holderList = ArrayList<InnerPlanetHolder>()

    inner class InnerPlanetHolder(view : View) : RecyclerView.ViewHolder(view){
        private val mTvName : TextView = view.findViewById(R.id.view_tv_myplanetshow_name)
        val mIvPlanet : ImageView = view.findViewById(R.id.view_iv_myplanetshow_image)
        var src : Int = 0
        var isChoose = false

        init {
            holderList.add(this)
            mTvName.visibility = View.GONE
            mIvPlanet.setOnClickListener {
                setPlanetChoose(this)
                mIvPlanet.setBackgroundResource(R.drawable.general_background_full)
                listener.onClickItem(src)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddPlanetRvAdapter.InnerPlanetHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_myplanetshow, parent, false)
        return InnerPlanetHolder(view)
    }

    override fun onBindViewHolder(holder: AddPlanetRvAdapter.InnerPlanetHolder, position: Int) {
        holder.src = srcList[position]
        holder.mIvPlanet.setImageResource(srcList[position])
        if(holder.isChoose){
            holder.mIvPlanet.setBackgroundResource(R.drawable.general_background_full)
        }
        else{
            holder.mIvPlanet.setBackgroundResource(R.drawable.general_background)
        }
    }

    override fun getItemCount(): Int {
        return srcList.size
    }

    private fun setPlanetChoose(h: InnerPlanetHolder){
        for(holder in holderList){
            holder.isChoose = h == holder
            try {
                if (holder.isChoose) {
                    holder.mIvPlanet.setBackgroundResource(R.drawable.general_background_full)
                } else {
                    holder.mIvPlanet.setBackgroundResource(R.drawable.general_background)
                }
            }catch (e : Exception){
                e.printStackTrace()
            }
        }
    }

    interface OnClickItemListener {
        fun onClickItem(src : Int)
    }

}