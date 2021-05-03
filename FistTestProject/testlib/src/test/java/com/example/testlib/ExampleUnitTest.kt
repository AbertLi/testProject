package com.example.testlib

import com.example.testlib.kotlintest.TransformationUtil
import org.junit.Test

import org.junit.Assert.*
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }


    @Test
    fun testJava() {
        var intArrayList = ArrayList<Int>()
        for (i in 0..47) {
            intArrayList.add((0..1).random())
        }
        var arrInfo = TransformationUtil.intArrayToBeanArray(intArrayList)

        for (i in intArrayList){
            print(i)
            print("   ")
        }
        println("   ")
        for (i in arrInfo){
            println(i.toString())
        }


//        for (i in 1..100) {
//            val randoms = Random().nextInt(11)
//            print(randoms)
//            print(" ")
//        }
//        println(" ")
//        for (i in 1..100) {
//            val randoms = (0..10).random()
//            print(randoms)
//            print(" ")
//        }
        println("======intArrayResult======")
        var intArrayResult = TransformationUtil.beanArrayToIntArray(48,arrInfo)
        for (i in intArrayResult) {
            print(i)
            print("   ")
        }
        print("kadfjaljla")
    }
}
