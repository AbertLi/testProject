package one.example.com.myapplication3.ui.wheelview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;
import java.util.List;

import one.example.com.myapplication3.R;
import timber.log.Timber;

public class ViewPager1Adapter extends PagerAdapter {
    private static final int MAX_SIZE = 2;
    private List<String> mData = new ArrayList<>();
    private List<CardView> mViewCaches = new ArrayList<>();
    private Context mContext;

    public ViewPager1Adapter(Context context) {
        this.mContext = context;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Timber.d("instantiateItem position=" + position);
        CardView card = null;
        ViewHolder holder = null;
        if (mViewCaches.size() == 0) {
            card = (CardView) LayoutInflater.from(mContext).inflate(R.layout.view_pager_item, null);
            holder = initHolder(card);
            card.setTag(holder);
        } else {
            card = mViewCaches.remove(0);
            holder = (ViewHolder) card.getTag();
        }
        setData(holder, position);
        container.addView(card);
        return card;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        Timber.d("destroyItem position =" + position);
        if (mViewCaches.size() < MAX_SIZE) {
            mViewCaches.add((CardView) object);
        }
        container.removeView((CardView) object);
    }

    @Override
    public int getCount() {
        Timber.d("getCount");
        return mData.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        Timber.d("isViewFromObject");
        return view == object;
    }

    public ViewHolder initHolder(CardView card) {
        ViewHolder holder = new ViewHolder();
        holder.mIcon = card.findViewById(R.id.icon);
        holder.mName = card.findViewById(R.id.content);
        return holder;
    }

    public void setDate(List<String> mData) {
        this.mData = mData;
        notifyDataSetChanged();
    }

    public void addDate(String itemData) {
        this.mData.add(itemData);
        notifyDataSetChanged();
    }

    private void setData(ViewHolder holder, int position) {
        holder.mName.setText(mData.get(position));
    }

    class ViewHolder {
        ImageView mIcon;
        TextView mName;
    }
}
