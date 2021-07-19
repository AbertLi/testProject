package com.book.logviewtool.mvvmunit.logdetail;

import com.book.logviewtool.base.BaseRepository
import com.book.logviewtool.util.GetLogFileDetailUtils

class LogDetailRep : BaseRepository() {
    suspend fun getLogList(requestBean: LogReadBean, callBack: (LogsRetrievedOnceBean) -> Unit) {
        async {//协程异步
            callBack(GetLogFileDetailUtils.getInstance().getLogList(requestBean))
        }
    }
}
