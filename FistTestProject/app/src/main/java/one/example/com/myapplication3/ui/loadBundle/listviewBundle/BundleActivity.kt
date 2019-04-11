package one.example.com.myapplication3.ui.loadBundle.listviewBundle

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.databinding.DataBindingUtil
import one.example.com.data.BaseData
import one.example.com.myapplication3.R
import one.example.com.myapplication3.databinding.ActivityBundleBinding

class BundleActivity : AppCompatActivity() {
    var binding: ActivityBundleBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_bundle)
        var adapter = ListViewAdapter(this)
        binding?.listview!!.adapter = adapter
        adapter.setData(ListViewDataInfo.getData())
    }


    class ListViewAdapter(context: Context) : BaseAdapter() {
        var mContext: Context? = null

        init {
            mContext = context
        }

        var mList = ArrayList<BaseData>()
        fun setData(list: List<BaseData>) {
            this.mList = list as ArrayList<BaseData>
            notifyDataSetChanged()
        }

        fun getViewType(position: Int):Int{
            return mList.get(position).viewType
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
            return ViewHolderManager.getView(getViewType(position), mContext, position,mList.get(position), convertView,
                    parent)
        }

        override fun getItem(position: Int): Any {
            return mList.get(position)
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return mList.size
        }
    }
}
