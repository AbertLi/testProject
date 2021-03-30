package one.example.com.myapplication3.utile

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.net.wifi.WifiManager
import android.provider.Settings

object WifiUtil {

    /**
     * 打开wifi设置页面
     */
    fun startWifiSettingsAct(act: Activity) {
        act.startActivity(Intent(Settings.ACTION_WIFI_SETTINGS)) //直接进入手机中的wifi网络设置界面
    }


    /**
     * 获取wifi列表
     */
    fun getWifiIP(appContext: Application): String {
        //获取wifi服务
        var wifiManager = appContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        //判断wifi是否开启
        var wifiInfo = wifiManager.connectionInfo
        var ipAddress = wifiInfo.ipAddress
        return intToIp(ipAddress)
    }

    private fun intToIp(i: Int): String {
        return "${i.and(0xFF)}" +
                ".${(i.shr(8)).and(0xFF)}" +
                ".${(i.shr(16)).and(0xFF)}" +
                ".${i.shr(24).and(0xFF)}"
    }

    /**
     * 判断wifi是否打开
     */
    fun isWifiOpened(appContext: Application): Boolean {
        var wifiManager = appContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        return wifiManager.isWifiEnabled
    }

    fun openWifi(appContext: Application) {
        var wifiManager = appContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        wifiManager.setWifiEnabled(true)
    }
}