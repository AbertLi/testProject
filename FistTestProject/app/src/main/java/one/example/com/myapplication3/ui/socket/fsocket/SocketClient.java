//package one.example.com.myapplication3.ui.socket.fsocket;
//
//import one.example.com.myapplication3.ui.socket.fsocket.connect.AbsConnectionManager;
//import one.example.com.myapplication3.ui.socket.fsocket.connect.IConnectionManager;
//
///**
// * 可以连接Socket
// * 1，可以提示断开连接，和连接成功。
// * 2，能使用心跳。
// * 3，实现断开自动重新连接。
// * 4， 只是先一个Socket连接。
// */
//public class SocketClient {
//    private static AbsConnectionManager manager = null;
//
//    private static IConnectionManager getManager(ConnectionInfo info) {
//        if (manager == null) {
//            synchronized (SocketClient.class) {
//                if (manager == null) {
//                    manager = new ConnectionManagerImpl(info);
//                }
//            }
//        }
//        return manager;
//    }
//
//
//    public static IConnectionManager open(ConnectionInfo connectInfo) {
//        return getManager(connectInfo);
//    }
//
//    public static IConnectionManager open(String ip, int port) {
//        ConnectionInfo info = new ConnectionInfo(ip, port);
//        return getManager(info);
//    }
//}
