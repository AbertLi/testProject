package one.example.com.myapplication3.ui.socket;

import com.xuhao.didi.core.iocore.interfaces.ISendable;

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;

/**
 * 发送消息的类
 */
public class SendDataImpl implements ISendable {
    private String TAG = "SendDataImpl";
    private String str = "";

    public SendDataImpl() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("cmd", 14);
            jsonObject.put("data", "{x:2,y:1}");
            str = jsonObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public SendDataImpl(String strInfo) {
        str = strInfo;
    }

    @Override
    public byte[] parse() {
        //根据服务器的解析规则,构建byte数组
        byte[] body = str.getBytes(Charset.defaultCharset());
//        ByteBuffer bb = ByteBuffer.allocate(4 + body.length);
//        bb.order(ByteOrder.BIG_ENDIAN);
//        bb.putInt(body.length);
//        bb.put(body);
//        return bb.array();
        return body;
    }
}

