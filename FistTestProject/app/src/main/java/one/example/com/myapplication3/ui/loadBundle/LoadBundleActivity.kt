package one.example.com.myapplication3.ui.loadBundle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import one.example.com.data.BaseData
import one.example.com.myapplication3.R
import one.example.com.myapplication3.databinding.ActivityLoadBundleBinding
import one.example.com.recyclerview.ClickCallBack
import one.example.com.recyclerview.ItemViewTypeManager

class LoadBundleActivity : AppCompatActivity() {
    var binding: ActivityLoadBundleBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this@LoadBundleActivity, R.layout.activity_load_bundle)
        init()
    }

    fun init() {
        ItemViewTypeManager.getInstance().registerItem(ItemViewTypeManager.DEFULT_ITEM,ViewItem())
        binding?.recyclerview!!.setLayoutManager(LinearLayoutManager(this))
        var adapter = MyAdapter(callBack)
        binding?.recyclerview!!.setAdapter(adapter)
        adapter.setmListInfo(null)
    }

    var callBack = CallBacks()

    class CallBacks : ClickCallBack() {
        override fun startPlay(v: View?, position: Int, datainfo: BaseData?) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun downLoad(v: View?, position: Int, datainfo: BaseData?) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun skip(v: View?, position: Int, datainfo: BaseData?) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

    }


}
