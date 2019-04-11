package one.example.com.recyclerview;

import android.view.View;

import one.example.com.data.BaseData;

public abstract class ClickCallBack {
    public abstract void startPlay(View v, int position, BaseData datainfo);

    public abstract void downLoad(View v, int position, BaseData datainfo);

    public abstract void skip(View v, int position, BaseData datainfo);
}
