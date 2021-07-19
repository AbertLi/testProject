package com.book.logviewtool.mvvmunit.folder;

import android.util.Log
import com.book.logviewtool.base.BaseRepository
import com.book.logviewtool.util.GetFilesUtils

class FolderRep : BaseRepository() {

    suspend fun getFolderFileList(filePath: String, callBack: (ArrayList<FolderBean>) -> Unit) {
        async {
            var list = GetFilesUtils.getInstance().getSonNode(filePath)
            if (list == null) {
                Log.e("TAG", "$filePath This address file query is empty")
                callBack(ArrayList<FolderBean>())
            } else {
                var arrayList = ArrayList<FolderBean>()
                list.forEach { map ->
                    var bean = FolderBean("${map[GetFilesUtils.FILE_INFO_NAME]}",
                            "${map[GetFilesUtils.FILE_INFO_CREATE_TIME]}",
                            "${map[GetFilesUtils.FILE_INFO_PATH]}",
                            "${map[GetFilesUtils.FILE_INFO_TYPE]}",
                            "${map[GetFilesUtils.FILE_INFO_NUM_SONFILES]}",
                            "${map[GetFilesUtils.FILE_INFO_NUM_SONDIRS]}",
                            "${map[GetFilesUtils.FILE_INFO_SIZE]}"
                    )
                    arrayList.add(bean)
                }
                callBack(arrayList)
            }
        }
    }
}
