package one.example.com.recyclerview;

import android.content.Context;
import android.view.ViewGroup;

import java.util.Map;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import one.example.com.data.BaseData;

public interface BaseViewItem {
    public AbstractView inflate(ViewGroup viewGroup, Context context);

    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position, BaseData info, Map<String,
            Object> callback);

}
