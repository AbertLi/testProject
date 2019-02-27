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
import one.example.com.myapplication3.db.entity.PersonEntity;
import one.example.com.myapplication3.utile.MyBusEven;

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
                MyBusEven.getInstance().with("lala").postValue("东临碣石，以观沧海。");
            }
        } );

        PersonEntity entity=new PersonEntity("今天是星期一","热评");
        //，明天星期二，后天星期三。在TextView中要显示HTML文字是比较轻松的事，但是在其中混上图片就变的复杂了起来。本文使用Glide作为图片加载工具。
        binding.setPerson(entity);
    }
}
