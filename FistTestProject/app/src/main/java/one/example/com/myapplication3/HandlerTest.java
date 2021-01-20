package one.example.com.myapplication3;


import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

public class HandlerTest {
    //请求发送到哪里去了。请求发送到了MessageQueue中，并使用单链表存储。
       //什么是单链表：不要求内存连续。存的时候随机，由n个节点组成，取的时候按顺序取值，通过next指针关联起来。

    //请求如何被消费的。
    //支线：设计思想，数据结构，什么是内存泄漏。怎么解决，有哪些
    //    //Looper，MessageQueue，Message,Handler直接的关系。方案。方案优缺点。
    //垃圾标记算法。 可达性分析算法 GCRoot

    Handler handler = new MyHandler();

    class MyHandler extends Handler {
        public void handleMessage(@NonNull Message msg) {

        }
    }


    public void runTest() {
        handler.sendEmptyMessageDelayed(1, 10);
    }
}
