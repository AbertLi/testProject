package one.example.com.myapplication3.ui.bindings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import one.example.com.myapplication3.R;
import one.example.com.myapplication3.databinding.FragMainBinding;

public class FragmentHome extends Fragment {
    FragMainBinding binding;
    View v;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate( inflater, R.layout.frag_main, container, false );
        v = binding.getRoot();
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated( savedInstanceState );
        v.findViewById( R.id.btn_showName ).setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        } );

    }
}