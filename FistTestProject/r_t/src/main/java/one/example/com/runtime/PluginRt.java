package one.example.com.runtime;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.util.Pair;

import java.util.List;

import one.example.com.runtime.host.HostInit;
import one.example.com.runtime.plugin.AdvPlugin;
import one.example.com.runtime.plugin.Loaders;
import one.example.com.runtime.plugin.PluginInfo;
import one.example.com.runtime.plugin.PluginInfoList;
import one.example.com.runtime.plugin.PluginTable;
import one.example.com.runtime.utils.FileUtils;
import one.example.com.runtime.utils.Logs;

/**
 * 中间件入口
 * <p>
 * （暂时不做签名验证）
 * <p>
 * 1，写入中间件或者文件到一半的时候突然关闭应用汇怎样。
 */
public class PluginRt {
    private static final String TAG = "PluginRt";

    /**
     * 获取下发的bundleInfo列表
     * 执行逻辑
     * 1，查询是否安装，赛选除未安装/需要更新的bundle信息，并将（未安装/需要更）信息持久化。
     * 2，对那些未安装/需要更新的bundle，进行下载和安装操作。
     * 3，执行完成更新状态信息。
     *
     * @param infosIn
     */
    public static synchronized void savePLuginToInfos(List<PluginInfo> infosIn) {
        if (infosIn == null || infosIn.isEmpty()) {
            Logs.eprintln(TAG, "pluginInfo is null or size is 0");
            return;
        }
    }

    /**
     * 通过包名获取最新版本的样式包对象
     *
     * @return
     */
    public static AdvPlugin getBundleByPackageId(String packageId) {
        return null;
    }

    /**
     * 判断这个插件是否安装...（暂时不做签名验证）
     *
     * @param pluginName
     * @return
     */
    public static boolean isPluginInstalled(String pluginName) {
        return false;
    }

    /**
     * 安装这个插件，并且返回插件相关的信息
     *
     * @param pluginPath 插件地址
     * @return 插件信息, 错误信息
     */
    public static Pair<AdvPlugin, String> install(String pluginPath) {
        //地址是否为空
        if (TextUtils.isEmpty(pluginPath)) {
            return new Pair<>(null, "pluginPath is null");
        }
        //判断地址是否是文件地址
        if (!FileUtils.isCreate(pluginPath)) {
            return new Pair<>(null, "The pluginPath path does not exist.");
        }

        //packageInfo判断
        Context context = HostInit.getHostAppContext();
        PackageInfo packageInfo =
                context.getPackageManager().getPackageArchiveInfo(pluginPath, PackageManager.GET_META_DATA);
        if (packageInfo == null) {
            return new Pair<>(null, "packageInfo is null");
        }
        PluginInfo infos = PluginInfo.parsePluginInfo2PluginInfo(packageInfo);
        if (!infos.islegal()) {
            return new Pair<>(null, "This pluginInfo is not legal");
        }
        if (!infos.isHostVersion()) {
            return new Pair<>(null, "Host version number and this plugin host version number are inconsistent ");
        }


        PluginInfo installedInfo = getInstallPluginInfo(infos);
        //检查插件是否有安装。
        if (installedInfo != null) {
            AdvPlugin advPlugin = PluginTable.getADVPluginByPluginInfo(context, installedInfo);
            return new Pair<>(advPlugin, null);
        }
        else {
            //执行安装
            Loaders loaders = new Loaders(context, infos);
            loaders.loadLoaders();
            AdvPlugin pluginS = new AdvPlugin();
            pluginS.setLoaders(loaders);
            pluginS.setPluginInfo(infos);
            PluginTable.putPlugin(infos.getAdvPuginMapKey(), pluginS);
            PluginInfoList.savePluginInfo(infos);
            return new Pair<>(pluginS, null);
        }
    }


    /**
     * 通过包名查询插件对象
     *
     * @param packageId
     * @return
     */
    private static Pair<AdvPlugin, String> getAdvPlugin(String packageId) {
        return PluginTable.getAdvPluginMap(packageId);
    }


    /**
     * 启动插件
     *
     * @param context
     * @param intent
     * @return
     */
    public static boolean startActivity(Context context, Intent intent) {
        return false;
    }


    /**
     * 查询相同包名和版本号的插件信息
     *
     * @return
     */
    private static PluginInfo getInstallPluginInfo(PluginInfo infos) {
        List<PluginInfo> list = PluginInfoList.loadPluginListData(false);
        if (list == null) {
            return null;
        }
        else {
            for (PluginInfo info : list) {
                if (info == null) {
                    continue;
                }
                else {
                    if (info.isHas(infos)) {
                        return info;
                    }
                }
            }
        }
        return null;
    }
}
