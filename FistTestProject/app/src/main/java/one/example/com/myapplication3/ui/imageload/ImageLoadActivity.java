package one.example.com.myapplication3.ui.imageload;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import one.example.com.myapplication3.Logs;
import one.example.com.myapplication3.R;
import one.example.com.myapplication3.databinding.ActivityImageloadBinding;
import one.example.com.myapplication3.ui.imageload.myImageLoad.MyImageLoader;

public class ImageLoadActivity extends Activity {
    ActivityImageloadBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_imageload);



        ArrayList<String> urls = new ArrayList<>();
        urls.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1576737327684&di=b8e5b700b2709000aa4487dd7041017c&imgtype=0&src=http%3A%2F%2F5b0988e595225.cdn.sohucs.com%2Fimages%2F20170927%2Fdbf49ecf74bf4101bcddeb08b2fc933f.jpeg");
        urls.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1576737327658&di=00dae384d9a29888310e5210ef3bed42&imgtype=0&src=http%3A%2F%2Fimg.bbs.cnhubei.com%2Fforum%2F201108%2F16%2F085316egdevqvu99usse90.jpg");
        urls.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1576737327730&di=d6441ed850f4cfd2a1d42b81803b165b&imgtype=0&src=http%3A%2F%2F04imgmini.eastday.com%2Fmobile%2F20190213%2F20190213105811_9b2c9fcad033984e0608f4876e2665b8_1.jpeg");
        urls.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1577345680&di=ebc2894aa1a71d449fd1dc62dcd6b178&imgtype=jpg&er=1&src=http%3A%2F%2Fwww.qqoi.cn%2Fimg_bizhi%2F11407462.jpeg");
        urls.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1576750967497&di=84be22b28b453408dfbca8e04e7ce3e1&imgtype=0&src=http%3A%2F%2Fp5.so.qhimgs1.com%2Ft01f14831e43cc5900b.jpg");
        urls.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1576750967496&di=990853bbd67545b560cb3fb9bd404e3e&imgtype=0&src=http%3A%2F%2Fpic1.win4000.com%2Fwallpaper%2F2018-05-31%2F5b0f625a33e23.jpg");
        urls.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1576750967495&di=2d2fd0b03f12406713876121b93f205d&imgtype=0&src=http%3A%2F%2Fpic.90sjimg.com%2Fdesign%2F00%2F40%2F57%2F65%2F561b65fd089e6.jpg");
        urls.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1576750967493&di=9228295af6f907b45bc82fec19bd6f6c&imgtype=0&src=http%3A%2F%2Fi0.hdslb.com%2Fbfs%2Farticle%2F0dd370c37088b921f7fac26da1a6290fe85f1143.jpg");
        urls.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1576750967490&di=ac4ab55cf0611a1ebf6914b686d2c966&imgtype=0&src=http%3A%2F%2Fi2.hdslb.com%2Fbfs%2Farchive%2Fab28eb2b4cd574df5a4d3be15ce57155f32143bf.jpg");
        urls.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1576750967482&di=f3ebf2f1260311fcceed858eeae954aa&imgtype=0&src=http%3A%2F%2Fup.zhuoku.org%2Fpic_source%2Fc9%2Fed%2Ff2%2Fc9edf2a1168dc0f56a86b053ae1322d3.jpg");
        urls.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1576750967479&di=413467b1b7206b3cf1518c71673541e6&imgtype=0&src=http%3A%2F%2Fimg.game333.net%2Fuploads%2Fallimg%2F140429%2F9-14042Z91034.jpg");
        urls.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1576750967477&di=de6732661026f67f2839e2fa0e848f58&imgtype=0&src=http%3A%2F%2Fpic1.win4000.com%2Fwallpaper%2F2018-05-31%2F5b0f6259254fa.jpg");
        urls.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1576750967474&di=a44d900634a9fc320d3ce16eda249130&imgtype=0&src=http%3A%2F%2Fimg.pconline.com.cn%2Fimages%2Fupload%2Fupc%2Ftx%2Fitbbs%2F1608%2F12%2Fc16%2F25464919_1470973339612_1024x1024.png");
        urls.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1576750967463&di=f01e78a7fd343b10549dc76a8c1fc491&imgtype=0&src=http%3A%2F%2Fp0.qhimgs4.com%2Ft010faaec8ae2adde1d.jpg");
        urls.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1576750967458&di=87cbaa2e86e9e9422f325ad08eec9f68&imgtype=0&src=http%3A%2F%2Fpic1.win4000.com%2Fwallpaper%2F2018-05-31%2F5b0f625d42f3a.jpg");
        urls.add("");
        urls.add("");
        urls.add("");
        urls.add("");
        urls.add("");
        urls.add("");
        MyImageLoader imageLoader = MyImageLoader.getInstance(this);
        binding.btnDownloadImage.setOnClickListener(view -> {

            for (String u : urls) {
                imageLoader.loadImage(u, binding.iv);
            }
        });
        binding.btnShowCash.setOnClickListener(view->{
            Handler handler = new Handler(){
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    imageLoader.loadImage(urls.get(msg.what), binding.iv);
                }
            };
           new Thread(){
               @Override
               public void run() {
                   super.run();
                   for (int i = 0; i < urls.size(); i++) {
                       handler.sendEmptyMessage(i);
                       try {
                           Thread.sleep(3000);
                       } catch (InterruptedException e) {
                           e.printStackTrace();
                       }
                   }
               }
           }.start();
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Logs.eprintln("BaseApplication  ImageLOADACTIVITY " + "@Override onDestroy()");
    }
}

