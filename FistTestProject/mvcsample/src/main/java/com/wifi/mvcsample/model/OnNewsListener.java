package com.wifi.mvcsample.model;

import com.wifi.mvcsample.bean.News;

public interface OnNewsListener {
    void onSuccess(News news);
    void onError();
}