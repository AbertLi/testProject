package com.book.logviewtool.mvvmunit.folder

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.book.logviewtool.base.BaseActivityVM
import com.book.logviewtool.interfaces.IBackListener
import com.book.logviewtool.mvvmunit.FolderAdapter
import com.example.logviewtool.BR
import com.example.logviewtool.R
import com.example.logviewtool.databinding.ActivityFolderBinding

class FolderActivity : BaseActivityVM<FolderViewModel>() {
    lateinit var binding: ActivityFolderBinding
    private var adapter = FolderAdapter(null, BR.folderBean)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_folder)
        binding.rv.adapter = adapter
        mViewModel.getFolderFileList("D:daljdljalgjalgjalgjaldgjaslgjlku40jartnbj文件夹的路径akpaggj")
        binding.refreshLayout.setOnRefreshListener {

        }
        binding.backListener = object : IBackListener {
            override fun back() {
                finish()
            }
        }
        initObserver()
    }

    private fun initObserver(){
        
    }
}