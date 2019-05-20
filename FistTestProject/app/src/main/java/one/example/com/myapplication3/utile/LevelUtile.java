package one.example.com.myapplication3.utile;

public class LevelUtile {
    public static final int VERBOSE = 1;
    public static final int DEBUG = 2;
    public static final int INFO = 3;
    public static final int WARN = 4;
    public static final int ERROR = 5;
    public static final int ASSERT = 6;

    public static final String V = "V";
    public static final String D = "D";
    public static final String I = "I";
    public static final String W = "W";
    public static final String E = "E";
    public static final String A = "A";

    public static String getLevel(int level) {
        String Level = " ";
        switch (level) {
            case VERBOSE:
                Level = V;
                break;
            case DEBUG:
                Level = D;
                break;
            case INFO:
                Level = I;
                break;
            case WARN:
                Level = W;
                break;
            case ERROR:
                Level = E;
                break;
            case ASSERT:
                Level = A;
                break;
        }
        return Level;
    }
}
