package com.book.logviewtool.mvvmunit

import com.book.logviewtool.base.BaseAdapter
import com.example.logviewtool.R
import com.example.logviewtool.databinding.FolderItemBinding

/**
 * 该适配器需要做内存容量限制
 * 采取DiskLruCache 的算法处理
 */
class FolderAdapter(itemList: ArrayList<FolderBean>? = null, brId: Int)
    : BaseAdapter<FolderBean, FolderItemBinding>(itemList, brId) {

    override fun getLayoutId(): Int {
        return R.layout.folder_item
    }

    fun setData(itemList: ArrayList<FolderBean>) {
        mItemList = itemList
    }

    fun addData(itemList: ArrayList<FolderBean>) {
        if (mItemList == null) {
            mItemList = itemList
        } else {
            mItemList?.addAll(itemList)
        }
    }
}