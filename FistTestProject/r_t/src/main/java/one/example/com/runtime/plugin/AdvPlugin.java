package one.example.com.runtime.plugin;

import android.content.Context;

import com.albert.interfalib.plugin.IPluginInterface;

public class AdvPlugin {
    private Loaders loaders;
    private PluginInfo pluginInfo;

    public Loaders getLoaders() {
        return loaders;
    }

    public void setLoaders(Loaders loaders) {
        this.loaders = loaders;
    }

    public PluginInfo getPluginInfo() {
        return pluginInfo;
    }

    public void setPluginInfo(PluginInfo pluginInfo) {
        this.pluginInfo = pluginInfo;
    }


    public Context getPluginContext() {
        if (loaders != null) {
            return loaders.getPluginContent();
        }
        return null;
    }

    public IPluginInterface getPluginInterface(){
        if (loaders != null) {
            return loaders.getPluginInterface();
        }
        return null;
    }
}
