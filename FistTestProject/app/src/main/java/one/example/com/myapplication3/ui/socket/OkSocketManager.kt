package one.example.com.myapplication3.ui.socket

import android.app.Application
import com.xuhao.didi.socket.client.sdk.OkSocket
import com.xuhao.didi.socket.client.sdk.client.ConnectionInfo
import com.xuhao.didi.socket.client.sdk.client.connection.IConnectionManager
import one.example.com.myapplication3.utile.WifiUtil


class OkSocketManager {
    companion object {
        private val TAG = "OkSocketManager"
        private var mSocketManager = SingleHolder.socketManager
        fun getInstance(): OkSocketManager {
            return mSocketManager
        }
    }

    object SingleHolder {
        var socketManager = OkSocketManager()
    }

    var manager: IConnectionManager? = null

    fun connect(app: Application) {
        var ip = WifiUtil.getWifiIP(appContext = app)
        connect(ip, 9000)
    }

    fun connect(ip: String, port: Int) {
        //连接参数设置(IP,端口号),这也是一个连接的唯一标识,不同连接,该参数中的两个值至少有其一不一样
        val info = ConnectionInfo(ip, port)//test ip and port  "104.238.184.237", 8080
        //调用OkSocket,开启这次连接的通道,调用通道的连接方法进行连接.
        manager = OkSocket.open(info)
        //注册Socket行为监听器,SocketActionAdapter是回调的Simple类,其他回调方法请参阅类文档
        manager?.registerReceiver(AdapterImpl(manager))
        //调用通道进行连接
        manager?.connect()
    }




    fun isConnect(): Boolean {
        return manager?.isConnect ?: false
    }

    fun sendData(str: String) {
        manager?.send(SendDataImpl(str))
    }
}
interface CallBack<T>{
    fun callBack(info : T)
}