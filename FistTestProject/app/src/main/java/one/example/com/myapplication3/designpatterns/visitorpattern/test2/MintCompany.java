package one.example.com.myapplication3.designpatterns.visitorpattern.test2;

public class MintCompany implements Company {
    @Override
    public String create(Cuprum el) {
        return "铜钱";
    }

    @Override
    public String create(Paper el) {
        return "纸币";
    }
}
