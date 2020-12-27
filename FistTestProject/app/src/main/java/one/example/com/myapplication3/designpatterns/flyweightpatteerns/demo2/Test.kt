package one.example.com.myapplication3.designpatterns.flyweightpatteerns.demo2

fun main() {
    var o = BoxFactory.getInstance().getShare("O")
    o.display("白色")
    var p = BoxFactory.getInstance().getShare("P")
    p.display("白色")
    var q = BoxFactory.getInstance().getShare("Q")
    q.display("白色")
    q.display("蓝色")
}