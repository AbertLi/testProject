package com.book.logviewtool.mvvmunit.logdetail

data class LogReadBean(val isNewRequest: Boolean,   //是否发送新的请求从新读取日志文件。为false表示继续读取原来的日志文件。
                       var startLine: Int,          //开始读取的日志行数
                       var filePath: String,        //文件的路径。
                       var keyWords: String,        //搜索关键词
                       var onceNumOfLine: Int = 100)//一次读取的行数