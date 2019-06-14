package one.example.com.runtime.plugin;

import android.content.Context;

import one.example.com.runtime.utils.Logs;

/**
 * 所有的插件数据
 * <p>
 * 没次进入应用的时候初始化读取数据的数据
 */
public class PluginInfoList {
    private static final String TAG = "PluginInfoList";

    public static AdvPlugin buildAVDPlugin(Context context, PluginInfo info) {
        if (context == null || info == null) {
            Logs.eprintln(TAG, "Context or info is null.");
            return null;
        }
        if (!info.isHostVersion()) {
            Logs.eprintln(TAG, "The version number of host does not match the version number in the plugin.");
            return null;
        }
        AdvPlugin advPlugin = new AdvPlugin();
        Loaders loaders = new Loaders(context,info);
        advPlugin.setLoaders(loaders);
        advPlugin.setPluginInfo(info);
        return advPlugin;
    }
}
