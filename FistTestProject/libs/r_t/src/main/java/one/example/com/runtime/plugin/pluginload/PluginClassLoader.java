package one.example.com.runtime.plugin.pluginload;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;
import one.example.com.runtime.utils.Logs;

public class PluginClassLoader extends DexClassLoader {
    private String TAG = "PluginClassLoader";
    private ClassLoader mHostclassLoader;
    private Method method;

    public PluginClassLoader(String dexPath, String optimizedDirectory, String librarySearchPath, ClassLoader parent,
            ClassLoader hostClassLoader) {
        super(dexPath, optimizedDirectory, librarySearchPath, parent);
        mHostclassLoader = hostClassLoader;

        Class<?> cls = mHostclassLoader.getClass();
        method = getMethod(cls, "loadClass", String.class, Boolean.TYPE);
    }


    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        Class<?> class1 = super.loadClass(name, resolve);
        if (class1 != null) {
            return class1;
        }

        try {
            class1 = (Class<?>) method.invoke(mHostclassLoader, name, resolve);
        } catch (IllegalAccessException e) {
            Logs.eprintln(TAG, "loadClass IllegalAccessException e.getMsg = " + e.getMessage());
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            Logs.eprintln(TAG, "loadClass InvocationTargetException e.getMsg = " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            Logs.eprintln(TAG, "loadClass Exception e.getMsg = " + e.getMessage());
            e.printStackTrace();
        }
        return class1;
    }

    public Method getMethod(Class<?> cl, String methodName, Class<?>... parameterType) {
        for (Class<?> cls = cl; cls != null; cls = cls.getSuperclass()) {
            try {
                Method method = cls.getDeclaredMethod(methodName, parameterType);

                if (method.isAccessible() != true) {//???
                    method.setAccessible(true);
                }
                return method;
            } catch (NoSuchMethodException e) {
                Logs.eprintln(TAG, "e.getMsg = " + e.getMessage());
                e.printStackTrace();
            }
        }
        Method methods = null;
        for (Class<?> class1 : cl.getInterfaces()) {
            try {
                methods = class1.getMethod(methodName, parameterType);
            } catch (NoSuchMethodException e) {
                Logs.eprintln(TAG, "getMethod NoSuchMethodException e.getMsg = " + e.getMessage());
                e.printStackTrace();
            }
        }
        return methods;
    }
}
