package one.example.com.myapplication3.sample.main.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.ViewModelProviders;
import one.example.com.myapplication3.R;
import one.example.com.myapplication3.databinding.SampleFragment1Binding;
import one.example.com.myapplication3.sample.main.db.Subject;
import one.example.com.myapplication3.sample.main.viewmodel.SampleViewModel;

public class SampleFragment extends Fragment {

    public static final String TAG = "ProductListViewModel";
    private SubjectsAdapter mProductAdapter;
    private SampleFragment1Binding mBinding;
    private SampleViewModel mViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.sample_fragment1, container, false);
        mProductAdapter = new SubjectsAdapter(mProductClickCallback);
        mBinding.productsList.setAdapter(mProductAdapter);
        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(SampleViewModel.class);
        // 在SampleFragment的生命周期内监听数据， 如果this销毁了，会自动解除监听
        mViewModel.getMovies().observe(this, this::onMovieChanged); // 监听数据
    }

    private void onMovieChanged(@Nullable List<Subject> movies) {
        if(movies != null) {
            // 显示ui
            mBinding.setIsLoading(false);
            mProductAdapter.setMovies(movies);
        } else {
            // 隐藏ui
            mBinding.setIsLoading(true);
        }
    }


    private final SubjectClickCallback mProductClickCallback = new SubjectClickCallback() {
        @Override
        public void onClick(Subject product) {
            if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
                Toast.makeText(getActivity(), "Click: " + Arrays.toString(product.getTitles()), Toast.LENGTH_LONG).show();
                mViewModel.refresh();
            }
        }
    };


}