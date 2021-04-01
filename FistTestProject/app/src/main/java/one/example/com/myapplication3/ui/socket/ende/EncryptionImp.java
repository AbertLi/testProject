package one.example.com.myapplication3.ui.socket.ende;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.zip.CRC32;

import one.example.com.myapplication3.Logs;

public class EncryptionImp extends AbstractEnDe {
    private String TAG = "EncryptionImp";

    @Override
    public String getJsonString() throws JSONException {
        JSONObject object = new JSONObject();
        object.put(ProtocolConstant.cmdType, ProtocolConstant.CMDTYPE1101);
        object.put(ProtocolConstant.equipNo, getEquipNo());
        object.put(ProtocolConstant.type, ParamCache.getInstance().getType());
        object.put(ProtocolConstant.timeStamp, getTimeStamp());
        object.put(ProtocolConstant.snno, NumOfCallUtil.getInstance().getCallNumAdd());
        object.put(ProtocolConstant.len, getLen());
        object.put(ProtocolConstant.crc, getCrc());
        object.put(ProtocolConstant.dataArea, getDataArea());
        Logs.iprintln("getJaonString = " + object.toString());
        return object.toString();
    }

    @Override
    public String getEquipNo() {
        return ParamCache.getInstance().getEquipNo();
    }

    public long getTimeStamp() {
        return System.currentTimeMillis();
    }

    public int getLen() throws JSONException {
        return getDataArea().toString().length();
    }

    public String getCrc() throws JSONException {
        CRC32 crc32 = new CRC32();
        crc32.update(getDataArea().toString().getBytes());
        return Long.toHexString(crc32.getValue());
    }

    public JSONObject getDataArea() throws JSONException {
        JSONObject areaObj = new JSONObject();
        areaObj.put("opt", 0);
        areaObj.put("minProtocolVer", "V1.00.00");
        return areaObj;
    }

    /**
     * =====================================================================
     **/

    public String getJson(String str) {
        String result = decode(str.getBytes());
        Logs.iprintln(TAG, "解密结果"+result);
        return result;
    }
}
