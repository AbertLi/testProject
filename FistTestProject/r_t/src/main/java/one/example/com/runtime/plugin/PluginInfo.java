package one.example.com.runtime.plugin;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.text.TextUtils;

import java.io.File;
import java.io.Serializable;

import one.example.com.runtime.host.HostInit;
import one.example.com.runtime.utils.Contant;

/**
 * 插件信息
 */
public class PluginInfo implements Serializable {
    private String packageId;           //插件包名，也是唯一id
    private String PluginName;          //插件名字
    private String adapterActivityName; //适配页面名字
    private String versionCode;         //插件版本号
    private String hostVersionCode;     //host版本号。
    private String fingerprint;         //插件指纹信息。

    private String downLoadUrl;         //插件下载信息
    private boolean isOffMarket;        //是否下架
    private boolean isAutoInstall;      //是否自动安装

    public String getPackageId() {
        return packageId;
    }

    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }

    public String getPluginName() {
        return PluginName;
    }

    public void setPluginName(String pluginName) {
        PluginName = pluginName;
    }

    public String getAdapterActivityName() {
        return adapterActivityName;
    }

    public void setAdapterActivityName(String adapterActivityName) {
        this.adapterActivityName = adapterActivityName;
    }

    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    public String getHostVersionCode() {
        return hostVersionCode;
    }

    public void setHostVersionCode(String hostVersionCode) {
        this.hostVersionCode = hostVersionCode;
    }

    public String getFingerprint() {
        return fingerprint;
    }

    public void setFingerprint(String fingerprint) {
        this.fingerprint = fingerprint;
    }

    public String getDownLoadUrl() {
        return downLoadUrl;
    }

    public void setDownLoadUrl(String downLoadUrl) {
        this.downLoadUrl = downLoadUrl;
    }

    public boolean isOffMarket() {
        return isOffMarket;
    }

    public void setOffMarket(boolean offMarket) {
        isOffMarket = offMarket;
    }

    public boolean isAutoInstall() {
        return isAutoInstall;
    }

    public void setAutoInstall(boolean autoInstall) {
        isAutoInstall = autoInstall;
    }

    @Override
    public String toString() {
        return "PluginInfo{" +
                "packageId='" + packageId + '\'' +
                ", PluginName='" + PluginName + '\'' +
                ", adapterActivityName='" + adapterActivityName + '\'' +
                ", versionCode='" + versionCode + '\'' +
                ", hostVersionCode='" + hostVersionCode + '\'' +
                ", fingerprint='" + fingerprint + '\'' +
                ", downLoadUrl='" + downLoadUrl + '\'' +
                ", isOffMarket=" + isOffMarket +
                ", isAutoInstall=" + isAutoInstall +
                '}';
    }

    public static PluginInfo parsePluginInfo2PluginInfo(PackageInfo info) {
        PackageInfo manager = info.applicationInfo;

        PluginInfo info1 = new PluginInfo();
        return info1;
    }

    public String getSavePluginPath() {
        if (!islegal()) {
            return null;
        }
        Context hostContext = HostInit.getHostAppContext();
        File dir = hostContext.getDir(Contant.plugin_save_path, 0);
        String filePath = "/" + packageId + "_" + versionCode + "/" + packageId + "_" + versionCode + ".pp";
        File file = new File(dir, filePath);
        return file.getPath();
    }

    /**
     * 获取存放plugin对象的唯一key
     *
     * @return
     */
    public String getAdvPuginMapKey() {
        return packageId + "_" + versionCode;
    }

    /**
     * 是否合法
     *
     * @return
     */
    public boolean islegal() {
        if (TextUtils.isEmpty(packageId) ||
                TextUtils.isEmpty(versionCode) ||
                TextUtils.isEmpty(hostVersionCode)) {
            return false;
        }
        return true;
    }

    /**
     * 插件和Host的版本是否对应得上。从而判断host中间件是否升级。
     *
     * @return
     */
    public boolean isHostVersion() {
        return Contant.mHostVersionCode.equals(hostVersionCode) ? true : false;
    }
}
/*

插件的对应数据

[{
	"packageId": "com.coloros.advert.browser.smallpicturedeeplink",
	"versionCode": "2",
	"lowHostVersionCode": "100",
	"highHostVersionCode": "100",
	"isOffMarket": false,
	"preDownload": false
 },
 {
	"packageId": "com.coloros.advert.browser.grouppicturefastapp",
	"versionCode": "2",
	"lowHostVersionCode": "100",
	"highHostVersionCode": "100",
	"isOffMarket": false,
	"preDownload": false
}]










从服务端下发的数据
[{
	"packageId": "com.test.bundleone",
        "pluginName": "bundleone",
        "adapterActivityName": "短视频主页",
	"versionCode": "2",
	"hostVersionCode": "100",
        "fingerprint": "345rgaqrgdag",

        "downLoadUrl": "http://www.dkgj.ajdk.djjjjjddkkkdkdkd",
	"isOffMarket": false,
	"isAutoInstall": false
 },
 {
	"packageId": "com.test.bundletwo",
        "pluginName": "bundletwo",
        "adapterActivityName": "短视频主页",
	"versionCode": "2",
	"hostVersionCode": "100",
        "fingerprint": "345rgaqrgdag",

        "downLoadUrl": "http://www.dkgj.ajdk.iijtdueophethhewpe",
	"isOffMarket": false,
	"isAutoInstall": false
 }]




* */