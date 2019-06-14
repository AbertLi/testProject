package com.albert.interfalib;

public class IHost {
    private IHostCommeFeatrue iHostCommeFeatrue;

    public static IHost mIHost;
    public static IHost getInstance() {
        if (mIHost == null) {
            synchronized (IHost.class) {
                if (mIHost == null) {
                    mIHost = new IHost();
                }
            }
        }
        return mIHost;
    }

    private IHost(){}


    public IHostCommeFeatrue getiHostCommeFeatrue() {
        return iHostCommeFeatrue;
    }

    public void setiHostCommeFeatrue(IHostCommeFeatrue iHostCommeFeatrue) {
        this.iHostCommeFeatrue = iHostCommeFeatrue;
    }
}
