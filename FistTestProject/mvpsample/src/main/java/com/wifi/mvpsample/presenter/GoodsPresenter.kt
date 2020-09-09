package com.wifi.mvpsample.presenter

import com.wifi.mvpsample.GoodsInfo
import com.wifi.mvpsample.model.GoodsModel
import com.wifi.mvpsample.model.IGoodsModel
import com.wifi.mvpsample.view.IGoodView

class GoodsPresenter<T : IGoodView>(goosView: T) {
    //持有View引用
    var mGoosView: IGoodView = goosView

    //持有Model引用
    var mGoodsModel: IGoodsModel = GoodsModel()

    fun fetch() {
        if (mGoodsModel != null && mGoosView != null) {
            mGoodsModel.loadGoodsData(object : IGoodsModel.OnLoadListener {
                override fun onComplete(list: List<GoodsInfo>) {
                    mGoosView.showGoodsView(list)
                }

                override fun onError(msg: String) {

                }
            })
        }
    }
}