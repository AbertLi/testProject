package one.example.com.myapplication3.ui.rxjava

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import io.reactivex.ObservableSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.annotations.NonNull
import io.reactivex.functions.BiFunction
import io.reactivex.functions.Consumer
import io.reactivex.functions.Function
import io.reactivex.functions.Function3
import io.reactivex.schedulers.Schedulers
import one.example.com.myapplication3.Logs
import one.example.com.myapplication3.R
import java.util.ArrayList
import java.util.concurrent.TimeUnit

class RxJavaTestActivity : Activity(){
    private val TAG = "RxZipActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rxjava_test)
    }


    @SuppressLint("CheckResult")
    fun btn(view: View){
        //Observable.create
/*        Observable.create<String> {
            it.onNext("一")
            it.onNext("二")
            it.onNext("三")
        }.subscribe(object: Observer<String>{
            override fun onComplete() {
                Logs.eprintln(" onComplete= ")
            }

            override fun onSubscribe(d: Disposable) {
                Logs.eprintln(" onSubscribe d= "+d.isDisposed)
            }

            override fun onNext(t: String) {
                Logs.eprintln(" onNext t= "+t)
            }

            override fun onError(e: Throwable) {
                Logs.eprintln(" e= "+e.message)
            }
        })*/


       //      Single.create
 /*       Single.create<String>{
            it.onSuccess("输入的字付串")
        }.subscribe(object :SingleObserver<String>{
            override fun onSuccess(t: String) {
                Logs.eprintln("onSuccess t= "+t)
            }

            override fun onSubscribe(d: Disposable) {
                Logs.eprintln("d isDisposed= "+d.isDisposed)
            }

            override fun onError(e: Throwable) {
                Logs.eprintln("e onError= "+e.message)
            }
        })*/
        //      zip
     /*   Observable.zip(getStringObservable(), getIntegerObservable(), object : BiFunction<String, Int, String> {
            @Throws(Exception::class)
            override fun apply(@NonNull s: String, @NonNull integer: Int): String {
                return s + integer!!
            }
        }).subscribe { s ->
            Logs.eprintln(TAG, "zip : accept : $s\n")
        }

        Observable.zip(getStringObservable(),getStringObservable2(),getIntegerObservable(),object: Function3<String,
                String,Int,String
                >{
            override fun apply(t1: String, t2: String, t3: Int): String {
               return t1+t2+t3
            }

        }).subscribe { s->
            Logs.eprintln(TAG, "zip : accept : $s\n")
        }*/


        //flatMap
        Observable.create(ObservableOnSubscribe<Int> { e ->
            e.onNext(1)
            e.onNext(2)
            e.onNext(3)
        }).flatMap(object : Function<Int, ObservableSource<String>> {
            @Throws(Exception::class)
            override fun apply(@NonNull integer: Int): ObservableSource<String> {
                val list = ArrayList<String>()
                for (i in 0..2) {
                    list.add("I am value " + integer!!)
                }
                val delayTime = (1 + Math.random() * 10).toInt()
                return Observable.fromIterable(list).delay(delayTime.toLong(), TimeUnit.MILLISECONDS)
            }
        }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { s ->
                    Log.e(TAG, "flatMap : accept : $s\n")
                }
    }











     private fun getStringObservable(): Observable<String> {
        return Observable.create { e ->
            if (!e.isDisposed) {
                e.onNext("A")
                Logs.eprintln(TAG, "String emit : A \n")
                e.onNext("B")
                Logs.eprintln(TAG, "String emit : B \n")
                e.onNext("C")
                Logs.eprintln(TAG, "String emit : C \n")
            }
        }
    }

    private fun getIntegerObservable():Observable<Int>
    {
        return Observable.create{ e ->
            if (!e.isDisposed) {
                e.onNext(1)
                Logs.eprintln(TAG, "Integer emit : 1 \n")
                e.onNext(2)
                Logs.eprintln(TAG, "Integer emit : 2 \n")
                e.onNext(3)
                Logs.eprintln(TAG, "Integer emit : 3 \n")
                e.onNext(4)
                Logs.eprintln(TAG, "Integer emit : 4 \n")
                e.onNext(5)
                Logs.eprintln(TAG, "Integer emit : 5 \n")
            }
        }
    }


    private fun getStringObservable2():Observable<String>
    {
        return Observable.create{ e ->
            if (!e.isDisposed) {
                e.onNext("天")
                Logs.eprintln(TAG, "Integer emit : 天 \n")
                e.onNext("气")
                Logs.eprintln(TAG, "Integer emit : 气 \n")
                e.onNext("好")
                Logs.eprintln(TAG, "Integer emit : 好 \n")
                e.onNext("热")
                Logs.eprintln(TAG, "Integer emit : 热 \n")
                e.onNext("呀")
                Logs.eprintln(TAG, "Integer emit : 呀 \n")
            }
        }
    }
}


