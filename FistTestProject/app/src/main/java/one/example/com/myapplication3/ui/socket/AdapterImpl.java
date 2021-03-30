package one.example.com.myapplication3.ui.socket;

import com.xuhao.didi.core.iocore.interfaces.IPulseSendable;
import com.xuhao.didi.core.iocore.interfaces.ISendable;
import com.xuhao.didi.core.pojo.OriginalData;
import com.xuhao.didi.socket.client.sdk.client.ConnectionInfo;
import com.xuhao.didi.socket.client.sdk.client.action.SocketActionAdapter;
import com.xuhao.didi.socket.client.sdk.client.connection.IConnectionManager;

import one.example.com.myapplication3.Logs;
import one.example.com.myapplication3.utile.ThreadUtil;

public class AdapterImpl extends SocketActionAdapter {
    public IConnectionManager mManager = null;
    CallBack<String> mCallBack = null;
    private String TAG = "AdapterImpl";

    public AdapterImpl(IConnectionManager manager) {
        mManager = manager;
    }

    public AdapterImpl(IConnectionManager manager, CallBack<String> callBack) {
        mManager = manager;
        mCallBack = callBack;
    }

    @Override
    public void onSocketConnectionSuccess(ConnectionInfo info, String action) {
//        mManager.send(new HandShakeBean());
        logSend("onSocketConnectionSuccess 连接成功(action = " + action);
    }

    @Override
    public void onSocketDisconnection(ConnectionInfo info, String action, Exception e) {
        if (e != null) {
            logSend("onSocketDisconnection 异常断开(Disconnected with exception):" + e.getMessage());
        } else {
            logSend("onSocketDisconnection 正常断开(Disconnect Manually)");
        }
    }

    @Override
    public void onSocketConnectionFailed(ConnectionInfo info, String action, Exception e) {
//        logSend("连接失败(Connecting Failed)");
        logSend("onSocketConnectionFailed  action=" + action);
    }

    @Override
    public void onSocketReadResponse(ConnectionInfo info, String action, OriginalData data) {
//        String str = new String(data.getBodyBytes(), Charset.forName("utf-8"));
//        logSend(str);
        logSend("onSocketReadResponse  action=" + action);
        logSend("onSocketReadResponse  是否是主线程=" + ThreadUtil.isMainThread());
        mCallBack.callBack(data.toString());

    }

    @Override
    public void onSocketWriteResponse(ConnectionInfo info, String action, ISendable data) {
//        String str = new String(data.parse(), Charset.forName("utf-8"));
//        logSend(str);
        logSend("onSocketWriteResponse  action=" + action);
    }

    @Override
    public void onPulseSend(ConnectionInfo info, IPulseSendable data) {
//        String str = new String(data.parse(), Charset.forName("utf-8"));
        logSend("onPulseSend  info=" + info.getIp());
    }

    @Override
    public void onSocketIOThreadStart(String action) {
        logSend("onSocketIOThreadStart action=" + action);
    }

    @Override
    public void onSocketIOThreadShutdown(String action, Exception e) {
        logSend("onSocketIOThreadShutdown action=" + action);
    }

    private void logSend(String str) {
        Logs.eprintln(TAG,str);
    }
}
