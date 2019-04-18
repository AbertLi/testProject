package one.example.com.myapplication3.utile.logutile;

public class Log {
    private static LogWriteUtile getLogWrite() {
        return LogWriteUtile.getInstance();
    }

    public static void v(String tag, String context) {
        getLogWrite().print(tag, LevelUtile.VERBOSE, context);
    }

    public static void d(String tag, String context) {
        getLogWrite().print(tag, LevelUtile.DEBUG, context);
    }

    public static void i(String tag, String context) {
        getLogWrite().print(tag, LevelUtile.INFO, context);
    }

    public static void w(String tag, String context) {
        getLogWrite().print(tag, LevelUtile.WARN, context);
    }

    public static void e(String tag, String context) {
        getLogWrite().print(tag, LevelUtile.ERROR, context);
    }

    public static void a(String tag, String context) {
        getLogWrite().print(tag, LevelUtile.ASSERT, context);
    }
}
