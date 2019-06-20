package one.example.com.runtime.plugin;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Build;

import com.albert.interfalib.plugin.IPluginInterface;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import one.example.com.runtime.plugin.pluginload.PluginClassLoader;
import one.example.com.runtime.plugin.pluginload.PluginContext;
import one.example.com.runtime.utils.Contant;
import one.example.com.runtime.utils.Logs;

public class Loaders {
    private static final String TAG = "Loaders";
    private Context hostContext;
    private String mPath;
    private PluginClassLoader pluginClassLoader;
    private Resources resources;
    private PluginContext pluginContext;
    private PackageInfo packageInfo;
    private IPluginInterface pluginInterface;

    public Loaders(Context hostContext, PluginInfo info) {
        this.hostContext = hostContext;
        this.mPath = info.getSavePluginPath();
    }

    public boolean loadLoaders() {
        try {
            final PackageManager packageManager = hostContext.getPackageManager();
            packageInfo = packageManager.getPackageArchiveInfo(mPath, PackageManager.GET_META_DATA);
            packageInfo.applicationInfo.sourceDir = mPath;
            packageInfo.applicationInfo.publicSourceDir = mPath;

            computTime(new Runnable() {
                @Override
                public void run() throws PackageManager.NameNotFoundException {
                    ClassLoader parent = getClass().getClassLoader().getParent();
                    pluginClassLoader = new PluginClassLoader(mPath, getDir(hostContext).getPath(), "", parent,
                            hostContext.getClassLoader());
                }
            }, "creat pluginClassLoader");

            computTime(new Runnable() {
                @Override
                public void run() throws PackageManager.NameNotFoundException {
                    resources = packageManager.getResourcesForApplication(packageInfo.applicationInfo);
                }
            }, "creat resources");

            computTime(new Runnable() {
                @Override
                public void run() throws PackageManager.NameNotFoundException {
                    pluginContext = new PluginContext(hostContext, android.R.style.Theme,
                            pluginClassLoader, packageInfo.packageName, resources);
                }
            }, "creat pluginContext");


            computTime(new Runnable() {
                @Override
                public void run() throws PackageManager.NameNotFoundException {
                    pluginInterface = getPluginINterfaceImple(pluginClassLoader);
                }
            }, "creat PluginInterface");

        } catch (Exception e) {
            Logs.eprintln(TAG, "Exception e.getMSG() = " + e.getMessage());
            e.printStackTrace();
            return false;
        }
        return true;
    }


    public IPluginInterface getPluginInterface() {
        return pluginInterface;
    }


    public PluginContext getPluginContent() {
        return pluginContext;
    }


    /**************************************************************************************************/

    private IPluginInterface getPluginINterfaceImple(PluginClassLoader classLoader) {
        String className = getPluginName(pluginContext.getPackageManager());
        try {
            Class cla = classLoader.loadClass(className);
            Constructor constructor = cla.getDeclaredConstructor();
            return (IPluginInterface) constructor.newInstance();
        } catch (ClassNotFoundException e) {
            Logs.eprintln(TAG, "getPluginINterfaceImple ClassNotFoundException e.getMsg =" + e.getMessage());
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            Logs.eprintln(TAG, "getPluginINterfaceImple NoSuchMethodException e.getMsg =" + e.getMessage());
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            Logs.eprintln(TAG, "getPluginINterfaceImple IllegalAccessException e.getMsg =" + e.getMessage());
            e.printStackTrace();
        } catch (InstantiationException e) {
            Logs.eprintln(TAG, "getPluginINterfaceImple InstantiationException e.getMsg =" + e.getMessage());
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            Logs.eprintln(TAG, "getPluginINterfaceImple InvocationTargetException e.getMsg =" + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取插件接口类名
     *
     * @param packageManager
     * @return
     */
    private String getPluginName(PackageManager packageManager) {
        try {
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(packageInfo.packageName,
                    PackageManager.GET_META_DATA);
            return applicationInfo.metaData.getString(Contant.plugin_class_name);
        } catch (PackageManager.NameNotFoundException e) {
            Logs.eprintln(TAG, "getPluginName e.getMsg =" + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    private File getDir(Context hostContext) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.N_MR1) {
            return new File(hostContext.getDir(Contant.plugin_save_path, 0).getAbsolutePath() + File.separator + "oat" +
                    File.separator +
                    "arnarnaarn");
        }
        else {
            return hostContext.getDir(Contant.plugin_save_path, 0);
        }
    }

    private void computTime(Runnable runnable, String name) throws Exception {
        if (runnable == null) {
            return;
        }
        long startTime = System.currentTimeMillis();
        runnable.run();
        long timeLo = System.currentTimeMillis() - startTime;
        Logs.iprintln(TAG, name + " user time = " + timeLo + "ms");
    }

    private interface Runnable {
        public void run() throws Exception;
    }
}
