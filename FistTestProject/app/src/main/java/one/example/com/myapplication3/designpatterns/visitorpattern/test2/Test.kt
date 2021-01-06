package one.example.com.myapplication3.designpatterns.visitorpattern.test2

fun main() {
    var cuprum = Cuprum()
    var paper = Paper()

    SetMaterial.getInstance().addMaterial(cuprum)
    SetMaterial.getInstance().addMaterial(paper)

    println("艺术公司生产了:${SetMaterial.getInstance().accept(ArtCompany())}")
    println("货币公司生产了：${SetMaterial.getInstance().accept(MintCompany())}")
}