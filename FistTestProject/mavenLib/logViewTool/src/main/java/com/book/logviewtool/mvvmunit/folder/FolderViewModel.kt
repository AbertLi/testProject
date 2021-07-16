package com.book.logviewtool.mvvmunit.folder;

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.book.logviewtool.base.BaseViewModel
import com.book.logviewtool.mvvmunit.FolderBean
import kotlinx.coroutines.launch

class FolderViewModel : BaseViewModel<FolderRep>() {
    override fun initRepository(): FolderRep {
        return FolderRep()
    }

    var folderFileLiveData = MutableLiveData<ArrayList<FolderBean>>()
    var folderFileLiveDataAdd = MutableLiveData<ArrayList<FolderBean>>()

    fun getFolderFileList(filePath: String, isAdd: Boolean = false) {
        viewModelScope.launch {
            mRepository.getFolderFileList(filePath) {
                if (isAdd) {
                    folderFileLiveDataAdd.value = it
                } else {
                    folderFileLiveData.value = it
                }
            }
        }
    }

}
