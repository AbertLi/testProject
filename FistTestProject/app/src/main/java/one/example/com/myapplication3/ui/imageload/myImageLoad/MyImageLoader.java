package one.example.com.myapplication3.ui.imageload.myImageLoad;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.util.LinkedHashMap;
import java.util.Map;

import one.example.com.myapplication3.Logs;

/**
 * Created by wangxin on 17-8-29.
 */
/*      使用方法
        ImageView iv_type;
        MyImageLoader imageLoader = MyImageLoader.getInstance(this);
                ArrayList<String> urls = new ArrayList<>();
                urls.add("http://img5.imgtn.bdimg.com/it/u=3206533887,2931873948&fm=27&gp=0.jpg");
                urls.add("http://img0.imgtn.bdimg.com/it/u=2138081325,2407756075&fm=27&gp=0.jpg");
                urls.add("http://img4.imgtn.bdimg.com/it/u=196278633,1521334363&fm=27&gp=0.jpg");
                urls.add("http://img3.imgtn.bdimg.com/it/u=3487766690,1195649711&fm=27&gp=0.jpg");
                urls.add("http://img1.imgtn.bdimg.com/it/u=698943482,1934898431&fm=27&gp=0.jpg");
                urls.add("http://img1.imgtn.bdimg.com/it/u=3187985879,2007978564&fm=27&gp=0.jpg");
                urls.add("http://img5.imgtn.bdimg.com/it/u=760482064,3255510007&fm=27&gp=0.jpg");

                for (String u : urls) {
                imageLoader.loadImage(u, iv_type);
                }

*/
public class MyImageLoader {
    private static MyImageLoader mInstance;
    private static final int MAX_CAPACITY = 20;
    private static Context mContext;

    private MyImageLoader(Context context) {
        mContext = context;
    }

    public static MyImageLoader getInstance(Context context) {
        if (mInstance == null) {
            synchronized (MyImageLoader.class) {
                if (mInstance == null) {
                    mInstance = new MyImageLoader(context);
                }
            }
        }
        return mInstance;
    }



    private static LinkedHashMap<String, SoftReference<Bitmap>> firstCacheMap = new LinkedHashMap<String, SoftReference<Bitmap>>(MAX_CAPACITY) //这里设置一个初始大小值.
    {
        //LinkedHashMap每次添加一个新元素进来, 都会回调这个接口, 询问是否要移除掉最老的那个元素.
        @Override
        protected boolean removeEldestEntry(Map.Entry<String, SoftReference<Bitmap>> eldest) {
            if(this.size() > MAX_CAPACITY) {
                //返回true, 表示移除最老的那个元素
                return true;
            }
            // 把最老的那个元素保存到磁盘中
            diskCache(eldest.getKey(), eldest.getValue());
            return false;
        }
    };

    //对外提供的API.
    public void loadImage(String key, ImageView view) {
        synchronized (view) {
            //检查缓存里是否有
            Bitmap bitmap = getFromCache(key);
            if(bitmap != null) {
                //缓存存在, 直接显示
                view.setBackgroundDrawable(new ColorDrawable(Color.GRAY));
                view.setImageBitmap(bitmap);
            } else {
                // 下载
                view.setBackgroundDrawable(new ColorDrawable(Color.GRAY));
                AsyncImageLoaderTask task = new AsyncImageLoaderTask(view);
                task.execute(key);
            }
        }
    }

    private Bitmap getFromCache(String key) {
        //检查内存缓存
        synchronized (firstCacheMap) {
            if(firstCacheMap.get(key) != null) {
                Bitmap bitmap = firstCacheMap.get(key).get();
                if(bitmap != null) {
                    //把这个元素再放进去一次的目的是, 增加它的计数值, 表示它又被访问了一次.
                    firstCacheMap.put(key,new SoftReference<Bitmap>(bitmap));
                    return bitmap;
                }
            }

            //检查磁盘
            Bitmap bitmap = getFromLocalKey(key);
            if (bitmap != null) {
                firstCacheMap.put(key,new SoftReference<Bitmap>(bitmap));
                return bitmap;
            }
            return null;
        }
    }

    // 检查SD卡中是否有该图片
    private Bitmap getFromLocalKey(String key) {
        String filename = MD5Utils.digest(key);
        if (filename == null) {
            return null;
        } else {
            String path = mContext.getFilesDir().getAbsoluteFile().getPath() + filename;
            //如果这个path指定的文件不存在的话, 后面也会return null.
            InputStream is = null;
            try {
                is = new FileInputStream(new File(path));
                Bitmap bitmap = BitmapFactory.decodeStream(is);
                return bitmap;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } finally {
                if (is != null) {
                    try {
                        is.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return null;
    }

    //把图片保存到本地磁盘
    //缓存文件的路径: /data/data/com.hola.weather/cache/539911d002dd6b9e0f23c851e6eb40ce
    private static void diskCache(String key, SoftReference<Bitmap> value) {
        //消息摘要算法
        String fileName = MD5Utils.digest(key);
        String path = mContext.getCacheDir().getAbsolutePath()+"/"+fileName;
        Logs.eprintln("path: " + path);
        FileOutputStream os = null;
        try {
            os = new FileOutputStream(new File(path));
            if(value.get() != null) {
                value.get().compress(Bitmap.CompressFormat.JPEG, 100, os);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if(os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    class AsyncImageLoaderTask extends AsyncTask<String, Void, Bitmap> {
        private ImageView imageView;
        private String key;

        public AsyncImageLoaderTask(ImageView imageView) {
            this.imageView = imageView;
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            this.key = params[0]; //图片的路径
            Bitmap bitmap = download(key);
            return bitmap;
        }

        private Bitmap download(String key) {
            InputStream is = null;
            try {
                is = HttpUtil.download(key);
                return BitmapFactory.decodeStream(is);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (is != null) {
                    try {
                        is.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            if (bitmap != null) {
                addFirstCache(key, bitmap);
                imageView.setImageBitmap(bitmap);
            }
        }
    }

    private void addFirstCache(String key, Bitmap bitmap) {
        if (bitmap != null) {
            synchronized (firstCacheMap) {
                firstCacheMap.put(key, new SoftReference<Bitmap>(bitmap));
            }

        }
    }
}


