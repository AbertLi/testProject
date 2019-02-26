package one.example.com.myapplication3.utile;


import java.util.Enumeration;
import java.util.Hashtable;

public class LiveDataBusEeven {

    private static Hashtable<String, BusliveEvent> liveDataBusEeven = new Hashtable<>();

    public static class singletHolder {
        private static LiveDataBusEeven liveDataBusEeven = new LiveDataBusEeven();
    }

    public static LiveDataBusEeven getInstance() {
        return singletHolder.liveDataBusEeven;
    }


    public BusliveEvent with(String key) {
        if (!liveDataBusEeven.containsKey(key)) {
            liveDataBusEeven.put(key, new BusliveEvent());
        }
        return (BusliveEvent) liveDataBusEeven.get(key);
    }

    public interface ICallBack {
        void back(Object o);
    }

    public static class BusliveEvent {
        private static Hashtable<Class, ICallBack> iCallBackTable = new Hashtable<>();


        public void postValue(final Object value) {
            Enumeration<ICallBack> e2 = iCallBackTable.elements();
            while (e2.hasMoreElements()) {
                ICallBack callBack = (ICallBack) e2.nextElement();
                callBack.back(value);
            }
        }


        public void observe(Class<?> c, final ICallBack iCallBack) {
            iCallBackTable.put(c, iCallBack);
        }

    }

}