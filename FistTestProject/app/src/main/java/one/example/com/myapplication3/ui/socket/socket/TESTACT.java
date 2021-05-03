//package one.example.com.myapplication3.ui.socket.socket;
//
//import android.app.Activity;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.Toast;
//
//import androidx.annotation.Nullable;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import io.netty.buffer.ByteBuf;
//import io.netty.channel.ChannelFuture;
//import io.netty.channel.ChannelFutureListener;
//
//public class TESTACT extends Activity implements NettyListener {
//
//    private Button btn_send;//发送
//    private NettyClient nettyClient;//socket操作连接对象
//    private final String TAG = "MainActivity.class";
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }
//
//    protected void initView() {//这我自己的基类抽象的
//        initSocketTcp();//默认自动连接socket
//
//        btn_send = new Button(this);
//        btn_send.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (!nettyClient.getConnectStatus()) {//获取连接状态，必须连接才能点。
//                    Toast.makeText(TESTACT.this, "先连接", Toast.LENGTH_SHORT).show();
//                } else {
//                    JSONObject jsonObject = new JSONObject();//传个jsonobject给服务器
//                    long l = System.currentTimeMillis();
//                    try {
//                        jsonObject.put("msgType", "infomation");
//                        jsonObject.put("msgValue", "status");
//                        jsonObject.put("msgTime", l + "");
//
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                    /*
//                    调用的发送。
//                     */
//                    nettyClient.sendMsgToServer(jsonObject.toString(), new ChannelFutureListener() {
//                        @Override
//                        public void operationComplete(ChannelFuture channelFuture) throws Exception {
//                            if (channelFuture.isSuccess()) {                //4
//                                Log.d(TAG, "Write auth successful");
//                            } else {
//                                Log.d(TAG, "Write auth error");
//                            }
//                        }
//                    });
//                }
//            }
//        });
//    }
//
//    /*
//     socket 端口号以及开始连接，配置接口监听
//     */
//    private void initSocketTcp() {
//        nettyClient = new NettyClient(Const.HOST, Const.TCP_PORT);
//        if (!nettyClient.getConnectStatus()) {
//            nettyClient.setListener(TESTACT.this);
//            nettyClient.connect();
//        } else {
//            nettyClient.disconnect();
//        }
//    }
//
//
//    /*
//       回调客户端接收的信息  解析 数据流， 转换为string
//       */
//    @Override
//    public void onMessageResponse(final Object msg) {
//        ByteBuf result = (ByteBuf) msg;
//        byte[] result1 = new byte[result.readableBytes()];
//        result.readBytes(result1);
//        result.release();
//        final String ss = new String(result1);
//
//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//
//                Toast.makeText(TESTACT.this, "接收成功" + ss, Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//
//    //连接状态变化的时候 会走这
//    @Override
//    public void onServiceStatusConnectChanged(final int statusCode) {
//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                if (statusCode == NettyListener.STATUS_CONNECT_SUCCESS) {
//                    Log.e(TAG, "STATUS_CONNECT_SUCCESS:");
//                    if (nettyClient.getConnectStatus()) {
//                        Toast.makeText(TESTACT.this, "连接成功", Toast.LENGTH_SHORT).show();
//                    }
//                } else {
//                    Log.e(TAG, "onServiceStatusConnectChanged:" + statusCode);
//                    if (!nettyClient.getConnectStatus()) {
//                        Toast.makeText(TESTACT.this, "网路不好，正在重连", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            }
//        });
//    }
//}