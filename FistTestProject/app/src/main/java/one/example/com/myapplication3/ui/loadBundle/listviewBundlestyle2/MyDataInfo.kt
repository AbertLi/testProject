package one.example.com.myapplication3.ui.loadBundle.listviewBundlestyle2

import one.example.com.data.BaseData

class MyDataInfo : BaseData{

    var title: String? = ""
    override fun getViewType(): Int {
        return viewType
    }

    override fun getId(): String {
        return id
    }

    fun setId(ids: String) {
        id = ids
    }

    fun setViewType(viewTypes: Int) {
        viewType = viewTypes
    }



}
