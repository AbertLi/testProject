package one.example.com.myapplication3.ui.wheelview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import one.example.com.myapplication3.R


/**
 * @Author: wuchaowen
 * @Description:
 * @Time:
 */
class ViewPager2Adapter(context: Context?, data: List<String>, viewPager2: ViewPager2) : RecyclerView.Adapter<ViewPager2Adapter.ViewHolder?>() {
    private val mData: List<String> = data
    private val mInflater: LayoutInflater = LayoutInflater.from(context)
    private val colorArray = intArrayOf(android.R.color.black,
            android.R.color.holo_blue_dark,
            android.R.color.holo_green_dark,
            android.R.color.holo_red_dark)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = mInflater.inflate(R.layout.view_pager_rv_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val animal = mData[position]
        holder.myTextView.text = animal
        holder.relativeLayout.setBackgroundResource(colorArray[position])
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    inner class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var myTextView: TextView = itemView.findViewById(R.id.tvTitle)
        var relativeLayout: RelativeLayout = itemView.findViewById(R.id.container)
    }
}

