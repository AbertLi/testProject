package one.example.com.myapplication3.ui.loadBundle.listviewBundle

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import one.example.com.data.BaseData
import one.example.com.myapplication3.R

class ViewHolderManager {


    class ViewHolder1(var view: View) : BaseHolder {
        var title: TextView = view.findViewById(R.id.tv_title)
        var content: TextView = view.findViewById(R.id.tv_constent)
    }

    companion object {
        val DEFULE_ITEM = 0
        val AD_ITEM = 1
        val VIDEO_ITEM = 2
        fun getView(viewType: Int, context: Context?, position: Int, baseInfo: BaseData, convertView: View?, parent:
        ViewGroup?):
                View? {

            var holder: BaseHolder? = null
            var root: View? = null
            var listViewHolder = baseInfo as ListViewDataInfo

            if (convertView == null) {
                when (viewType) {
                    AD_ITEM -> {
                    }

                    DEFULE_ITEM -> {
                        root = LayoutInflater.from(context).inflate(R.layout.listview_item_one, parent, false)
                        holder = ViewHolder1(root)
                        root.tag = holder
                    }
                }
            } else {
                root = convertView
                holder = root.tag as BaseHolder
            }

            when (viewType) {
                AD_ITEM -> {
                }

                DEFULE_ITEM -> {
                    if (holder is ViewHolder1) {///////////////////////////////////////
                        holder.title.setText(listViewHolder.title)
                        holder.content.setText(listViewHolder.constent)
                    }
                }
            }
            return root
        }
    }
}