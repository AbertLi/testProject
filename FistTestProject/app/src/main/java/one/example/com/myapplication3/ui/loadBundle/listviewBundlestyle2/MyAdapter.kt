package one.example.com.myapplication3.ui.loadBundle.listviewBundlestyle2
import java.util.HashMap
import one.example.com.data.DataInfo
import one.example.com.recyclerview.MultipleItemViewAdapter

internal class MyAdapter(callBack1: Any,callBack2: Any,callBack3: Any) : MultipleItemViewAdapter<DataInfo>() {

    init {
        val mapCallBack1 = HashMap<String, Any>()
        mapCallBack1["clickCallBack"] = callBack1//除了加入Callback以外也可以加入其它的对象
        viewTypeCallBackMap[Constant.DEFULT_ITEM] = mapCallBack1

        val mapCallBack2 = HashMap<String, Any>()
        mapCallBack2["clickCallBack"] = callBack2
        viewTypeCallBackMap[Constant.AD_ITEM1] = mapCallBack2

        val mapCallBack3 = HashMap<String, Any>()
        mapCallBack3["clickCallBack"] = callBack3
        viewTypeCallBackMap[Constant.AD_ITEM2] = mapCallBack3
    }
}
