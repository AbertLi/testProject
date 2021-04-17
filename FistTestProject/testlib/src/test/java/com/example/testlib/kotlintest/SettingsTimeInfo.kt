package com.example.testlib.kotlintest

class SettingsTimeInfo {
    var startHours = 0         //开始小时
    var startMinutes = 0       //开始分钟
    var endHours = 0           //结束小时
    var endMinutes = 0         //结束分钟
    override fun toString(): String {
        return "SettingsTimeInfo(startHours=$startHours, startMinutes=$startMinutes, endHours=$endHours, endMinutes=$endMinutes)"
    }

//    var data = "2020-2-2"      //日期
//    var controlSwitch = true   //控制开关
//    var isShowAble = false     //这个时间信息是否可用，是否，可以显示。


}