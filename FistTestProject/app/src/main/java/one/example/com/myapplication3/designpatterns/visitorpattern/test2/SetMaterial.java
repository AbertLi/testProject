package one.example.com.myapplication3.designpatterns.visitorpattern.test2;

import java.util.ArrayList;
import java.util.List;

public class SetMaterial {
    static class Holder {
        static SetMaterial material = new SetMaterial();
    }

    public static SetMaterial getInstance() {
        return Holder.material;
    }

    List<Material> list = new ArrayList();

    public String accept(Company company) {
        String tem = "";
        for (Material ma : list) {
            tem += ma.accept(company) + "   ";
        }
        return tem;
    }

    public void addMaterial(Material material) {
        list.add(material);
    }

    public void removeMaterrial(Material element) {
        list.remove(element);
    }
}
