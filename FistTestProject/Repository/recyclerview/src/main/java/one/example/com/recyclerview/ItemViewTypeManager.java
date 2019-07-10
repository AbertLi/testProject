package one.example.com.recyclerview;

import android.content.Context;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import one.example.com.data.BaseData;

public class ItemViewTypeManager {
    Map<Integer, BaseViewItem> viewTypeViewItem = new HashMap<>();

    public void registerItem(int key, BaseViewItem item) {
        viewTypeViewItem.put(key, item);
    }

    static class Holder {
        public static ItemViewTypeManager mItemViewTypeManager = new ItemViewTypeManager();
    }

    public static ItemViewTypeManager getInstance() {
        return Holder.mItemViewTypeManager;
    }

    public AbstractView inflate(int viewType, ViewGroup parent, Context con) {
        return viewTypeViewItem.get(viewType).inflate(parent,con);
    }

    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int viewType, int position,
            BaseData dataInfo, Map<String, Object> parm) {
        viewTypeViewItem.get(viewType).onBindViewHolder(holder, position, dataInfo, parm);
    }

}
