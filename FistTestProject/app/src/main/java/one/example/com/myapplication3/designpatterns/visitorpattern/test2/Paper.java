package one.example.com.myapplication3.designpatterns.visitorpattern.test2;

public class Paper implements Material {
    @Override
    public String accept(Company company) {
        return company.create(this);
    }
}
