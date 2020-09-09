package com.wifi.mvpsample.view

import com.wifi.mvpsample.GoodsInfo

/**
 * UI逻辑
 */
interface IGoodView : IBaseView {
    //显示图片和文字
    fun showGoodsView(list: List<GoodsInfo>)
    //加载进度条
    //加载动画
}