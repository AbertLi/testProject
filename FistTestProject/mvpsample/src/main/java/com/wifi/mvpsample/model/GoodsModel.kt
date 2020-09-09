package com.wifi.mvpsample.model

import com.wifi.mvpsample.GoodsInfo
import com.wifi.mvpsample.R

class GoodsModel : IGoodsModel {
    override fun loadGoodsData(listener: IGoodsModel.OnLoadListener) {
        listener.onComplete(getData())
    }

    private fun getData(): List<GoodsInfo> {
        var listData = ArrayList<GoodsInfo>()
        listData.add(GoodsInfo(R.mipmap.s1, "1星", "她是一个不平凡的女孩"))
        listData.add(GoodsInfo(R.mipmap.s2, "1星", "她是一个不平凡的女孩"))
        listData.add(GoodsInfo(R.mipmap.s3, "1星", "她是一个不平凡的女孩"))
        listData.add(GoodsInfo(R.mipmap.s4, "1星", "她是一个不平凡的女孩"))
        listData.add(GoodsInfo(R.mipmap.s5, "1星", "她是一个不平凡的女孩"))
        listData.add(GoodsInfo(R.mipmap.s6, "1星", "她是一个不平凡的女孩"))
        listData.add(GoodsInfo(R.mipmap.s7, "1星", "她是一个不平凡的女孩"))
        listData.add(GoodsInfo(R.mipmap.s8, "1星", "她是一个不平凡的女孩"))
        listData.add(GoodsInfo(R.mipmap.s9, "1星", "她是一个不平凡的女孩"))
        listData.add(GoodsInfo(R.mipmap.s10, "1星", "她是一个不平凡的女孩"))
        listData.add(GoodsInfo(R.mipmap.s11, "1星", "她是一个不平凡的女孩"))
        listData.add(GoodsInfo(R.mipmap.s12, "1星", "她是一个不平凡的女孩"))
        listData.add(GoodsInfo(R.mipmap.s13, "1星", "她是一个不平凡的女孩"))
        listData.add(GoodsInfo(R.mipmap.s14, "1星", "她是一个不平凡的女孩"))
        listData.add(GoodsInfo(R.mipmap.s15, "1星", "她是一个不平凡的女孩"))
        return listData
    }
}