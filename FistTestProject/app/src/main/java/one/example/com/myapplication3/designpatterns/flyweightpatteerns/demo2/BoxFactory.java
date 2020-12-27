package one.example.com.myapplication3.designpatterns.flyweightpatteerns.demo2;

import java.util.HashMap;
import java.util.Map;
//享元工厂类
public class BoxFactory {
    private Map<String, AbstractBox> map = new HashMap();
    private static BoxFactory boxFactory = new BoxFactory();

    public static BoxFactory getInstance() {
        return boxFactory;
    }

    public AbstractBox getShare(String box) {
        return map.get(box);
    }

    private BoxFactory()
    {
        map.put("O", new OBox());
        map.put("P", new PBox());
        map.put("Q", new QBox());
    }
}
