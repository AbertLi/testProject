package one.example.com.myapplication3.ui.loadBundle.listviewBundlestyle2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import one.example.com.data.BaseData
import one.example.com.myapplication3.R
import one.example.com.myapplication3.databinding.ActivityLoadBundleBinding
import one.example.com.recyclerview.ICallBack
import one.example.com.recyclerview.ItemViewTypeManager

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
        var adapter = MyAdapter(callBack1,callBack2,callBack3)
        binding?.recyclerview!!.setAdapter(adapter)
        adapter.setmListInfo(null)
    }

    var callBack1 = ClickCallBackImp1()
    var callBack2 = ClickCallBackImp2()
    var callBack3 = ClickCallBackImp3()

    /**
     * 实现ICallBack接口
     * 添加各种Item样式需要带的事件方法
     */
    class ClickCallBackImp1:ICallBack{
        override fun itemOnClick(position: Int, data: BaseData?) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        fun downLoad(){

        }
    }

    class ClickCallBackImp2:ICallBack{
        override fun itemOnClick(position: Int, data: BaseData?) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

    }

    class ClickCallBackImp3:ICallBack{
        override fun itemOnClick(position: Int, data: BaseData?) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        fun downLoad(){

        }
    }




}
