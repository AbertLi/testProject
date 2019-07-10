package one.example.com.myapplication3.ui.loadBundle.listviewBundlestyle2

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import one.example.com.recyclerview.AbstractView
import one.example.com.data.BaseData
import one.example.com.myapplication3.R
import one.example.com.recyclerview.BaseViewItem

/**
 * 常用的Item样式
 */
class ViewItem3 : BaseViewItem {
    override fun inflate(viewGroup: ViewGroup?, context: Context): AbstractView {
        var view = LayoutInflater.from(context).inflate(R.layout.recyclerview_item3, null, false)
        return AbstractView(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int, info: BaseData?, callback: MutableMap<String, Any>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

    }
}