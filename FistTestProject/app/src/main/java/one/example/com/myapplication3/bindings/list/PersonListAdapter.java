package one.example.com.myapplication3.bindings.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import one.example.com.myapplication3.R;

public class PersonListAdapter extends RecyclerView.Adapter<PersonListAdapter.PersonViewHolper> {

    IPersonClickCallBack mClickCallBack;

    public PersonListAdapter(IPersonClickCallBack clickCallBack) {
        mClickCallBack = clickCallBack;
        setHasStableIds( true );//为了使 url 没变的时候刷新RecyclerView时ImageView 不重新加载
    }

    @NonNull
    @Override
    public PersonViewHolper onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        DataBindingUtil.inflate( LayoutInflater.from( parent.getContext() ), R.layout.penson_recyclerview_item, parent, false );
        return new PersonViewHolper( parent );
    }

    @Override
    public void onBindViewHolder(@NonNull PersonViewHolper holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }


    @Override
    public long getItemId(int position) {
        return super.getItemId( position );
    }

    static class PersonViewHolper extends RecyclerView.ViewHolder {
        //        PersonRecyclerviewItemBinding binding
        public PersonViewHolper(View view) {
            super( view );
//            super( binding.getRoot() );
//            mBinding = binding;
        }
    }
}
