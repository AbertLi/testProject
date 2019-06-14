package one.example.com.runtime.plugin;

import android.content.Context;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

import one.example.com.runtime.utils.Logs;

/**
 * 存放Plugin对象
 *
 */
public class PluginTable {
    private static Map<String, SoftReference<AdvPlugin>> advPluginMap = new HashMap<>();

    /**
     * 通过Plugin 信息获取 plugin对象
     *
     * @param info
     * @return AdvPlugin
     */
    public static AdvPlugin getADVPluginByPluginInfo(Context context, PluginInfo info) {
        synchronized (advPluginMap) {
            AdvPlugin advPlugin = null;
            SoftReference<AdvPlugin> softReferencePlugin = advPluginMap.get(info.getAdvPuginMapKey());
            advPlugin = softReferencePlugin.get();

            if (advPlugin == null) {
                advPlugin = PluginInfoList.buildAVDPlugin(context,info);
                if (advPlugin != null) {
                    putPlugin(info.getAdvPuginMapKey(), advPlugin);
                }
            }
            return advPlugin;
        }
    }


    public static void putPlugin(String key, AdvPlugin plugin) {
        if (plugin == null) {
            Logs.eprintln("PluginTabe put plugin is null");
            return;
        }
        synchronized (advPluginMap) {
            advPluginMap.put(key, new SoftReference<>(plugin));
        }
    }
}
