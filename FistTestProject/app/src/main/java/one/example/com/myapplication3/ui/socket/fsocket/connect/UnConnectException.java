package one.example.com.myapplication3.ui.socket.fsocket.connect;


/**
 * Albert
 */

public class UnConnectException extends RuntimeException {
    public UnConnectException() {
        super();
    }

    public UnConnectException(String message) {
        super(message);
    }

    public UnConnectException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnConnectException(Throwable cause) {
        super(cause);
    }

//    @RequiresApi(api = Build.VERSION_CODES.N)
//    protected UnConnectException(String message, Throwable cause, boolean enableSuppression,
//                                 boolean writableStackTrace) {
//        super(message, cause, enableSuppression, writableStackTrace);
//    }
}
