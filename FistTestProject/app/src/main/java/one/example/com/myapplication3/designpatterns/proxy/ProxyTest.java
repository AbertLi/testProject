package one.example.com.myapplication3.designpatterns.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

interface Human {
    String getBelied();

    void eat(String food);
}

class SuperMan implements Human {//被代理类

    @Override
    public String getBelied() {
        return "I believe T can fly!";
    }

    @Override
    public void eat(String food) {
        System.out.println("like eat :" + food);
    }
}

/*
 * 要实现动态代理需要解决的问题：
 * 1.怎么根据加载到内存中的被代理类 ，动态的创建一个代理类以及代理类对象
 * 2.当通过代理类的对象时 如何动态的去调用被代理类中的同名方法
 */
class ProxyFactory {
    public static Object getProxyInstance(Object obj) {//obj:被代理类对象
        MyInvocationHandler handler = new MyInvocationHandler();
        handler.bind(obj);//这里就创建被被代理类对象
        //返回一个代理类对象  Object是一个代理类的对象
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), handler);
    }
}

class MyInvocationHandler implements InvocationHandler {
    private Object obj;//被代理类对象  赋值时使用被代理类对象复制

    public void bind(Object obj) {
        this.obj = obj;
    }

    //通过代理类对象，调用方法时，会自动的调用invoke()
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {//proxy ：代理类对象    method：代理类对象调用的方法
        //当通过代理类的对象时 如何动态的去调用被代理类中的同名方法
        //将被代理类要执行的方法声明在这里 invoke()
        return method.invoke(obj, args);//method:即为代理类对象，此方法就作为被代理独享要调用的方法
    }
}

