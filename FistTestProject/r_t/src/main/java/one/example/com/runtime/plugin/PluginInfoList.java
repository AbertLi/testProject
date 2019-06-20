package one.example.com.runtime.plugin;

import android.content.Context;

import org.json.JSONArray;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import one.example.com.runtime.host.HostInit;
import one.example.com.runtime.utils.Contant;
import one.example.com.runtime.utils.FileUtils;
import one.example.com.runtime.utils.Logs;

/**
 * 所有的插件数据
 * <p>
 * 没次进入应用的时候初始化读取数据的数据
 */
public class PluginInfoList {
    private static final String TAG = "PluginInfoList";
    public static final List<PluginInfo> pluginInfoList = new ArrayList<>();
    private static final ReentrantReadWriteLock lockRW = new ReentrantReadWriteLock();

    /**
     * 读取数据
     *
     * @param loadTofile
     * @return
     */
    public static synchronized List<PluginInfo> loadPluginListData(boolean loadTofile) {
        if (loadTofile) {
            pluginInfoList.clear();
            pluginInfoList.addAll(pares2bundleList());
        }
        return pluginInfoList;
    }

    /**
     * 写入数据
     *
     * 1,缓存去重,缓存写入，永久化写入
     * @param info
     */
    public static void savePluginInfo(PluginInfo info) {
        synchronized (pluginInfoList) {
            List<PluginInfo> list = loadPluginListData(false);
            for (PluginInfo infos : list) {
                if (infos != null && info != null && infos.isHas(info)) {
                    Logs.eprintln(TAG, "This data has already been written in the JSON data. ");
                    return;
                }
            }
            if (info != null) {
                list.add(info);
                pluginInfoList.clear();
                pluginInfoList.addAll(list);
                Context context = HostInit.getHostAppContext();
                File dir = context.getDir(Contant.plugin_save_path, 0);
                File file = new File(dir, Contant.plugin_info_json);
                JSONArray jsonArray = new JSONArray();
                for (PluginInfo info2 : list) {
                    try {
                        jsonArray.put(info2.obj2Json());
                    } catch (Exception e) {
                        e.printStackTrace();
                        Logs.eprintln(TAG, "The object turned json wrong.");
                    }
                }
                putData(file.getAbsolutePath(), jsonArray.toString());
            }
        }

    }


    private static List<PluginInfo> pares2bundleList() {
        Context context = HostInit.getHostAppContext();
        List<PluginInfo> list = null;
        File dir = context.getDir(Contant.plugin_save_path, 0);
        File file = new File(dir, Contant.plugin_info_json);
        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray(getData(file.getAbsolutePath()));
            list = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                PluginInfo info = new PluginInfo(jsonArray.getJSONObject(i));
                list.add(info);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Logs.eprintln(TAG, e.getMessage());
        }
        return list;
    }


    private static void putData(String filePath, String data) {
        lockRW.writeLock().lock();
        FileUtils.writeData(filePath, data);
        lockRW.writeLock().unlock();
    }

    private static String getData(String filePath) {
        lockRW.readLock().lock();
        try {
            return FileUtils.readData(filePath);
        } catch (Exception e) {
            return "{}";
        } finally {
            lockRW.readLock().unlock();
        }
    }
}
