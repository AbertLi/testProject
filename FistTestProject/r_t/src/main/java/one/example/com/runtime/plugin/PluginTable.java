package one.example.com.runtime.plugin;

import android.content.Context;
import android.text.TextUtils;
import android.util.Pair;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import one.example.com.runtime.host.HostInit;
import one.example.com.runtime.utils.Logs;

/**
 * 存放Plugin对象
 */
public class PluginTable {
    private static final String TAG = "PluginTable";
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
            SoftReference<AdvPlugin> softReferencePlugin = getPlugin(info.getAdvPuginMapKey());
            advPlugin = softReferencePlugin.get();

            if (advPlugin == null) {
                advPlugin = buildAVDPlugin(context, info);
                if (advPlugin != null) {
                    putPlugin(info.getAdvPuginMapKey(), advPlugin);
                }
            }
            return advPlugin;
        }
    }


    /**
     * 构建Plugin对象
     * @return
     */
    public static List<PluginInfo> buidPlugins() {
        synchronized (advPluginMap) {
            Context context = HostInit.getHostAppContext();
            List<PluginInfo> list = PluginInfoList.loadPluginListData(true);
            if (list == null) {
                Logs.eprintln(TAG, "PluginList data is null.");
                return null;
            }
            for (PluginInfo info : list) {
                if (info == null) {
                    Logs.eprintln(TAG, "When creating Adv Plugin, PluginInfo is null");
                }
                else {
                    AdvPlugin advPlugin = buildAVDPlugin(context, info);
                    putPlugin(info.getAdvPuginMapKey(), advPlugin);
                }
            }
            return list;
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

    public static SoftReference<AdvPlugin> getPlugin(String key) {
        if (key == null) {
            Logs.eprintln("key is null");
            return null;
        }
        synchronized (advPluginMap) {
            return advPluginMap.get(key);
        }
    }

    /**
     * 通过包名查找最新版本的样式包。
     *
     * @param packagetID
     * @return
     */
    public static Pair<AdvPlugin, String> getAdvPluginMap(String packagetID) {
        synchronized (advPluginMap) {
            AdvPlugin advPlugin = null;
            String versionCode = null;
            if (!advPluginMap.isEmpty()) {
                for (String key : advPluginMap.keySet()) {
                    String[] strKey = splitKey(key);
                    if (strKey == null) {
                        continue;
                    }
                    else {
                        if (strKey[0].equals(packagetID)) {
                            if (isHigh(versionCode, strKey[1])) {
                                versionCode = strKey[1];
                                advPlugin = getPlugin(key).get();
                            }
                        }
                    }
                }
                if (advPlugin != null) {
                    return new Pair<>(advPlugin, null);
                }
                else {
                    return new Pair<>(null, "this plugin not install.");
                }
            }
            else {
                return new Pair<>(null, "PluginTable advPluginMap is null.");
            }
        }
    }


    /**
     * 创建插件对象
     *
     * @param context
     * @param info
     * @return
     */
    private static AdvPlugin buildAVDPlugin(Context context, PluginInfo info) {
        if (context == null || info == null) {
            Logs.eprintln(TAG, "Context or info is null.");
            return null;
        }
        if (!info.isHostVersion()) {
            Logs.eprintln(TAG, "The version number of host does not match the version number in the plugin.");
            return null;
        }
        AdvPlugin advPlugin = new AdvPlugin();
        Loaders loaders = new Loaders(context, info);
        if (!loaders.loadLoaders()){
            Logs.eprintln(TAG, "loaders load fail");
            return null;
        }
        advPlugin.setLoaders(loaders);
        advPlugin.setPluginInfo(info);
        return advPlugin;
    }


    /**
     * 版本比较
     *
     * @param versionCode1
     * @param versionCode2
     * @return
     */
    private static boolean isHigh(String versionCode1, String versionCode2) {
        if (versionCode2 == null) {
            Logs.eprintln(TAG, "key split versionCode is null!!!");
            return false;
        }
        if (versionCode1 == null) {
            return true;
        }
        else {
            int versionCodeOne = Integer.parseInt(versionCode1);
            int versionCodeTwo = Integer.parseInt(versionCode2);
            if (versionCodeOne < versionCodeTwo) {
                return true;
            }
            else {
                return false;
            }
        }
    }

    /**
     * key分割
     *
     * @param key
     * @return
     */
    private static String[] splitKey(String key) {
        if (!TextUtils.isEmpty(key)) {
            return key.split("_");
        }
        return null;
    }
}
