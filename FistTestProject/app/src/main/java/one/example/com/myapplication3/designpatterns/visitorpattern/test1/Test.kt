package one.example.com.myapplication3.designpatterns.visitorpattern.test1

import one.example.com.myapplication3.designpatterns.visitorpattern.test1.*

fun main() {
    val os = ObjectStructure()
    os.add(ConcreteElementA())
    os.add(ConcreteElementB())
    var visitor: Visitor = ConcreteVisitorA()
    os.accept(visitor)
    println("------------------------")
    visitor = ConcreteVisitorB()
    os.accept(visitor)
}