package one.example.com.myapplication3.designpatterns.visitorpattern.test1;

//抽象元素类
interface Element {
    void accept(Visitor visitor);
}