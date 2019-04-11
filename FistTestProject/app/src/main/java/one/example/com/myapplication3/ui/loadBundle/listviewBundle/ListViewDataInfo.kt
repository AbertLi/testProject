package one.example.com.myapplication3.ui.loadBundle.listviewBundle

import one.example.com.data.BaseData

class ListViewDataInfo : BaseData {
    override fun getId(): String? {
        return id
    }

    fun setId(id: String) {
        this.id = id
    }

    fun setViewType(viewType: Int) {
        this.viewType = viewType
    }

    override fun getViewType(): Int {
        return 0
    }

    private var id: String? = null
    private var viewType: Int = 0

    var title: String? = null
    var constent: String? = null


    constructor(title: String?, constent: String?) {
        this.title = title
        this.constent = constent
    }

    companion object {
        fun getData(): List<ListViewDataInfo> {
            var list = ArrayList<ListViewDataInfo>()
            for (i: Int in 0..100) {
                var viewIteminfo = ListViewDataInfo("静夜思 " + i, "床前明月光，疑是地上霜，举头望明月，低头思故乡。")
                viewIteminfo.viewType = ViewHolderManager.DEFULE_ITEM
                list.add(viewIteminfo)
            }
            return list
        }
    }
}