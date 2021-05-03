package one.example.com.myapplication3.ui.socket.fsocket.action;

import one.example.com.myapplication3.ui.socket.fsocket.ConnectionInfo;

/**
 * Socket行为适配器,是行为监听器的一个Simple版本,详情请见{@link ISocketActionListener}
 * Created by xuhao on 2017/5/17.
 */

public abstract class SocketActionAdapter implements ISocketActionListener {
    @Override
    public void onSocketIOThreadStart(String action) {

    }
    @Override
    public void onSocketIOThreadShutdown(String action, Exception e) {

    }
    @Override
    public void onSocketDisconnection(ConnectionInfo info, String action, Exception e) {

    }
    @Override
    public void onSocketConnectionSuccess(ConnectionInfo info, String action) {

    }
    @Override
    public void onSocketConnectionFailed(ConnectionInfo info, String action, Exception e) {

    }
//    @Override
//    public void onSocketReadResponse(ConnectionInfo info, String action, OriginalData data) {
//    }
//    @Override
//    public void onSocketWriteResponse(ConnectionInfo info, String action, ISendable data) {
//    }
//    @Override
//    public void onPulseSend(ConnectionInfo info, IPulseSendable data) {
//    }
}
