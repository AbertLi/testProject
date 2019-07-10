package one.example.com.myapplication3.ui.loadBundle.listviewBundlestyle2

import java.util.*

class Bean {

    var id2: String? = null

    //长整型 64位 注意后面加大写L
    var height: Long? = 0L

    //整型 32 位
    var id: Int? = 0

    //短整型 16位
    var short: Short? = 0

    // 8位
    var name: Byte? = null

    //浮点类型 双精度  64位
    var level: Double = 0.0

    //单精度  后面加f
    var levelf: Float = 0.0f

    //时间类型成员属性   ？表示该属性可以为null
    var createTime: Date? = null

    //初始化，kotlin没有new关键字，直接 对象()就是创建一个新对象
    var time: Date? = Date()

    //字符串类型
    var introduction: String? = null

    //布尔类型作为属性
    var isHide: Boolean? = null


    // Char是一个单独类型 ，不表示数字，需要用‘’括起来，不然那会报错
    var char: Char? = '1'


}