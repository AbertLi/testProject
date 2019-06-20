package com.albert.interfalib.plugin;

import android.view.View;

/**
 * 样式View的操作管理器
 */
public interface IPluginViewManager {

    public void updataTheme(int theme);

    public View getRootView();

    public void setPluginStat(IStatCallBack callBack);
}
