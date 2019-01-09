package one.example.com.myapplication3.bindings.list;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import one.example.com.myapplication3.R;
import one.example.com.myapplication3.modle.IPersonBean;
import one.example.com.myapplication3.databinding.PersonRecyclerviewItemBinding;

public class PersonListAdapter extends RecyclerView.Adapter<PersonListAdapter.PersonViewHolper> {
    private List<? extends IPersonBean> beanList;
    private final IPersonClickCallBack mClickCallBack;

    public PersonListAdapter(IPersonClickCallBack clickCallBack) {
        mClickCallBack = clickCallBack;
        setHasStableIds( true );//为了使 url 没变的时候刷新RecyclerView时ImageView 不重新加载
    }

    public void addPersonList(List<? extends IPersonBean> list) {
        if (beanList == null) {
            beanList = list;
            notifyItemRangeInserted( 0, beanList.size() );
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff( new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return beanList.size();
                }

                @Override
                public int getNewListSize() {
                    return list.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return beanList.get( oldItemPosition ).getId() == list.get( newItemPosition ).getId();
                }

                @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    IPersonBean newProduct = list.get( newItemPosition );
                    IPersonBean oldProduct = beanList.get( oldItemPosition );
                    return newProduct.getId() == oldProduct.getId() && Objects.equals( newProduct.getAge(), oldProduct.getAge() ) && Objects.equals( newProduct.getName(), oldProduct.getName() );
                }
            } );
            beanList = list;
            result.dispatchUpdatesTo( this );
        }
    }

    @NonNull
    @Override
    public PersonViewHolper onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PersonRecyclerviewItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.person_recyclerview_item, parent, false );
        binding.setCallback( mClickCallBack );
        return new PersonViewHolper( binding );
    }


    @Override
    public void onBindViewHolder(@NonNull PersonViewHolper holder, int position) {
        holder.mBinding.setPersonBean( beanList.get( position ) );
        holder.mBinding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return beanList == null ? 0 : beanList.size();
    }

    @Override
    public long getItemId(int position) {
        return beanList.get( position ).getId();
    }

    static class PersonViewHolper extends RecyclerView.ViewHolder {
        final PersonRecyclerviewItemBinding mBinding;

        public PersonViewHolper(PersonRecyclerviewItemBinding binding) {
            super( binding.getRoot() );
            this.mBinding = binding;
        }
    }
}
