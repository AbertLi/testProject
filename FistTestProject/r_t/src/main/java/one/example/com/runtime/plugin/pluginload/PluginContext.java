package one.example.com.runtime.plugin.pluginload;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.view.ContextThemeWrapper;

public class PluginContext extends ContextThemeWrapper {
    private ClassLoader classLoader;
    private String pluginPackageName;
    private Resources resources;


    public PluginContext(Context base, int theme, ClassLoader classLoader, String pluginPakName,
            Resources resources) {
        super(base, theme);
        this.classLoader = classLoader;
        this.pluginPackageName = pluginPakName;
        this.resources = resources;
    }


    @Override
    public AssetManager getAssets() {
        if (resources != null) {
            return resources.getAssets();
        }
        return super.getAssets();
    }

    @Override
    public ClassLoader getClassLoader() {
        if (classLoader != null) {
            return classLoader;
        }
        return classLoader;
    }

    @Override
    public Resources getResources() {
        if (resources != null) {
            return resources;
        }
        return resources;
    }

    @Override
    public String getPackageName() {
        if (pluginPackageName != null) {
            return pluginPackageName;
        }
        return super.getPackageName();
    }
}
