package com.book.logviewtool.mvvmunit.logdetail

import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.book.logviewtool.base.BaseActivityVM
import com.book.logviewtool.interfaces.IRefreshListener
import com.example.logviewtool.BR
import com.example.logviewtool.R
import com.example.logviewtool.databinding.ActivityLogDetailBinding

class LogDetailActivity : BaseActivityVM<LogDetailViewModel>() {
    private val TAG = "LogDetailActivity"
    lateinit var binding: ActivityLogDetailBinding
    private var logAdapter = LogDetailAdapter(null, BR.logItem)
    private var mKeyWords = ""
    private var filePath = ""
    private var startLine = 0

    private var isResponse = false//读取同一个文件的时候是否有回复结果

    override fun onCreateView() {
        filePath = intent.extras?.getString("path") ?: ""
        binding = DataBindingUtil.setContentView(this, R.layout.activity_log_detail)
        binding.rv.adapter = logAdapter
        binding.backListener = this
        binding.refreshListener = serchListener
        requestNewFile("")
        binding.rv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                //当前RecyclerView显示出来的最后一个的item的position
                var lastPosition = 0
                //当前状态为停止滑动状态SCROLL_STATE_IDLE时
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    when (val layoutManager: RecyclerView.LayoutManager? = recyclerView.layoutManager) {
                        is GridLayoutManager -> {
                            //通过LayoutManager找到当前显示的最后的item的position
                            lastPosition = layoutManager.findLastVisibleItemPosition()
                        }
                        is LinearLayoutManager -> {
                            lastPosition = layoutManager.findLastVisibleItemPosition()
                        }
                        is StaggeredGridLayoutManager -> {
                            //因为StaggeredGridLayoutManager的特殊性可能导致最后显示的item存在多个，所以这里取到的是一个数组
                            //得到这个数组后再取到数组中position值最大的那个就是最后显示的position值了
                            val lastPositions = IntArray(layoutManager.spanCount)
                            layoutManager.findLastVisibleItemPositions(lastPositions)
                            lastPosition = findMax(lastPositions)
                        }
                        //时判断界面显示的最后item的position是否等于itemCount总数-1也就是最后一个item的position
                        //如果相等则说明已经滑动到最后了
                    }

                    //时判断界面显示的最后item的position是否等于itemCount总数-1也就是最后一个item的position
                    //如果相等则说明已经滑动到最后了
                    if (lastPosition == (recyclerView.layoutManager?.itemCount ?: 0) - 1) {
                        Log.e(TAG, "已经滑到底部")
                        var requestBean = LogReadBean(false, startLine, filePath, mKeyWords, 100)
                        if (isResponse) {
                            mViewModel.getLogList(requestBean)
                            isResponse = false
                        } else {
                            Log.e(TAG, "读取日志暂时还没有返回结果。请稍等...")
                        }
                    }
                }
            }
        })
        initObserver()
    }

    //找到数组中的最大值
    private fun findMax(lastPositions: IntArray): Int {
        var max = lastPositions[0]
        for (value in lastPositions) {
            if (value > max) {
                max = value
            }
        }
        return max
    }

    private fun initObserver() {
        mViewModel.folderFileLiveData.observe(this, Observer {
            it?.let { bean ->
                logAdapter.setData(bean.list)
                startLine = bean.numberOfRowsRead.toIntOrNull() ?: 0
                isResponse = true
            }
        })

        mViewModel.folderFileLiveDataAdd.observe(this, Observer {
            it?.let { bean ->
                logAdapter.addData(bean.list)
                startLine = bean.numberOfRowsRead.toIntOrNull() ?: 0
                isResponse = true
            }
        })
    }

    private var serchListener = object : IRefreshListener {
        override fun refresh() {
            requestNewFile(binding.etTitle.text.toString())
        }
    }

    private fun requestNewFile(searchStr: String) {
        startLine = 0
        mKeyWords = searchStr
        var requestBean = LogReadBean(true, startLine, filePath, mKeyWords, 100)
        mViewModel.getLogList(requestBean)
    }
}