package one.example.com.myapplication3.bindings;

import android.os.Bundle;

import org.jetbrains.annotations.Nullable;

import androidx.fragment.app.FragmentActivity;
import one.example.com.myapplication3.R;

public class ActivityTestFragment extends FragmentActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_fragment );

        FragmentHome f = new FragmentHome();
        getSupportFragmentManager().beginTransaction().replace( R.id.fl, f ).commit();
    }
}
