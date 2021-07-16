package com.book.logviewtool.base

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * 两个泛型，T代表item的model类型即itemList的bean类型，TBinding代表itemLayout对应生成的XXXBinding类，
 * 比如itemLayout是fruit_item.xml，对应的就是FruitItemBinding
 * 两个参数itemList对应具体要显示的arrayList，brId则对应itemLayout里相应variable的变量名，
 *
 * 比如variable的name是fruit，brId就是BR.fruit
 */
abstract class BaseAdapter<T, TBinding : ViewDataBinding>(
    itemList: ArrayList<T>?,
    private val brId: Int
) : RecyclerView.Adapter<BaseAdapter<T, TBinding>.ViewHolder>() {

    protected var mContext: Context? = null
    protected var mItemList: ArrayList<T>? = itemList


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        //相当于itemView
        lateinit var binding: TBinding
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        if (mContext == null) {
            mContext = parent.context
        }
        //因为TBinding的具体类型不确定，这里只能用DataBindingUtil.inflate()，而不能用XXXBinding.inflate()
        val binding: TBinding =
            DataBindingUtil.inflate(LayoutInflater.from(mContext), getLayoutId(), parent, false)
        val holder = ViewHolder(binding.root)
        holder.binding = binding
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.setVariable(brId, mItemList?.get(position))
        //立即执行绑定，在对view变化时效敏感的地方常用，不加这句有可能出现itemView更新滞后、闪烁等问题
        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int {
        return mItemList?.size ?: 0
    }

    //获取具体子adapter对应的itemLayoutId
    abstract fun getLayoutId(): Int
}
