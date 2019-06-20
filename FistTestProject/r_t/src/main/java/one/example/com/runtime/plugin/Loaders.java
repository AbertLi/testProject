package one.example.com.runtime.plugin;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;

import one.example.com.runtime.plugin.pluginload.PluginClassLoader;
import one.example.com.runtime.plugin.pluginload.PluginContext;
import one.example.com.runtime.utils.Logs;

public class Loaders {
    private static final String TAG = "Loaders";
    private Context hostContext;
    private String mPath;
    private PluginClassLoader pluginClassLoader;
    private Resources resources;
    private PluginContext pluginContext;
    private PackageInfo packageInfo;

    public Loaders(Context hostContext, PluginInfo info) {
        this.hostContext = hostContext;
        this.mPath = info.getSavePluginPath();
    }

    public boolean loadLoaders() {
        try {
            packageInfo = hostContext.getPackageManager().getPackageArchiveInfo(mPath, PackageManager.GET_META_DATA);

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }





    private void computTime(Runnable runnable, String name) {
        if (runnable == null) {
            return;
        }
        long startTime = System.currentTimeMillis();
        runnable.run();
        long timeLo = System.currentTimeMillis() - startTime;
        Logs.iprintln(TAG, name + " user time = " + timeLo + "ms");
    }

    public interface Runnable {
        public void run();
    }
}
