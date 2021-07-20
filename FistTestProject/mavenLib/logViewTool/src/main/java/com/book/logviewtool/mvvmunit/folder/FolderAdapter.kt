package com.book.logviewtool.mvvmunit.folder

import com.book.logviewtool.base.BaseAdapter
import com.book.logviewtool.interfaces.OnClickListener
import com.example.logviewtool.R
import com.example.logviewtool.databinding.DevToolFolderItemBinding

/**
 * 该适配器需要做内存容量限制
 * 采取DiskLruCache 的算法处理
 */
class FolderAdapter(itemList: ArrayList<FolderBean>? = null, brId: Int)
    : BaseAdapter<FolderBean, DevToolFolderItemBinding>(itemList, brId) {

    var mOnClickListener: OnClickListener? = null

    override fun getLayoutId(): Int {
        return R.layout.dev_tool_folder_item
    }

    fun setData(itemList: ArrayList<FolderBean>) {
        mItemList = itemList
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.clItemRootView.setOnClickListener { view ->
            mOnClickListener?.onClick(view, position)
        }
        holder.binding.clItemRootView.setOnLongClickListener {v->
            mOnClickListener?.onLongClick(v, position)
            true
        }
        super.onBindViewHolder(holder, position)
    }

    fun addData(itemList: ArrayList<FolderBean>) {
        if (mItemList == null) {
            mItemList = itemList
        } else {
            mItemList?.addAll(itemList)
        }
    }

    fun getItem(position: Int): FolderBean? {
        return mItemList?.get(position)
    }

    fun setOnclickListener(onClickListener: OnClickListener) {
        mOnClickListener = onClickListener
    }
}