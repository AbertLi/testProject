package one.example.com.runtime.plugin;
import android.content.Context;
import one.example.com.runtime.plugin.pluginload.PluginClassLoader;

public class Loaders {
    private Context hostContext;
    private String mPath;
    private PluginInfo mInfo;
    private PluginClassLoader pluginClassLoader;

    public Loaders(Context hostContext, PluginInfo info) {
        this.hostContext = hostContext;
        this.mPath = info.getSavePluginPath();
        this.mInfo = info;
    }
}
