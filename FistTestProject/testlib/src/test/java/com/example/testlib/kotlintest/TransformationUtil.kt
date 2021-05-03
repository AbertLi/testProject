package com.example.testlib.kotlintest

/**
 * 时间和数组之间的转换工具类
 */
object TransformationUtil {
    fun intArrayToBeanArray(arrayListInt: ArrayList<Int>): ArrayList<SettingsTimeInfo> {
        var arrayListInfo = ArrayList<SettingsTimeInfo>()
        var info = SettingsTimeInfo()
        var isSaveTimeEnd = false
        var isSaveTemeStart = false
        for (i in 0 until arrayListInt.size) {
            if (arrayListInt[i] == 1) {
                if (!isSaveTemeStart) {
                    isSaveTemeStart = true
                    if (isSaveTimeEnd.or(i == 0)) {
                        isSaveTimeEnd = false
                        info = SettingsTimeInfo()
                        info.startHours = i / 2
                        info.startMinutes = i % 2 * 30
                    }
                }
                if (i == arrayListInt.size - 1) {
                    info.endHours = 0
                    info.endMinutes = 0
                    arrayListInfo.add(info)
                }
            } else {
                if (!isSaveTimeEnd) {
                    isSaveTimeEnd = true
                    if (isSaveTemeStart) {
                        isSaveTemeStart = false
                        info?.endHours = i / 2
                        info?.endMinutes = i % 2 * 30
                        arrayListInfo.add(info)
                    }
                }
            }
        }
        return arrayListInfo
    }

    fun beanArrayToIntArray(arraySize:Int,beanArray: ArrayList<SettingsTimeInfo>): ArrayList<Int> {
        var intArrayMaxIndex = arraySize -1
        var intArray = ArrayList<Int>()
        for (i in 0..intArrayMaxIndex) {
            intArray.add(0)
        }
        for (bean in beanArray) {
            var startIndex = (bean.startHours * 2) + (bean.startMinutes / 30)
            var endIndex = if (bean.endHours + bean.endMinutes != 0) bean.endHours * 2 + (bean.endMinutes / 30) - 1 else intArrayMaxIndex
            for (j in startIndex..endIndex) {
                intArray[j] = 1
            }
        }
        return intArray
    }
}