package one.example.com.bundlethree;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.albert.interfalib.plugin.IPluginInterface;
import com.albert.interfalib.plugin.IPluginViewManager;

import one.example.com.myapplication3.R;

/**
 * 定制动态下发的样式 1
 * 大图 H5跳转
 */
public class AdViewItem implements IPluginInterface {
    private Context mHostContext;
    private Context mPluginContext;

    @Override
    public IPluginViewManager inflate(Context hostContext, Context pluginContext) {
        mHostContext = hostContext;
        mPluginContext = pluginContext;
        View root = LayoutInflater.from(pluginContext).inflate(R.layout.adview_three_item, null, false);
        return new PluginViewManagerImple(root);
    }

    @Override
    public void asseamble(View rootView, Object o) {

    }
}
