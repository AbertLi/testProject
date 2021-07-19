package com.book.logviewtool.mvvmunit.logdetail

import com.book.logviewtool.base.BaseAdapter
import com.book.logviewtool.interfaces.OnClickListener
import com.example.logviewtool.R
import com.example.logviewtool.databinding.LogItemBinding

/**
 * 该适配器需要做内存容量限制
 * 采取DiskLruCache 的算法处理
 */
class LogDetailAdapter(itemList: ArrayList<String>? = null, brId: Int)
    : BaseAdapter<String, LogItemBinding>(itemList, brId) {

    var mOnClickListener: OnClickListener? = null

    override fun getLayoutId(): Int {
        return R.layout.log_item
    }

    fun addData(itemList: ArrayList<String>) {
        if (mItemList == null) {
            mItemList = itemList
        } else {
            mItemList?.addAll(itemList)
        }
        notifyDataSetChanged()
    }

    fun setData(itemList: ArrayList<String>) {
        mItemList = itemList
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.binding.clItemRootView.setOnClickListener { view ->
//            mOnClickListener?.onClick(view, position)
//        }
        super.onBindViewHolder(holder, position)
    }

    fun getItem(position: Int): String? {
        return mItemList?.get(position)
    }

    fun setOnclickListener(onClickListener: OnClickListener) {
        mOnClickListener = onClickListener
    }
}