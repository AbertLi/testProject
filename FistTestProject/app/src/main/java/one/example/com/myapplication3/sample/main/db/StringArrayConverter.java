package one.example.com.myapplication3.sample.main.db;

import androidx.room.TypeConverter;

public class StringArrayConverter {
    @TypeConverter
    public static String[] stringToStringArray(String s) {
        return s.split(",");
    }

    @TypeConverter
    public static String stringArrayToString(String[] array) {
        if (array == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        for (String s : array) {
            sb.append(s).append(",");
        }
        return sb.toString();
    }
}
