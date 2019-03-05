package one.example.com.myapplication3.ui.kotlinAct

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import one.example.com.myapplication3.databinding.ActivityMain2Binding
import one.example.com.myapplication3.ui.bindings.list.IPersonCallBack
import one.example.com.myapplication3.ui.bindings.list.PersonListAdapter
import one.example.com.myapplication3.R
import android.view.WindowManager
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import one.example.com.myapplication3.Logs
import one.example.com.myapplication3.db.entity.PersonEntity
import one.example.com.myapplication3.ui.bindings.list.DetailActivity
import one.example.com.myapplication3.viewmodle.PersonListViewModle
import androidx.lifecycle.Observer


class Main2Activity : FragmentActivity() {
    var TAG = "Main2Activity"
    var binding: ActivityMain2Binding? = null
    var select: PersonEntity? = null
    var adapter: PersonListAdapter? = null

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//通知栏设置颜色
//            setTranslucentStatus(true)
//            val tintManager = SystemBarTintManager(this)
//            tintManager.setStatusBarTintEnabled(true)
//            tintManager.setStatusBarTintResource(android.R.color.white)//通知栏所需颜色
//        }
        binding = DataBindingUtil.setContentView(this@Main2Activity, R.layout.activity_main2)
        adapter = PersonListAdapter(clickCallBack)

        var result = "ladbekdkg".let {
            it.length
        }
        Logs.eprintln(TAG, result.toString())

        var personEntity = PersonEntity("Jack", "33")
        var result2 = with(personEntity) {
            var stringBuffer = StringBuffer()
            stringBuffer.append(name)
            stringBuffer.append(age)
            stringBuffer
        }
        Logs.eprintln(TAG, result2.toString())


        binding?.recyclerview?.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding?.recyclerview?.adapter = adapter

        if (select == null) {
            select = PersonEntity("暂时么有选择", "")
        }
        binding?.personBean = select
    }

    internal var clickCallBack: IPersonCallBack = IPersonCallBack { person ->
        select = person as PersonEntity
        binding?.personBean = select
        val intent = Intent(this@Main2Activity, DetailActivity::class.java)
        intent.putExtra("person", select)
        this@Main2Activity.startActivity(intent)
        Logs.eprintln(TAG, "点击：" + person.getName())
    }

    override fun onResume() {
        super.onResume()
        addDataToViewModle(adapter)
    }

    /**
     * 通过ViewModle从数据库里面获取数据。
     * 数据是数据在创建的时候创建的。
     */
    fun addDataToViewModle(adapter: PersonListAdapter?) {
        val viewModel = ViewModelProviders.of(this).get(PersonListViewModle::class.java)

        val nameObserver = Observer<List<PersonEntity>> { listEntity ->
            Logs.eprintln(TAG, "subscribeUi   listEntity==null" + (listEntity == null))
            if (listEntity != null) {
                adapter?.addPersonList(listEntity)
                binding?.executePendingBindings()//计算挂起的绑定，更新将表达式绑定到已修改变量的任何视图。
                Logs.eprintln(TAG, "subscribeUi  onChanged=" + listEntity.size)
            } else {

            }
        }
        viewModel.person.observe(this, nameObserver)
    }

    @TargetApi(19)
    private fun setTranslucentStatus(on: Boolean) {
        val win = window
        val winParams = win.attributes
        val bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
        if (on) {
            winParams.flags = winParams.flags or bits
        } else {
            winParams.flags = winParams.flags and bits.inv()
        }
        win.attributes = winParams
    }
}
