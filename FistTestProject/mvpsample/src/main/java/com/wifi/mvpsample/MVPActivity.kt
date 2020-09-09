package com.wifi.mvpsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.widget.ListView
import com.wifi.mvpsample.presenter.GoodsPresenter
import com.wifi.mvpsample.view.IBaseView
import com.wifi.mvpsample.view.IGoodView
import kotlinx.android.synthetic.main.activity_m_v_p.*

class MVPActivity() : AppCompatActivity(), IGoodView {
    lateinit var mPresenter: GoodsPresenter<IGoodView>
    lateinit var mListView: ListView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_m_v_p)
        mPresenter = GoodsPresenter(this)
        mListView = findViewById<ListView>(R.id.listview)
        mPresenter.fetch()
    }

    override fun showGoodsView(list: List<GoodsInfo>) {
        mListView.adapter = GoodsAdapter(this,list)
    }

    override fun showErrorMassage(msg: String) {
        TODO("Not yet implemented")
    }

}
