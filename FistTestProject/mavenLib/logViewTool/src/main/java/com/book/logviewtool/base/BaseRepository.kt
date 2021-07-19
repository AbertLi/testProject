package com.book.logviewtool.base

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


open class BaseRepository {
    suspend fun async(callBack: () -> Unit) = withContext(Dispatchers.Default) {
        callBack()
    }
}