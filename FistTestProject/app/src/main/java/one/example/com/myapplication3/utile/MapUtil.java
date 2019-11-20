package one.example.com.myapplication3.utile;

import java.util.Map;

public class MapUtil {
    @SuppressWarnings("unchecked")
    public static <T> T safeGet(Map<String, Object> map, String key, T defVal) {

        if (map == null || map.isEmpty()) {

            return defVal;

        }
        Object obj = map.get(key);
        try {

            return (T) obj;

        } catch (Exception e) {
            e.printStackTrace();

        }

        return defVal;

    }
}