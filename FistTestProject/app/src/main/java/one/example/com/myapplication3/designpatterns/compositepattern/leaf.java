package one.example.com.myapplication3.designpatterns.compositepattern;

//树叶构件
class leaf implements Component {
    private String name;
    public leaf(String name) {
        this.name = name;
    }
    public void add(Component c) {
    }
    public void remove(Component c) {
    }
    public Component getChild(int i) {
        return null;
    }
    public void operation() {
        System.out.println("树叶" + name + "：被访问！");
    }
}