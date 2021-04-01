package one.example.com.myapplication3.ui.socket.ende;

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.Charset;

import one.example.com.myapplication3.ui.socket.BytesHexStrTranslate;
import one.example.com.runtime.utils.Logs;

abstract class AbstractEnDe implements IEncryptionDecryption {
    private String TAG = "AbstractEnDe";

    /**
     * 加密
     *
     * @param stringJson
     * @param equipNoSum
     * @return
     */
    private byte[] encodeByteArray(String stringJson, int equipNoSum) {
        byte[] result = new byte[stringJson.length()];
        int commaIndex = 0;
        boolean isEnCode = false;//是否开始加密
        for (int i = 0; i < result.length; i++) {
            char ch = stringJson.charAt(i);
            if (ch == ',') {
                commaIndex++;
            }
            if (commaIndex >= 2) {
                if (isEnCode) {
                    byte sum = (byte) ((byte) equipNoSum + (byte) i + (byte) ch);
                    Logs.iprintln("result  加密后的sumStr=" + sum + " toString = " + BytesHexStrTranslate.bytesToHexFun1(new byte[]{sum}));
                    result[i] = sum;
                } else {
                    result[i] = (byte) ch;
                }
                isEnCode = true;
            } else {
                result[i] = (byte) ch;
            }
        }
        return result;
    }

    @Override
    public byte[] encodeToByteArray() {
        String jsonString = null;
        try {
            jsonString = getJsonString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return encodeByteArray(jsonString, DeviceCodesumUtil.getInstance().getEquipNoSUM(getEquipNo()));
    }

    @Override
    public String encode() {
        return BytesHexStrTranslate.bytesToHexFun1(encodeToByteArray());//把字节数组转成16进制String字符串
    }

    abstract public String getJsonString() throws JSONException;

    abstract public String getEquipNo();

    /**
     * ======================解密====================
     **/
    @Override
    public String decode(byte[] ba) {
        byte[] result = decodeToByteArray(ba);
        return new String(result, Charset.defaultCharset());
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
                    byte difference = (byte) (b - DeviceCodesumUtil.getInstance().getCacheSum() - (byte) i);
                    Logs.dprintln(TAG, "result  加密后的sumStr=" + difference + " toString = " + BytesHexStrTranslate.bytesToHexFun1(new byte[]{difference}));
                    byteArray[i] = difference;
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
            throw new Exception("下位机返回数据异常");
        }
    }
}
