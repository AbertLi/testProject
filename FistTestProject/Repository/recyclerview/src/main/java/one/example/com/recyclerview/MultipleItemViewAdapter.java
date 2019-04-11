package one.example.com.recyclerview;

import android.view.ViewGroup;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import one.example.com.data.BaseData;


/**
 * 多种ItemView的适配器
 */
public class MultipleItemViewAdapter<D extends BaseData> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<D> mListInfo = new ArrayList<>();
    public Map<Integer, Map<String, Object>> viewTypeCallBackMap = new HashMap<>();

//    public MultipleItemViewAdapter(Object callBack) {
//        Map<String, Object> mapCallBack = new HashMap<>();
//        mapCallBack.put("clickCallBack", callBack);
//        viewTypeCallBackMap.put(ItemViewTypeManager.DEFULT_ITEM, mapCallBack);
//        viewTypeCallBackMap.put(ItemViewTypeManager.AD_ITEM, mapCallBack);
//    }

    /**
     * 刷新
     *
     * @param mListInfo
     */
    public void setmListInfo(List<D> mListInfo) {
        this.mListInfo = mListInfo;
        notifyDataSetChanged();
    }

    /**
     * 追加
     */
    public void addListInfo(List<D> listInfo) {
        if (listInfo == null || listInfo.isEmpty()) {
            return;
        }

        if (mListInfo == null || mListInfo.isEmpty()) {
            this.mListInfo = listInfo;
            notifyItemChanged(listInfo.size());
        }else {
            this.mListInfo.addAll(listInfo);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AbstractView abstractView = ItemViewTypeManager.getInstance().inflate(viewType, parent);
        return getViewHolder(abstractView);
    }

    //直接创建实例
    public RecyclerView.ViewHolder getViewHolder(AbstractView abstractView) {
        return new CommonViewHolder(abstractView);
    }

    //java反射创建实例
    public RecyclerView.ViewHolder newClass2ViewHolder(Class cla, AbstractView abstractView) {
        RecyclerView.ViewHolder viewHolder = null;
        try {
            Constructor c = cla.getConstructor(AbstractView.class);
            viewHolder = (RecyclerView.ViewHolder) c.newInstance(abstractView);
        } catch (Exception e) {

        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        ItemViewTypeManager.getInstance().onBindViewHolder(holder, viewType, position, mListInfo.get(position),
                viewTypeCallBackMap.get(viewType));
    }

    @Override
    public int getItemCount() {
        return mListInfo != null ? mListInfo.size() : 0;
    }

    @Override
    public int getItemViewType(int position) {
        D infoD = mListInfo.get(position);
        return infoD != null ? infoD.getViewType() : 0;
    }
}
