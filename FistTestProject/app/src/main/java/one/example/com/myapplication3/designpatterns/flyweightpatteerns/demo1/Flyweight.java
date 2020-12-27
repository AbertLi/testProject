package one.example.com.myapplication3.designpatterns.flyweightpatteerns.demo1;

//抽象享元角色
interface Flyweight {
    public void operation(UnsharedConcreteFlyweight state);

}