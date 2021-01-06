package one.example.com.myapplication3.designpatterns.strategy;

public class KitChiken {
    private CrabCooking cooking;

    public void setStrategy(CrabCooking cooking) {
        this.cooking = cooking;
    }

    public CrabCooking getStrategy() {
        return this.cooking;
    }

    public void cookingMethod() {
        this.cooking.cookingMethod();
    }
}
