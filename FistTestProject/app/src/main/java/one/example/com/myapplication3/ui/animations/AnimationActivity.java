package one.example.com.myapplication3.ui.animations;

import androidx.fragment.app.FragmentActivity;
import one.example.com.myapplication3.R;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;


public class AnimationActivity extends FragmentActivity {
    //https://blog.csdn.net/ruancoder/article/details/52357566
    View v = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        v = findViewById(R.id.view);
    }


    public void btn(View view) {
        /**
         * 使用java代码的方式创建ScaleAnimation，传入八个参数，fromX、toX、fromY、toY和pivotXType、pivotXValue、pivotYType、pivotYValue，使用如下构造方法。
         * 参数说明：
         * fromX：动画开始前在X坐标的大小。
         * toX：动画结束后在X坐标的大小。
         * fromY：动画开始前在Y坐标的大小。
         * toY：动画结束后在Y坐标的大小。
         * pivotXType：缩放中心点的X坐标类型。取值范围为ABSOLUTE、RELATIVE_TO_SELF、RELATIVE_TO_PARENT。
         * pivotXValue：缩放中心点的X坐标值。当pivotXType==ABSOLUTE时，表示绝对位置；否则表示相对位置，1.0表示100%。
         * pivotYType：缩放中心点的Y坐标类型。
         * pivotYValue：缩放中心点的Y坐标。
         * ---------------------
         * 作者：ruancoder
         * 来源：CSDN
         * 原文：https://blog.csdn.net/ruancoder/article/details/52357566
         * 版权声明：本文为博主原创文章，转载请附上博文链接！
         */
        ScaleAnimation animation = new ScaleAnimation(100f, 100f, 100f, -900f, Animation.ABSOLUTE, 50f,
                Animation.ABSOLUTE,
                50f);
        ScaleAnimation sato0 = new ScaleAnimation(1,0,1,1, Animation.RELATIVE_TO_PARENT,0.5f,
                Animation.RELATIVE_TO_PARENT,0.5f);
        animation.setDuration(3000);
        v.startAnimation(sato0);
    }


}
