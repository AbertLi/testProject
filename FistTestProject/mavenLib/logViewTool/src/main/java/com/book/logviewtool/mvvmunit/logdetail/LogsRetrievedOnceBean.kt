package com.book.logviewtool.mvvmunit.logdetail

/**
 * 一次检索的日志
 */
data class LogsRetrievedOnceBean(var toMeetTheConditionsNum: String,
                                 var numberOfRowsRead: String,
                                 var totalNumberOfRows :String,
                                 var list: ArrayList<String>)