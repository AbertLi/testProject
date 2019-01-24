package one.example.com.myapplication3.ui.bindings.list;

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
import one.example.com.myapplication3.databinding.FamilyRecyclerviewItemBinding;
import one.example.com.myapplication3.modle.IFamily;

public class FamilyListAdapter extends RecyclerView.Adapter<FamilyListAdapter.PersonViewHolper> {
    private List<? extends IFamily> beanList;
    private final IFamilyCallBack mClickCallBack;

    public FamilyListAdapter(IFamilyCallBack clickCallBack) {
        mClickCallBack = clickCallBack;
        setHasStableIds( true );//为了使 url 没变的时候刷新RecyclerView时ImageView 不重新加载
    }

    public void addFamily(List<? extends IFamily> list) {
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
                    IFamily newProduct = list.get( newItemPosition );
                    IFamily oldProduct = beanList.get( oldItemPosition );
                    return newProduct.getId() == oldProduct.getId() && newProduct.getPersonId() == oldProduct.getPersonId()
                            && Objects.equals( newProduct.getAge(), oldProduct.getAge() )
                            && Objects.equals( newProduct.getText(), oldProduct.getText() )
                            && Objects.equals( newProduct.getLike(), oldProduct.getLike() );
                }
            } );
            beanList = list;
            result.dispatchUpdatesTo( this );
        }
    }

    @NonNull
    @Override
    public PersonViewHolper onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        FamilyRecyclerviewItemBinding binding = DataBindingUtil.inflate( LayoutInflater.from( parent.getContext() ), R.layout.family_recyclerview_item, parent, false );
        binding.setCallback( mClickCallBack );
        return new PersonViewHolper( binding );
    }


    @Override
    public void onBindViewHolder(@NonNull PersonViewHolper holder, int position) {
        holder.mBinding.setFamily( beanList.get( position ) );
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
        final FamilyRecyclerviewItemBinding mBinding;

        public PersonViewHolper(FamilyRecyclerviewItemBinding binding) {
            super( binding.getRoot() );
            this.mBinding = binding;
        }
    }
}
