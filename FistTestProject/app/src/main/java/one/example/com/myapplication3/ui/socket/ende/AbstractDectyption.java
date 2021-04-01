package one.example.com.myapplication3.ui.socket.ende;

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.Charset;

import one.example.com.runtime.utils.Logs;

/**
 * 解密
 */
abstract class AbstractDectyption implements IDecryption {
    private String TAG = "AbstractDectyption";
    private String dectyption = "";
    private boolean isDe;

    @Override
    public String decode(byte[] ba) {
        byte[] result = decodeToByteArray(ba);
        dectyption = new String(result, Charset.defaultCharset());
        return dectyption;
    }

    @Override
    public byte[] decodeToByteArray(byte[] ba) {
        byte[] result = new byte[0];
        try {
            result = decodeByteArray(ba);
        } catch (JSONException e) {
            e.printStackTrace();
            Logs.eprintln(TAG, "JSONException = " + e.toString());
        } catch (Exception e) {
            e.printStackTrace();
            Logs.eprintln(TAG, "Exception = " + e.toString());
        }
        isDe = true;
        return result;
    }

    private byte[] decodeByteArray(byte[] ba) throws Exception {
        byte[] byteArray = new byte[ba.length];
        int commaIndex = 0;//第几个逗号
        int quotationMarksIndex = 0;//第几个引号
        boolean isEnCode = false;//是否开始加密
        boolean isPrefixFlag = false;
        byte[] byteArrayPrefix;
        for (int i = 0; i < ba.length; i++) {
            byte b = ba[i];
            if (b == (byte) ',') {
                commaIndex++;
            }
            if (commaIndex >= 2) {
                if (isEnCode) {
                    b -= DeviceCodesumUtil.getInstance().getCacheSum();
                    b -= i;
                    byteArray[i] = b;
                } else {
                    byteArray[i] = b;
                }
                isEnCode = true;
            } else {
                byteArray[i] = b;
            }
            if (b == (byte) '"') {
                quotationMarksIndex++;
            }
            if (quotationMarksIndex == 6 && !isPrefixFlag) {
                isPrefixFlag = true;
                byteArrayPrefix = new byte[i + 2];
                savePrefix(byteArray, byteArrayPrefix);
            }
        }
        return byteArray;
    }

    private void savePrefix(byte[] arrayAll, byte[] byteArrayPrefix) throws Exception {
        int byteArrayPrefixLen = byteArrayPrefix.length;
        int LenReduceOne = byteArrayPrefixLen - 1;
        for (int i = 0; i < byteArrayPrefixLen; i++) {
            if (i == LenReduceOne) {
                byteArrayPrefix[i] = '}';
            } else {
                byteArrayPrefix[i] = arrayAll[i];
            }
        }
        String jsonPrefix = new String(byteArrayPrefix);
        JSONObject obj;

        obj = new JSONObject(jsonPrefix);
        int camType = obj.getInt(ProtocolConstant.cmdType);
        String equipNo = obj.getString(ProtocolConstant.equipNo);
        ParamCache.getInstance().setCmdType(camType);
        ParamCache.getInstance().setEquipNo(equipNo);
        DeviceCodesumUtil.getInstance().getEquipNoSUM(getEquipNo());
        if (camType == 9999) {
            throw new Exception("camType is 9999,The lower computer returns abnormal data.");
        }
    }

    private void checkDectyption() throws Exception {
        if (!isDe){
            throw new Exception("The decryption method has not been called yet, please call the decryption method first.");
        }
    }

    class Holder<T> {
        public T getValue(String key) throws JSONException {
            JSONObject obj = new JSONObject(dectyption);
            return (T) obj.get(key);
        }
    }


    public int getCmdType() throws Exception {
        checkDectyption();

    }

    public int getType(){

    }
    public int getTimeStamp(){

    }
    public int getNnno(){

    }
    public int getLen(){

    }
    public String getCrc(){

    }
    public String getDataArea(){

    }

}
