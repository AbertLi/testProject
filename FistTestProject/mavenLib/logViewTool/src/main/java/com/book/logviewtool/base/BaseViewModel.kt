package com.book.logviewtool.base
import androidx.lifecycle.ViewModel

open abstract class BaseViewModel<R : BaseRepository> : ViewModel() {

    protected val mRepository: R by lazy { initRepository() }

    protected abstract fun initRepository(): R

}