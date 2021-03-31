package one.example.com.myapplication3.ui.socket.ende;

import org.json.JSONException;
import org.json.JSONObject;

import one.example.com.myapplication3.ui.socket.BytesHexStrTranslate;
import one.example.com.runtime.utils.Logs;

abstract class AbstractEnDe implements IEncryptionDecryption {
    /**
     * 加密
     *
     * @param stringJson
     * @param equipNoSum
     * @return
     */
    private byte[] encode(String stringJson, int equipNoSum) {
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


    public byte[] encodeToByteArray() {
        String jsonString = null;
        try {
            jsonString = getJsonString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return encode(jsonString, getEquipNoSUM(getEquipNo()));
    }

    @Override
    public String encode() {
        return BytesHexStrTranslate.bytesToHexFun1(encode())
    }

    /**
     * 求设备字符之和，用byte存储
     *
     * @param sourceStr
     * @return
     */
    private byte getEquipNoSUM(String sourceStr) {
        byte sum = 0;
        for (char ch : sourceStr.toCharArray()) {
            sum += (byte) ch;
        }
        return sum;
    }

    abstract public String getJsonString() throws JSONException;

    abstract public String getEquipNo();

    //==========================================
    @Override
    public String decodeToJsonString(byte[] ba) {
        return decodeToJsonString(ba, getEquipNoSUM(getEquipNo()));
    }

    public String decodeToJsonString(byte[] ba, int equipNoSum) {
        byte[] byteArray = new byte[ba.length];
        int commaIndex = 0;//第几个逗号
        int quotationMarksIndex = 0;//第几个引号
        boolean isEnCode = false;//是否开始加密
        for (int i = 0; i < ba.length; i++) {
            byte b = ba[i];
            if (b == (byte) ',') {
                commaIndex++;
            }
            if (b == (byte) '"') {
                quotationMarksIndex++;
            }
            if (quotationMarksIndex==5){

            }

            if (commaIndex >= 2) {
                if (isEnCode) {
                    byte sum = (byte) (b - (byte) equipNoSum - (byte) i);
                    Logs.iprintln("result  加密后的sumStr=" + sum + " toString = " + BytesHexStrTranslate.bytesToHexFun1(new byte[]{sum}));
                    byteArray[i] = sum;
                } else {

                    byteArray[i] = b;
                }
                isEnCode = true;
            } else {
                byteArray[i] = b;
            }
        }
        return BytesHexStrTranslate.bytesToHexFun1(byteArray);
    }
}
