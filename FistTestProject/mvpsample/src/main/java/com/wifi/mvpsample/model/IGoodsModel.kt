package com.wifi.mvpsample.model

import com.wifi.mvpsample.GoodsInfo

interface IGoodsModel {
    fun loadGoodsData(listener: OnLoadListener)


    interface OnLoadListener {
        fun onComplete(list: List<GoodsInfo>)
        fun onError(msg: String)
    }
}