package one.example.com.myapplication3.runtime;

import com.albert.interfalib.IHostCommeFeatrue;

import one.example.com.myapplication3.Logs;

public class HomeImpl implements IHostCommeFeatrue {
    @Override
    public void callPhone() {
        Logs.eprintln("拨打电话");
    }

    @Override
    public void startWebLink() {
        Logs.eprintln("启动网页");
    }

    @Override
    public void startDeeplink() {
        Logs.eprintln("启动Deeplink");
    }
}
