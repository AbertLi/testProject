package com.book.logviewtool.mvvmunit.folder;

import android.util.Log
import com.book.logviewtool.base.BaseRepository
import com.book.logviewtool.mvvmunit.FolderBean
import com.book.logviewtool.util.GetFilesUtils

class FolderRep : BaseRepository() {
    fun getFolderFileList(filePath: String, callBack: (ArrayList<FolderBean>) -> Unit) {
        var list = GetFilesUtils.getInstance().getBrotherNode(filePath)
        if (list == null) {
            Log.e("TAG", "$filePath This address file query is empty")
            callBack(ArrayList<FolderBean>())
        } else {
            var arrayList = ArrayList<FolderBean>()
            list.forEach { map ->
                var fileNum = map[GetFilesUtils.FILE_INFO_NUM_SONFILES] as Int
                var dirNum = map[GetFilesUtils.FILE_INFO_NUM_SONDIRS] as Int

                var bean = FolderBean("${map[GetFilesUtils.FILE_INFO_NAME]}",
                        "${map[GetFilesUtils.FILE_INFO_CREATE_TIME]}",
                        "${map[GetFilesUtils.FILE_INFO_PATH]}",
                        "${map[GetFilesUtils.FILE_INFO_TYPE]}",
                        fileNum,
                        dirNum
                )
                arrayList.add(bean)
            }
            callBack(arrayList)
        }
    }
}
