package com.book.logviewtool.mvvmunit.logdetail;

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.book.logviewtool.base.BaseViewModel
import com.book.logviewtool.util.HandlerUtils
import kotlinx.coroutines.launch

class LogDetailViewModel : BaseViewModel<LogDetailRep>() {
    override fun initRepository(): LogDetailRep {
        return LogDetailRep()
    }

    var folderFileLiveData = MutableLiveData<LogsRetrievedOnceBean>()
    var folderFileLiveDataAdd = MutableLiveData<LogsRetrievedOnceBean>()

    fun getLogList(requestBean: LogReadBean) {
        viewModelScope.launch {
            mRepository.getLogList(requestBean) {
                HandlerUtils.postDelayed(0) {
                    if (requestBean.isNewRequest) {
                        folderFileLiveData.value = it
                    } else {
                        folderFileLiveDataAdd.value = it
                    }
                }
            }
        }
    }

}
