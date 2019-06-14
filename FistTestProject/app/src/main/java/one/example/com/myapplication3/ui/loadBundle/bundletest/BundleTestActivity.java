package one.example.com.myapplication3.ui.loadBundle.bundletest;

import androidx.appcompat.app.AppCompatActivity;
import one.example.com.myapplication3.Logs;
import one.example.com.myapplication3.R;
import one.example.com.runtime.plugin.PluginInfo;
import one.example.com.runtime.PluginRt;
import com.albert.interfalib.AssetsUtils;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * r_t  用于实现加载apk插件的包的中间件。
 * plugin  插件。
 */
public class BundleTestActivity extends AppCompatActivity {
    private RelativeLayout rl_parent;
    private EditText editText;
    private String defultPackageId = "com.test.bundleone";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bundle_test);
        rl_parent = findViewById(R.id.rl_parents);
        editText = findViewById(R.id.et_package_Id);
        editText.setText(defultPackageId);
    }

    public void btn(View view) {
        switch (view.getId()) {
            case R.id.btn_showWiew:
                String packAgeId = editText.getText().toString().trim();


                rl_parent.addView(null);
                break;
            case R.id.btn_loadPluginInfo:
                List<PluginInfo>list = getAssetsListPluginInfo();
                Logs.eprintln("PluginInfo toString = "+list.toString());
                PluginRt.saveBundleInfos(list);
                break;
        }
    }


    /**
     * 从asset中获取bundleInfo数据，或者从服务器中拉去bundleInfo数据。
     * @return
     */
    public List<PluginInfo> getAssetsListPluginInfo(){
        List<PluginInfo> list = new ArrayList<>();
        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray(AssetsUtils.getStringFromAssert(this,"plugin_info"));
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsoninfo = jsonArray.getJSONObject(i);
                PluginInfo info= new PluginInfo();
                if (jsoninfo!=null){
                    if (!jsoninfo.isNull("packageId")){
                        info.setPackageId(jsoninfo.getString("packageId"));
                    }
                    if (!jsoninfo.isNull("pluginName")){
                        info.setPluginName(jsoninfo.getString("pluginName"));
                    }
                    if (!jsoninfo.isNull("adapterActivityName")){
                        info.setAdapterActivityName(jsoninfo.getString("adapterActivityName"));
                    }
                    if (!jsoninfo.isNull("versionCode")){
                        info.setVersionCode(jsoninfo.getString("versionCode"));
                    }
                    if (!jsoninfo.isNull("hostVersionCode")){
                        info.setHostVersionCode(jsoninfo.getString("hostVersionCode"));
                    }
                    if (!jsoninfo.isNull("fingerprint")){
                        info.setFingerprint(jsoninfo.getString("fingerprint"));
                    }
                    if (!jsoninfo.isNull("downLoadUrl")){
                        info.setDownLoadUrl(jsoninfo.getString("downLoadUrl"));
                    }
                    if (!jsoninfo.isNull("isOffMarket")){
                        info.setOffMarket(jsoninfo.getBoolean("isOffMarket"));
                    }
                    if (!jsoninfo.isNull("isAutoInstall")){
                        info.setAutoInstall(jsoninfo.getBoolean("isAutoInstall"));
                    }
                    list.add(info);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
}
