package com.wifi.mvpsample

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class GoodsAdapter(var context: Context, var list: List<GoodsInfo>) : BaseAdapter() {
    class ViewHolder() {
        var image: ImageView? = null
        var like: TextView? = null
        var type: TextView? = null
    }


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        var viewHolder: ViewHolder? = null
        var view: View? = null
        var item = list.get(position)
        if (convertView == null) {
            viewHolder = ViewHolder()
            view = LayoutInflater.from(context).inflate(R.layout.goods_item, null)
            viewHolder.image = view.findViewById(R.id.imageview)
            viewHolder.like = view.findViewById(R.id.like)
            viewHolder.type = view.findViewById(R.id.type)
            view?.tag = viewHolder

        } else {
            viewHolder = convertView.tag as ViewHolder
            view = convertView
        }
        viewHolder.image?.setImageResource(item.image)
        viewHolder.like?.text = item.like
        viewHolder.type?.text = item.stye
        return view
    }

    override fun getItem(position: Int): Any {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return list.size
    }
}