package one.example.com.myapplication3.sample.main.ui;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import one.example.com.myapplication3.R;
import one.example.com.myapplication3.databinding.SampleSubjectItem1Binding;
import one.example.com.myapplication3.sample.main.db.Subject;

public class SubjectsAdapter extends RecyclerView.Adapter<SubjectsAdapter.SubjectViewHolder> {

    static class SubjectViewHolder extends RecyclerView.ViewHolder {
        final SampleSubjectItem1Binding mBinding;

        public SubjectViewHolder(SampleSubjectItem1Binding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }
    }

    private List<Subject> mSubects;

    @Nullable
    private final SubjectClickCallback mSubjectClickCallback;

    public SubjectsAdapter(@Nullable SubjectClickCallback clickCallback) {
        mSubjectClickCallback = clickCallback;
    }

    public void setMovies(final List<Subject> newSubjects) {
        if (mSubects == null) {
            mSubects = newSubjects;
            notifyItemRangeInserted(0, mSubects.size());
        }
        else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return mSubects.size();
                }

                @Override
                public int getNewListSize() {
                    return newSubjects.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return TextUtils.equals(mSubects.get(oldItemPosition).getSubjectId(),
                            newSubjects.get(newItemPosition).getSubjectId());
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    Subject newProduct = newSubjects.get(newItemPosition);
                    Subject oldProduct = mSubects.get(oldItemPosition);
                    return TextUtils.equals(newProduct.getSubjectId(), oldProduct.getSubjectId());
                }
            });
            mSubects = newSubjects;
            result.dispatchUpdatesTo(this);
        }
    }

    @Override
    public SubjectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        SampleSubjectItem1Binding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.sample_subject_item1,
                        parent, false);
        binding.setCallback(mSubjectClickCallback);
        return new SubjectViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(SubjectViewHolder holder, int position) {
        holder.mBinding.setSubject(mSubects.get(position));
        holder.mBinding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mSubects == null ? 0 : mSubects.size();
    }
}