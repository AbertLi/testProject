package one.example.com.myapplication3.ui.loadBundle

import java.util.HashMap

import one.example.com.data.DataInfo
import one.example.com.recyclerview.ItemViewTypeManager
import one.example.com.recyclerview.MultipleItemViewAdapter

internal class MyAdapter(callBack: Any) : MultipleItemViewAdapter<DataInfo>() {

    init {
        val mapCallBack = HashMap<String, Any>()
        mapCallBack["clickCallBack"] = callBack
        viewTypeCallBackMap[ItemViewTypeManager.DEFULT_ITEM] = mapCallBack
        viewTypeCallBackMap[ItemViewTypeManager.AD_ITEM] = mapCallBack
    }

}
