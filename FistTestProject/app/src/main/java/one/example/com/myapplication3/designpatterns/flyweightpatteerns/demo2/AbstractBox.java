package one.example.com.myapplication3.designpatterns.flyweightpatteerns.demo2;
//抽象享元类
public abstract class AbstractBox {
    public abstract String getShape();

    public void display(String color) {
        System.out.println("方块的形状是：" + getShape() + " 颜色是：" + color);
    }
}
