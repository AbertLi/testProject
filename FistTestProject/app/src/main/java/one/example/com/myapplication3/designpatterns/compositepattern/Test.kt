package one.example.com.myapplication3.designpatterns.compositepattern


fun main() {
    val c0: Component = Composite()
    val c1: Component = Composite()
    val leaf1: Component = leaf("1")
    val leaf2: Component = leaf("2")
    val leaf3: Component = leaf("3")
    c0.add(leaf1)
    c0.add(c1)
    c1.add(leaf2)
    c1.add(leaf3)
    c0.operation()
}