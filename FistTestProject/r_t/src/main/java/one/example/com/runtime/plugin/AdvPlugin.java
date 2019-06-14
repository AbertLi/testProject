package one.example.com.runtime.plugin;

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


    public PluginInterface getPluginInterface(){
        
    }
}
