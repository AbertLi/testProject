package one.example.com.data;

//抽象数据实现类
public class DataInfo implements BaseData {
    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

    @Override
    public int getViewType() {
        return 0;
    }

    private String id;
    private int viewType;


}
