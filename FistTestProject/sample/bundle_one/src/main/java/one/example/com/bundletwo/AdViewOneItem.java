package one.example.com.bundletwo;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Map;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import one.example.com.bundleone.R;
import one.example.com.recyclerview.AbstractView;
import one.example.com.data.BaseData;
import one.example.com.recyclerview.BaseViewItem;

/**
 * 定制动态下发的样式 1
 */
public class AdViewOneItem implements BaseViewItem {
    private Context mContext;
    public AdViewOneItem(Context context){
        mContext = context;
    }

    @Override
    public AbstractView inflate(ViewGroup viewGroup) {
        View root = LayoutInflater.from(mContext).inflate(R.layout.adview_one_item, viewGroup, false);
        return new AbstractView(root);
    }

    @Override

    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position,
            BaseData info, Map<String, Object> callback) {
    }
}
