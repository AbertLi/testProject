package one.example.com.myapplication3.ui.socket.fsocket.action;


import one.example.com.myapplication3.ui.socket.fsocket.ConnectionInfo;

public interface ISocketActionListener {
    void onSocketIOThreadStart(String action);
    void onSocketIOThreadShutdown(String action, Exception e);
//    void onSocketReadResponse(ConnectionInfo info, String action, OriginalData data);
//    void onSocketWriteResponse(ConnectionInfo info, String action, ISendable data);
//    void onPulseSend(ConnectionInfo info, IPulseSendable data);
    void onSocketDisconnection(ConnectionInfo info, String action, Exception e);
    void onSocketConnectionSuccess(ConnectionInfo info, String action);
    void onSocketConnectionFailed(ConnectionInfo info, String action, Exception e);
}
