//package one.example.com.myapplication3.ui.socket.fsocket;
//
//import android.text.TextUtils;
//
//import java.io.IOException;
//import java.net.InetSocketAddress;
//import java.net.Socket;
//import java.security.SecureRandom;
//
//import javax.net.ssl.SSLContext;
//import javax.net.ssl.SSLSocketFactory;
//import javax.net.ssl.TrustManager;
//
//import one.example.com.myapplication3.Logs;
//import one.example.com.myapplication3.ui.socket.fsocket.connect.AbsConnectionManager;
//import one.example.com.myapplication3.ui.socket.fsocket.connect.IConnectionManager;
//import one.example.com.myapplication3.ui.socket.fsocket.connect.UnConnectException;
//import one.example.com.myapplication3.ui.socket.fsocket.interfaces.IIOManager;
//
///**
// * Albert
// */
//public class ConnectionManagerImpl extends AbsConnectionManager {
//    private volatile Socket mSocket;
//    private IIOManager mManager;
//    private Thread mConnectThread;
//    private volatile boolean isConnectionPermitted = true; //能否连接
//    private volatile boolean isDisconnecting = false;      //是否正在断开
//
//
//    protected ConnectionManagerImpl(ConnectionInfo info) {
//        this(info, null);
//    }
//
//    public ConnectionManagerImpl(ConnectionInfo remoteInfo, ConnectionInfo localInfo) {
//        super(remoteInfo, localInfo);
//        String ip = "";
//        String port = "";
//        if (remoteInfo != null) {
//            ip = remoteInfo.getIp();
//            port = remoteInfo.getPort() + "";
//        }
//        Logs.iprintln("block connection init with:" + ip + ":" + port);
//
//        if (localInfo != null) {
//            Logs.iprintln("binding local addr:" + localInfo.getIp() + " port:" + localInfo.getPort());
//        }
//    }
//
//    @Override
//    public synchronized void connect() {
//        Logs.iprintln("Thread name:" + Thread.currentThread().getName() + " id:" + Thread.currentThread().getId());
//        String info = mRemoteConnectionInfo.getIp() + ":" + mRemoteConnectionInfo.getPort();
//        mConnectThread = new ConnectionThread(" Connect thread for " + info);
//        mConnectThread.setDaemon(true);
//        mConnectThread.start();
//    }
//
//    private synchronized Socket getSocketByConfig() throws Exception {
//        return new Socket();
//    }
//
//    private class ConnectionThread extends Thread {
//        public ConnectionThread(String name) {
//            super(name);
//        }
//
//        @Override
//        public void run() {
//            try {
//                try {
//                    mSocket = getSocketByConfig();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    throw new UnConnectException("Create socket failed.", e);
//                }
//                if (mLocalConnectionInfo != null) {
//                    Logs.iprintln("try bind: " + mLocalConnectionInfo.getIp() + " port:" + mLocalConnectionInfo.getPort());
//                    mSocket.bind(new InetSocketAddress(mLocalConnectionInfo.getIp(), mLocalConnectionInfo.getPort()));
//                }
//
//                Logs.iprintln("Start connect: " + mRemoteConnectionInfo.getIp() + ":" + mRemoteConnectionInfo.getPort() + " socket server...");
//                mSocket.connect(new InetSocketAddress(mRemoteConnectionInfo.getIp(), mRemoteConnectionInfo.getPort()), 3 * 1000);
//                //关闭Nagle算法,无论TCP数据报大小,立即发送
//                mSocket.setTcpNoDelay(true);
//                resolveManager();
//                Logs.iprintln("Socket server: " + mRemoteConnectionInfo.getIp() + ":" + mRemoteConnectionInfo.getPort() + " connect successful!");
//            } catch (Exception e) {
//                e.printStackTrace();
//                Exception exception = new UnConnectException(e);
//                Logs.iprintln("Socket server " + mRemoteConnectionInfo.getIp() + ":" + mRemoteConnectionInfo.getPort() + " connect failed! error msg:" + e.getMessage());
//            } finally {
//                isConnectionPermitted = true;
//            }
//        }
//    }
//
////    private void resolveManager() throws IOException {
////        mPulseManager = new PulseManager(this, mOptions);
////
////        mManager = new IOThreadManager(
////                mSocket.getInputStream(),
////                mSocket.getOutputStream(),
////                mOptions,
////                mActionDispatcher);
////        mManager.startEngine();
////    }
//
////    @Override
////    public void disconnect(Exception exception) {
////        synchronized (this) {
////            if (isDisconnecting) {
////                return;
////            }
////            isDisconnecting = true;
////
////            if (mPulseManager != null) {
////                mPulseManager.dead();
////                mPulseManager = null;
////            }
////        }
////
////        if (exception instanceof ManuallyDisconnectException) {
////            if (mReconnectionManager != null) {
////                mReconnectionManager.detach();
////                Logs.iprintln("ReconnectionManager is detached.");
////            }
////        }
////
////        String info = mRemoteConnectionInfo.getIp() + ":" + mRemoteConnectionInfo.getPort();
////        DisconnectThread thread = new DisconnectThread(exception, "Disconnect Thread for " + info);
////        thread.setDaemon(true);
////        thread.start();
////    }
//
//    private class DisconnectThread extends Thread {
//        private Exception mException;
//
//        public DisconnectThread(Exception exception, String name) {
//            super(name);
//            mException = exception;
//        }
//
//        @Override
//        public void run() {
//            try {
//                if (mManager != null) {
//                    mManager.close(mException);
//                }
//
//                if (mConnectThread != null && mConnectThread.isAlive()) {
//                    mConnectThread.interrupt();
//                    try {
//                        Logs.iprintln("disconnect thread need waiting for connection thread done.");
//                        mConnectThread.join();
//                    } catch (InterruptedException e) {
//                    }
//                    Logs.iprintln("connection thread is done. disconnection thread going on");
//                    mConnectThread = null;
//                }
//
//                if (mSocket != null) {
//                    try {
//                        mSocket.close();
//                    } catch (IOException e) {
//                    }
//                }
//
//                if (mActionHandler != null) {
//                    mActionHandler.detach(com.xuhao.didi.socket.client.impl.client.ConnectionManagerImpl.this);
//                    Logs.iprintln("mActionHandler is detached.");
//                    mActionHandler = null;
//                }
//
//            } finally {
//                isDisconnecting = false;
//                isConnectionPermitted = true;
//                if (!(mException instanceof UnConnectException) && mSocket != null) {
//                    mException = mException instanceof ManuallyDisconnectException ? null : mException;
//                    sendBroadcast(IAction.ACTION_DISCONNECTION, mException);
//                }
//                mSocket = null;
//
//                if (mException != null) {
//                    Logs.iprintln("socket is disconnecting because: " + mException.getMessage());
//                    if (mOptions.isDebug()) {
//                        mException.printStackTrace();
//                    }
//                }
//            }
//        }
//    }
//
//
//    @Override
//    public void disconnect() {
//        disconnect(new ManuallyDisconnectException());
//    }
//
//    @Override
//    public IConnectionManager send(ISendable sendable) {
//        if (mManager != null && sendable != null && isConnect()) {
//            mManager.send(sendable);
//        }
//        return this;
//    }
//
//    @Override
//    public IConnectionManager option(OkSocketOptions okOptions) {
//        if (okOptions == null) {
//            return this;
//        }
//        mOptions = okOptions;
//        if (mManager != null) {
//            mManager.setOkOptions(mOptions);
//        }
//
//        if (mPulseManager != null) {
//            mPulseManager.setOkOptions(mOptions);
//        }
//        if (mReconnectionManager != null && !mReconnectionManager.equals(mOptions.getReconnectionManager())) {
//            if (mReconnectionManager != null) {
//                mReconnectionManager.detach();
//            }
//            Logs.iprintln("reconnection manager is replaced");
//            mReconnectionManager = mOptions.getReconnectionManager();
//            mReconnectionManager.attach(this);
//        }
//        return this;
//    }
//
//    @Override
//    public OkSocketOptions getOption() {
//        return mOptions;
//    }
//
//    @Override
//    public boolean isConnect() {
//        if (mSocket == null) {
//            return false;
//        }
//
//        return mSocket.isConnected() && !mSocket.isClosed();
//    }
//
//    @Override
//    public boolean isDisconnecting() {
//        return isDisconnecting;
//    }
//
//    @Override
//    public PulseManager getPulseManager() {
//        return mPulseManager;
//    }
//
//    @Override
//    public void setIsConnectionHolder(boolean isHold) {
//        mOptions = new OkSocketOptions.Builder(mOptions).setConnectionHolden(isHold).build();
//    }
//
//    @Override
//    public AbsReconnectionManager getReconnectionManager() {
//        return mOptions.getReconnectionManager();
//    }
//
//    @Override
//    public ConnectionInfo getLocalConnectionInfo() {
//        ConnectionInfo local = super.getLocalConnectionInfo();
//        if (local == null) {
//            if (isConnect()) {
//                InetSocketAddress address = (InetSocketAddress) mSocket.getLocalSocketAddress();
//                if (address != null) {
//                    local = new ConnectionInfo(address.getHostName(), address.getPort());
//                }
//            }
//        }
//        return local;
//    }
//
//    @Override
//    public void setLocalConnectionInfo(ConnectionInfo localConnectionInfo) {
//        if (isConnect()) {
//            throw new IllegalStateException("Socket is connected, can't set local info after connect.");
//        }
//        mLocalConnectionInfo = localConnectionInfo;
//    }
//}
