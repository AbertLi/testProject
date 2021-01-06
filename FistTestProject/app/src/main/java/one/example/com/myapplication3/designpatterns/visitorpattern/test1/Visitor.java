package one.example.com.myapplication3.designpatterns.visitorpattern.test1;

//抽象访问者
interface Visitor {
    void visit(ConcreteElementA element);

    void visit(ConcreteElementB element);
}