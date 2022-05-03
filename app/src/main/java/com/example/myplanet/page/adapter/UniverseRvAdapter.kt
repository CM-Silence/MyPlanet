package com.example.myplanet.page.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myplanet.R
import com.example.myplanet.bean.PlanetBean

/**
 * @ClassName UniverseRvAdapter
 * @author Silence~
 * @date 2022/5/3
 * @Description UniverseWaitFragment中的RecycleView适配器
 */
class UniverseRvAdapter(private val planetList : List<PlanetBean>, private val listener : OnClickItemListener)
    : RecyclerView.Adapter<UniverseRvAdapter.InnerHolder>(){

    private val holderList = ArrayList<InnerHolder>()

    inner class InnerHolder(view : View) : RecyclerView.ViewHolder(view){
        lateinit var mPlanetBean : PlanetBean
        val mIvPlanet : ImageView = view.findViewById(R.id.view_iv_myplanetshow_image)
        val mTvName : TextView = view.findViewById(R.id.view_tv_myplanetshow_name)
        var isChoose = false

        init {
            holderList.add(this)
            mIvPlanet.setOnClickListener {
                setPlanetChoose(this)
                listener.onClickItem(mPlanetBean)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UniverseRvAdapter.InnerHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_myplanetshow, parent, false)
        return InnerHolder(view)
    }

    override fun onBindViewHolder(holder: UniverseRvAdapter.InnerHolder, position: Int) {
        val planet = planetList[position]
        holder.mPlanetBean = planet
        holder.mIvPlanet.setImageResource(planet.getImageID())
        holder.mIvPlanet.setBackgroundResource(R.drawable.selector_btn)
        holder.mTvName.text = planet.getName()

    }

    override fun getItemCount(): Int {
        return planetList.size
    }

    private fun setPlanetChoose(h: InnerHolder){
        for(holder in holderList){
            holder.isChoose = h == holder
        }
    }

    interface OnClickItemListener {
        fun onClickItem(planet : PlanetBean)
    }

}