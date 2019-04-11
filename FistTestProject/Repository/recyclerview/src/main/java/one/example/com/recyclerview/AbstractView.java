package one.example.com.recyclerview;

import android.view.View;

public class AbstractView {
    private View mView;

    public AbstractView(View view) {
        mView = view;
    }

    public View getRootView() {
        return mView;
    }
}
