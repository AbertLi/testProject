package one.example.com.myapplication3.bindings.list;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import org.jetbrains.annotations.Nullable;

import androidx.databinding.DataBindingUtil;
import one.example.com.myapplication3.R;
import one.example.com.myapplication3.bindings.PersonBean;
import one.example.com.myapplication3.databinding.ActivityListBinding;


/**
 * 1，通过Binding框架实现绑定Activity功能
 */
public class ListActivity extends Activity {
    private ActivityListBinding binding;
    PersonBean select;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        binding = DataBindingUtil.setContentView( this, R.layout.activity_list );

        if (select == null) {
            select = new PersonBean( "暂时么有选择", "" );
        }

        String[] strData = {"AAA", "BBB", "CCC", "DDD", "Jack", "Mary", "Fox", "Albert", "Jason", "FFF", "ggg"};
        for (int j = 0; j < strData.length; j++) {
            PersonBean personBean = new PersonBean( strData[j], "" + (21 + j) );
        }
    }
}
