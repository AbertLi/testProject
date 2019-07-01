package one.example.com.myapplication3.ui.loadBundle.bundletest;

import androidx.appcompat.app.AppCompatActivity;
import one.example.com.myapplication3.Logs;
import one.example.com.myapplication3.R;
import one.example.com.runtime.plugin.AdvPlugin;
import one.example.com.runtime.plugin.PluginInfo;
import one.example.com.runtime.PluginRt;

import com.albert.interfalib.AssetsUtils;
import com.albert.interfalib.plugin.IPluginInterface;
import com.albert.interfalib.plugin.IPluginViewManager;

import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * r_t  用于实现加载apk插件的包的中间件。
 * plugin  插件。
 */
public class BundleTestActivity extends AppCompatActivity {
    private String TAG = "BundleTestActivity";
    private RelativeLayout rl_parent;
    private EditText editText;
    private String defultPackageId = "com.test.bundleone";
    private String fileName = "plugin_three.apk";
    String pluginPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bundle_test);
        rl_parent = findViewById(R.id.rl_parents);
        editText = findViewById(R.id.et_package_Id);
        editText.setText(defultPackageId);
        pluginPath = this.getExternalFilesDir("file").getAbsolutePath();
    }

    public void btn(View view) {
        switch (view.getId()) {
            case R.id.btn_showWiew:
                String packAgeId = editText.getText().toString().trim();
                Pair<AdvPlugin, String> plugins1 = PluginRt.getAdvPlugin(packAgeId);

                rl_parent.addView(null);
                break;
            case R.id.btn_loadPluginInfo:
//                //数据下发到安装成功过整个过程
//                List<PluginInfo>list = getAssetsListPluginInfo();
//                Logs.eprintln("PluginInfo toString = "+list.toString());
//                PluginRt.savePluginToInfos(list);

                //单个插件安装
                copyAssetsFileToAppFiles(fileName, fileName);

                Logs.iprintln(TAG, "pluginPath = " + pluginPath+File.separator+fileName);
                Pair<AdvPlugin, String> plugins2 = PluginRt.install(pluginPath+File.separator+fileName);
                View view1 = null;
                if (plugins2 != null && plugins2.first != null) {
                    IPluginInterface iPluginInterface = plugins2.first.getPluginInterface();
                    IPluginViewManager manager =
                            iPluginInterface.inflate(this, plugins2.first.getPluginContext());
                    view1 = manager.getRootView();
                    rl_parent.addView(view1);
                }
                else {
                    Logs.eprintln(TAG, "plugin2 = null   error = " + plugins2 != null ? plugins2.second : " null ");
                }
                break;
        }
    }


    /**
     * 从asset中获取bundleInfo数据，或者从服务器中拉去bundleInfo数据。
     *
     * @return
     */
    public List<PluginInfo> getAssetsListPluginInfo() {
        List<PluginInfo> list = new ArrayList<>();
        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray(AssetsUtils.getStringFromAssert(this, "plugin_info"));
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsoninfo = jsonArray.getJSONObject(i);
                PluginInfo info = new PluginInfo();
                if (jsoninfo != null) {
                    if (!jsoninfo.isNull("packageId")) {
                        info.setPackageId(jsoninfo.getString("packageId"));
                    }
                    if (!jsoninfo.isNull("pluginName")) {
                        info.setPluginName(jsoninfo.getString("pluginName"));
                    }
                    if (!jsoninfo.isNull("adapterActivityName")) {
                        info.setAdapterActivityName(jsoninfo.getString("adapterActivityName"));
                    }
                    if (!jsoninfo.isNull("versionCode")) {
                        info.setVersionCode(jsoninfo.getString("versionCode"));
                    }
                    if (!jsoninfo.isNull("hostVersionCode")) {
                        info.setHostVersionCode(jsoninfo.getString("hostVersionCode"));
                    }
                    if (!jsoninfo.isNull("fingerprint")) {
                        info.setFingerprint(jsoninfo.getString("fingerprint"));
                    }
                    if (!jsoninfo.isNull("downLoadUrl")) {
                        info.setDownLoadUrl(jsoninfo.getString("downLoadUrl"));
                    }
                    if (!jsoninfo.isNull("isOffMarket")) {
                        info.setOffMarket(jsoninfo.getBoolean("isOffMarket"));
                    }
                    if (!jsoninfo.isNull("isAutoInstall")) {
                        info.setAutoInstall(jsoninfo.getBoolean("isAutoInstall"));
                    }
                    list.add(info);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


    /**
     * 从assets目录中复制某文件内容
     *
     * @param assetFileName assets目录下的Apk源文件路径
     * @param newFileName   复制到/data/data/package_name/files/目录下文件名
     */
    private void copyAssetsFileToAppFiles(String assetFileName, String newFileName) {
        InputStream ism = null;
        FileOutputStream fos = null;
        int buffsize = 1024;

        try {
            ism = this.getAssets().open(assetFileName);
//            fos = this.openFileOutput(newFileName, Context.MODE_PRIVATE);
            fos = new FileOutputStream(new File(pluginPath+File.separator+newFileName));
            int byteCount = 0;
            byte[] buffer = new byte[buffsize];
            byteCount = ism.read(buffer);
            while (byteCount != -1) {
                fos.write(buffer, 0, byteCount);
                byteCount = ism.read(buffer);
            }
            fos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                ism.close();
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
