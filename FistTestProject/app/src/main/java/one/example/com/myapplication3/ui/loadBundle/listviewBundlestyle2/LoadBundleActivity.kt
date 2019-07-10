package one.example.com.myapplication3.ui.loadBundle.listviewBundlestyle2

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import one.example.com.data.BaseData
import one.example.com.myapplication3.R
import one.example.com.myapplication3.databinding.ActivityLoadBundleBinding
import one.example.com.recyclerview.ICallBack
import one.example.com.recyclerview.ItemViewTypeManager
import java.util.ArrayList

class LoadBundleActivity : AppCompatActivity() {
    var binding: ActivityLoadBundleBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this@LoadBundleActivity, R.layout.activity_load_bundle)
        init()
    }

    fun init() {
        ItemViewTypeManager.getInstance().registerItem(Constant.DEFULT_ITEM, ViewItem1())
        ItemViewTypeManager.getInstance().registerItem(Constant.AD_ITEM1, ViewItem2())
        ItemViewTypeManager.getInstance().registerItem(Constant.AD_ITEM2, ViewItem3())

        binding?.recyclerview!!.setLayoutManager(LinearLayoutManager(this))
        var adapter = MyAdapter(callBack1, callBack2, callBack3, this)
        binding?.recyclerview!!.setAdapter(adapter)

        var data =  getListData()
        adapter.setmListInfo(data)
    }

    fun getListData(): List<MyDataInfo> {
        var datalist = ArrayList<MyDataInfo>()
        for (i in 1..100) {
            var item = MyDataInfo()
            item.id = i.toString()
            item.title = "第： " + i + " 个信息流数据"

            if (i % 10 == 0) {
                item.viewType = Constant.AD_ITEM2
            } else if (i % 5 == 0) {
                item.viewType = Constant.AD_ITEM1
            } else {
                item.viewType = Constant.DEFULT_ITEM
            }
        }
        return datalist
    }

    var callBack1 = object:ICallBack{
        override fun itemOnClick(position: Int, data: BaseData?) {
            showToast("样式1监听",this@LoadBundleActivity)
        }
    }//样式1监听

    var callBack2 = object:ICallBack{
        override fun itemOnClick(position: Int, data: BaseData?) {
            showToast("样式2监听",this@LoadBundleActivity)
        }
        fun downLoad(){
        }
    }//样式2监听

    var callBack3 = object:ICallBack{
        override fun itemOnClick(position: Int, data: BaseData?) {
            showToast("样式3监听",this@LoadBundleActivity)
        }
    }//样式3监听

    /**
     * 实现ICallBack接口
     * 添加各种Item样式需要带的事件方法
     */
    companion object{
         fun showToast(str: String?, con: Context) {
            Toast.makeText(con, str, Toast.LENGTH_SHORT).show();
        }
    }
}


