package one.example.com.bundlethree;

import android.view.View;

import com.albert.interfalib.plugin.IPluginViewManager;
import com.albert.interfalib.plugin.IStatCallBack;

public class PluginViewManagerImple implements IPluginViewManager {
    private View rootView;

    public PluginViewManagerImple(View view) {
        rootView = view;
    }

    @Override
    public void updataTheme(int theme) {

    }

    @Override
    public View getRootView() {
        return rootView;
    }

    @Override
    public void setPluginStat(IStatCallBack callBack) {

    }
}
