//package one.example.com.myapplication3.KTFiles
//
//import android.util.Log
//
//class KT {
//
//    companion object{
//        var ktTest:KT?=null
//        fun getInstance():KT{
//            if (ktTest==null){
//                ktTest=KT()
//            }
//            return  ktTest!!
//        }
//    }
//
//    // 扩展函数 swap,调换不同位置的值
//    fun MutableList<Int>.swap(index1: Int, index2: Int) {
//        val tmp = this[index1]     //  this 对应该列表
//        this[index1] = this[index2]
//        this[index2] = tmp
//    }
//
////    fun main(args: Array<String>) {
////
////        val l = mutableListOf(1, 2, 3)
////        // 位置 0 和 2 的值做了互换
////        l.swap(0, 2) // 'swap()' 函数内的 'this' 将指向 'l' 的值
////
////        println(l.toString())
////    }
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//    fun test(){
//        val info= ABTInfo()
//        info.save()
//
//        if (!"".equals(info.key)&&info.key!!.contains("da")){
//            Log.e("TAG","包含")
//        }else{
//            Log.e("TAG","不包含")
//        }
//    }
//
//
//
//    /**
//     * 2，定义函数
//     */
//
////求和函数表达形式一
//    fun print1(a: Int, b: Int): Int {
//        return a + b
//    }
//
//    //求和函数表达形式二
//    fun print2(a: Int, b: Int) = a + b
//
//
//    //返回值为Unit的函数表达形式一
//    fun printSum1(a: Int, b: Int): Unit {
//        println("sum of $a and $b is ${a + b}")
//    }
//
//
//    //返回值为Unit的函数表达形式二
//    fun printSum2(a: Int, b: Int) {
//        println("sum of $a and $b is ${a + b}")
//    }
//
//    /**
//     * 3，局部函数
//     */
////一次赋值（只读）的局部变量:
//    fun localVariable1() {
////立即赋值
//        val a: Int = 2
////自动判断类型
//        val b = 3
////先声明后赋值
//        val c: Int
//        c = 5
//    }
//
//    //可变变量：
//    fun localVariable2(): Int {
//        var x = 5 // 自动推断出 `Int` 类型
//        x += 1
//        return x
//    }
//
//    /**
//     * 4,注释
//     */
////第一种注释
//    /**
//     *第二种注释
//     */
//
///*
// *第三种注释
// */
//
//
//    /**
//     * 5，使用字符串模板
//     */
//    fun String() {
//        var e = 1
//// 模板中的简单名称：
//        val s1 = "a is $e"
//
//        e = 2
//// 模板中的任意表达式：
//        val s2 = "${s1.replace("is", "was")}, but now is $e"
//    }
//
//
//    /**
//     * 6,使用条件表达式
//     */
////表达方式一
//    fun maxOf(a: Int, b: Int): Int {
//        if (a > b) {
//            return a
//        } else {
//            return b
//        }
//    }
//
//    //表达方式二
//    fun maxOf2(a: Int, b: Int) = if (a > b) a else b
//
//    /**
//     * 7,使用可空值及 null 检测
//     *
//     * 当某个变量的值可以为 null 的时候，必须在声明处的类型后添加 ?
//     * 来标识该引用可为空。如果 str 的内容不是数字返回 null：
//     */
////情况1
//    fun parseInt(str: String): Int? {
//        // ……
//        if (str == null || "".equals(str)) {
//            return null
//        } else {
//            return str.toInt()
//        }
//    }
//
//    //情况二，使用返回值可能为空的函数
//    fun printProduct(arg1: String, arg2: String) {
//        val x = parseInt(arg1)
//        val y = parseInt(arg2)
//
//        // 直接使用 `x * y` 可能会报错，因为他们可能为 null
//        if (x != null && y != null) {
//            // 在空检测后，x 和 y 会自动转换为非空值（non-nullable）
//            println(x * y)
//        } else {
//            println("either '$arg1' or '$arg2' is not a number")
//        }
//    }
//
//    //情况三，使用返回值可能为空的函数
//    fun printProduct3(arg1: String, arg2: String) {
//        val x = parseInt(arg1)
//        val y = parseInt(arg2)
//        if (x == null) {
//            println("Wrong number format in arg1: '${arg1}'")
//            return
//        }
//        if (y == null) {
//            println("Wrong number format in arg2: '${arg2}'")
//            return
//        }
//// 在空检测后，x 和 y 会自动转换为非空值
//        println(x * y)
//    }
//
//
//    /**
//     * 8，使用类型检测及自动类型转换
//     * is 运算符检测一个表达式是否某类型的一个实例。 如果一个不可变的局部变量或属性已经判断出为某类型，那么检测后的分支中可以直接当作该类型使用，无需显式转换：
//     */
////情况一
//    fun getStringLength1(obj: Any): Int? {
//        if (obj is String) {
//            // `obj` 在该条件分支内自动转换成 `String`
//            return obj.length
//        }
//        // 在离开类型检测分支后，`obj` 仍然是 `Any` 类型
//        return null
//    }
////情况二
//
//    fun getStringLength2(obj: Any): Int? {
//        if (obj !is String)
//            return 0
//        // `obj` 在这一分支自动转换为 `String`
//        return obj.length
//    }
//
//    //情况三
//    fun getStringLength3(obj: Any): Int? {
//        // `obj` 在 `&&` 右边自动转换成 `String` 类型
//        if (obj is String && obj.length > 0) {
//            return obj.length
//        }
//        return 0
//    }
//
//    /**
//     * 9，使用 for 循环
//     */
//
////情况一
//    fun for1() {
//        val items = listOf("apple", "banana", "kiwi")
//        for (item in items) {
//            println(item)
//        }
//    }
//
//    //情况二
//    fun for2() {
//        val items = listOf("apple", "banana", "kiwi")
//        for (index in items.indices) {
//            println("item at $index is ${items[index]}")
//        }
//    }
//
//    /**
//     * 10,使用while循环
//     */
////情况一
//    fun while1() {
//        var index = 0
//        while (index < items.size) {
//            println("item at $index is ${items[index]}")
//            index++
//        }
//
//    }
//
//
//
//
//    /**
//     * 11,使用when表达式
//     */
//
//    fun describe(obj: Any): String =
//            when (obj) {
//                1 -> "One"
//                "Hello" -> "Greeting"
//                is Long -> "Long"
//                !is String -> "Not a string"
//                else -> "Unknown"
//            }
//
//
//    /**
//     * 12,使用区间 range
//     */
////情况一，使用 in 运算符来检测某个数字是否在指定区间内：
//
//    fun range1() {
//        val x = 10
//        val y = 9
//        if (x in 1..y + 1) {
//            println("fits in range")
//        }
//    }
//
//    //情况二，检测某个数字是否在指定区间外:
//    fun range2() {
//        val list = listOf("a", "b", "c")
//
//        if (-1 !in 0..list.lastIndex) {
//            println("-1 is out of range")
//        }
//        if (list.size !in list.indices) {
//            println("list size is out of valid list indices range too")
//        }
//    }
//
//    //情况三，区间迭代
//    fun range3() {
//        for (x in 1..5) {
//            print(x)
//        }
//    }
//
//    //情况四，数字迭代
//    fun range4() {
//        for (x in 1..10 step 2) {
//            print(x)
//        }
//        for (x in 9 downTo 0 step 3) {
//            print(x)
//        }
//    }
//
//
//    /**
//     * 13，使用集合
//     */
////情况一，对集合进行迭代
//    fun jihe1() {
//        for (item in items) {
//            println(item)
//        }
//    }
////情况二，使用 in 运算符来判断集合内是否包含某实例：
//
//    fun jihe2() {
//        when {
//            "orange" in items -> println("juicy")
//            "apple" in items -> println("apple is fine too")
//        }
//
//    }
//    //情况三，使用 lambda 表达式来过滤（filter）和映射（map）集合：
//    fun jihe3() {
//        items.filter { it.startsWith("a") }
//                .sortedBy { it }
//
//                .map { it.toUpperCase() }
//
//                .forEach { println(it) }
//    }
//
//    val items = listOf("apple", "banana", "kiwi","ljh","albert")//集合
//}