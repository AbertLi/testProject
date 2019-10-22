package one.example.com.runtime.host;

import android.app.Application;
import android.content.Context;

import java.util.List;

import one.example.com.runtime.plugin.PluginInfo;
import one.example.com.runtime.plugin.PluginStateUtil;
import one.example.com.runtime.plugin.PluginTable;
import one.example.com.runtime.utils.AppExecutors;
import one.example.com.runtime.utils.Logs;

public class HostInit {
    public static String TAG = "HostInit";
    private static Context mHostAppContext;

    public static void setHostAppContext(Application hostAppContext) {
        mHostAppContext = hostAppContext;
    }

    public static Context getHostAppContext() {
        return mHostAppContext;
    }

    public static void attachBeseContext(Application application) {
        setHostAppContext(application);
        AppExecutors.runOnBackground(new Runnable() {
            @Override
            public void run() {
                Logs.iprintln(TAG, "Plugin Host init");
                PluginStateUtil.cleanAll();
                List<PluginInfo> list = PluginTable.buidPlugins();
                print(list);
            }
        });
    }

    private static void print(List<PluginInfo> list) {
        if (list == null) {
            Logs.iprintln(TAG, "PluginList is null");
            return;
        }
        for (PluginInfo infos : list) {
            if (infos != null) {
                Logs.iprintln(TAG, "PluginInfo toString = " + infos.toString());
            }
            else {
                Logs.iprintln(TAG, "PluginInfo == null");
            }
        }
    }
}
