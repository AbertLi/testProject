package com.book.logviewtool.mvvmunit.folder

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.book.logviewtool.base.BaseActivityVM
import com.book.logviewtool.interfaces.IBackListener
import com.book.logviewtool.interfaces.OnClickListener
import com.book.logviewtool.mvvmunit.logdetail.LogDetailActivity
import com.book.logviewtool.util.GetFilesUtils
import com.example.logviewtool.BR
import com.example.logviewtool.R
import com.example.logviewtool.databinding.ActivityFolderBinding

class FolderActivity : BaseActivityVM<FolderViewModel>() {
    lateinit var binding: ActivityFolderBinding
    private var adapter = FolderAdapter(null, BR.folderBean)
    private var path = ""
    override fun onCreateView() {
        path = intent.extras?.getString("path") ?: ""
        if (TextUtils.isEmpty(path)) {
            path = externalCacheDir?.absolutePath ?: ""
        }
        binding = DataBindingUtil.setContentView(this, R.layout.activity_folder)
        binding.rv.adapter = adapter
        mViewModel.getFolderFileList(path)
        binding.refreshLayout.setOnRefreshListener {
            mViewModel.getFolderFileList(path)
            binding.refreshLayout.finishRefresh(10000, false, false)
        }
        binding.backListener = object : IBackListener {
            override fun back() {
                finish()
            }
        }
        adapter.setOnclickListener(object : OnClickListener {
            override fun onClick(v: View?, position: Int) {
                var itemBean = adapter.getItem(position)
                itemBean?.let { folderBean ->
                    if (folderBean.fileInfoType == GetFilesUtils.FILE_TYPE_FOLDER) {
                        var bundle = Bundle()
                        bundle.putString("path", folderBean.fileInfoPath)
                        jumpToActivity(FolderActivity::class.java, bundle)
                    } else {
                        var bundle = Bundle()
                        bundle.putString("path", folderBean.fileInfoPath)
                        jumpToActivity(LogDetailActivity::class.java, bundle)
                    }
                }

            }
        })
        initObserver()
    }

    private fun initObserver() {
        mViewModel.folderFileLiveData.observe(this, Observer {
            it?.let { arrayList ->
                adapter.setData(arrayList)
                if (binding.refreshLayout.isRefreshing) {
                    binding.refreshLayout.finishRefresh(true)
                }
            }
        })
    }
}