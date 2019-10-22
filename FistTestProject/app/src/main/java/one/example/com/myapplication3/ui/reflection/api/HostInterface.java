package one.example.com.myapplication3.ui.reflection.api;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import one.example.com.myapplication3.utile.logutile.Log;

public class HostInterface {
    private String TAG = "HostInterface";
    private static HostInterface hostInterface;
    private static Map<Class, Object> map = new HashMap<>();

    public static HostInterface getInstance() {
        if (hostInterface == null) {
            synchronized (HostInterface.class) {
                if (hostInterface == null) {
                    hostInterface = new HostInterface();
                }
            }
        }
        return hostInterface;
    }


    public void register(Class<?> key, Object object) {
        synchronized (map) {
            map.put(key, object);
        }
    }

    public <T> T get(Class<T> key) {
        Object objct;
        synchronized (map) {
            objct = map.get(key);
        }
        if (objct == null) {
            Log.e(TAG, " map get is null");
            objct = reflectionGetImpl(key);
            Log.e(TAG, " reflectionGetImpl get is null");
            if (objct != null) {
                synchronized (map) {
                    map.put(key, objct);
                }
            }
            else {
                objct = null;
            }
        }
        return (T) objct;
    }

    public Object reflectionGetImpl(Class<?> key) {
        try {
            Class cla = Class.forName("one.example.com.myapplication3.ui.reflection.host_impl.CommonInterfaceImpl");
            Method method = cla.getMethod("getHostInterface", Class.class);
            return method.invoke(null, key);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
