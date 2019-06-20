package com.albert.interfalib.plugin;

import android.content.Context;
import android.view.View;

public interface IPluginInterface {
    public IPluginViewManager inflate(Context hostContext,Context pluginContext);

    public void asseamble(View rootView,Object o);
}
