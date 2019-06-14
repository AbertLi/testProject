package one.example.com.myapplication3.runtime;

import com.albert.interfalib.IHost;

public class RunTimeInit {

    public static void init() {
        HomeImpl impl = new HomeImpl();
        IHost.getInstance().setiHostCommeFeatrue(impl);
    }
}
