package one.example.com.myapplication3.ui.wheelview.sensor;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class SensorTool {
    public void run(Context context) {
        SensorManager mSensorManager;
        List<Sensor> sensorList;
        // 实例化传感器管理者
        mSensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        // 得到设置支持的所有传感器的List
        sensorList = mSensorManager.getSensorList(Sensor.TYPE_ALL);
        List<String> sensorNameList = new ArrayList<String>();
        for (Sensor sensor : sensorList) {
            Log.d("TAG", "onResume: " + sensor.getName());
        }
    }
}
