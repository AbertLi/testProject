package com.wifi.mvpsample.presenter

import com.wifi.mvpsample.view.IGoodView
import java.lang.ref.WeakReference

open class BasePresenter<T : IGoodView>(var view: T) {
    //持有View引用
    var mGoosViewWeakRef: WeakReference<IGoodView>? = null
    fun attachView() {
        mGoosViewWeakRef = WeakReference<IGoodView>(view)
    }

    fun detachView() {
        mGoosViewWeakRef?.clear()
        mGoosViewWeakRef = null
    }
}