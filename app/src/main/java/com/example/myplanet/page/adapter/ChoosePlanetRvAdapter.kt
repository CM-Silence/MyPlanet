package com.example.myplanet.page.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.myplanet.R
import com.example.myplanet.base.MyApplication
import com.example.myplanet.bean.PlanetBean

/**
 * @ClassName ChoosePlanetRvAdapter
 * @author Silence~
 * @date 2022/5/2
 * @Description TimerFragment中下方弹窗中的RecycleView适配器
 */
class ChoosePlanetRvAdapter(private val planetList : List<PlanetBean>,private val listener : OnClickItemListener)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    companion object {
        //尾部按钮布局
        private const val DEFAULT_ADD_VIEW = 1
        //常规布局
        private const val ALBUM_DATA_VIEW = 2
    }

    inner class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        lateinit var mPlanetBean : PlanetBean
        val mIvPlanet : ImageView = view.findViewById(R.id.view_iv_myplanetshow_image)
        val mTvName : TextView = view.findViewById(R.id.view_tv_myplanetshow_name)

        init {
            mIvPlanet.setOnClickListener {
                listener.onClickItem(mPlanetBean)
            }
        }
    }

    inner class ButtonViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val mBtnAdd : ImageView = view.findViewById(R.id.view_btn_myplanetshow_add)

        init {
            mBtnAdd.setOnClickListener {
                Toast.makeText(MyApplication.getAppContext(), "对不起,我实在是肝不动了qaq", Toast.LENGTH_SHORT).show()

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if(viewType == ALBUM_DATA_VIEW) {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.view_myplanetshow, parent, false)
            ViewHolder(view)
        } else{
            val view = LayoutInflater.from(parent.context).inflate(R.layout.view_myplanetshow_button, parent, false)
            ButtonViewHolder(view)
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ViewHolder) {
            val planet = planetList[position]
            holder.mPlanetBean = planet
            holder.mIvPlanet.setImageResource(planet.getImageID())
            holder.mTvName.text = planet.getName()
        } else if (holder is ButtonViewHolder) {
            holder.mBtnAdd
        }
    }

    override fun getItemCount(): Int {
        return planetList.size + 1
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == planetList.size) DEFAULT_ADD_VIEW else ALBUM_DATA_VIEW
    }

    interface OnClickItemListener {
        fun onClickItem(planet : PlanetBean)
    }

}