package one.example.com.myapplication3.designpatterns.flyweightpatteerns.demo1;

import one.example.com.myapplication3.designpatterns.flyweightpatteerns.demo1.FlyweightFactory
import one.example.com.myapplication3.designpatterns.flyweightpatteerns.demo1.UnsharedConcreteFlyweight

fun main() {
    var factory = FlyweightFactory()
    var f01 = factory.getFlyweight("a");
    var f02 = factory.getFlyweight("a");
    var f03 = factory.getFlyweight("a");
    var f11 = factory.getFlyweight("b");
    var f12 = factory.getFlyweight("b");
    f01.operation(UnsharedConcreteFlyweight("第1次调用a。"));
    f02.operation(UnsharedConcreteFlyweight("第2次调用a。"));
    f03.operation(UnsharedConcreteFlyweight("第3次调用a。"));
    f11.operation(UnsharedConcreteFlyweight("第1次调用b。"));
    f12.operation(UnsharedConcreteFlyweight("第2次调用b。"));
}


