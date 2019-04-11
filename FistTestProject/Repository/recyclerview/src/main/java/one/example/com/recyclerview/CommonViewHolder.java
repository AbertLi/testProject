package one.example.com.recyclerview;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CommonViewHolder extends RecyclerView.ViewHolder {
    AbstractView mAbstractView;

    public CommonViewHolder(@NonNull AbstractView itemView) {
        super(itemView.getRootView());
        mAbstractView = itemView;
    }

    public AbstractView getmAbstractView() {
        return mAbstractView;
    }
}
