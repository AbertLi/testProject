package one.example.com.myapplication3.ui.socket.ende;

/**
 * 设备号加密
 */
public class DeviceCodesumUtil {
    private byte mSum = 0;//字节求和
    private DeviceCodesumUtil() {
    }

    static class SingleHolder {
        static DeviceCodesumUtil mDeviceCodesumUtil = new DeviceCodesumUtil();
    }

    public static DeviceCodesumUtil getInstance() {
        return SingleHolder.mDeviceCodesumUtil;
    }

    public byte getCacheSum(){
        return mSum;
    }

    /**
     * 求设备字符之和，用byte存储
     *
     * @param sourceStr
     * @return
     */
    public byte getEquipNoSUM(String sourceStr) {
        byte sum = 0;
        for (char ch : sourceStr.toCharArray()) {
            sum += (byte) ch;
        }
        mSum = sum;
        return sum;
    }
}
