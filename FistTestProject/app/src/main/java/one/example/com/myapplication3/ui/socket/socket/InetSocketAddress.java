package one.example.com.myapplication3.ui.socket.socket;


import java.net.SocketAddress;

public class InetSocketAddress extends SocketAddress {
    public InetSocketAddress(){

    }


    private static int checkPort(int var0) {
        if (var0 >= 0 && var0 <= 65535) {
            return var0;
        } else {
            throw new IllegalArgumentException("port out of range:" + var0);
        }
    }

    private static String checkHost(String var0) {
        if (var0 == null) {
            throw new IllegalArgumentException("hostname can't be null");
        } else {
            return var0;
        }
    }
}
