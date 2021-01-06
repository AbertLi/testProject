package one.example.com.myapplication3.designpatterns.visitorpattern.test2;

public class ArtCompany implements Company {
    @Override
    public String create(Cuprum el) {
        return "雕塑";
    }

    @Override
    public String create(Paper el) {
        return "油画";
    }
}
