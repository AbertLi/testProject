package com.book.logviewtool

import android.os.Environment
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.IOException
import java.text.DecimalFormat
import java.util.*


/**
 * 用于获取手机的文件夹及文件的工具类，如果权限允许，可以获取手机上任意路径的文件列表
 * GetFilesUtils使用的是懒汉式单例模式，线程安全
 * @author wuwang
 * @since 2014.11
 */
class GetFilesUtils  {
    /**
     * 获取文件path文件夹下的文件列表
     * @see .getSonNode
     * @param path 手机上的文件夹
     * @return path文件夹下的文件列表的信息，信息存储在Map中，Map的key的列表如下：<br></br>
     * FILE_INFO_NAME : String 文件名称 <br></br>
     * FILE_INFO_ISFOLDER: boolean 是否为文件夹  <br></br>
     * FILE_INFO_TYPE: string 文件的后缀 <br></br>
     * FILE_INFO_NUM_SONDIRS : int 子文件夹个数  <br></br>
     * FILE_INFO_NUM_SONFILES: int 子文件个数  <br></br>
     * FILE_INFO_PATH : String 文件的绝对路径 <br></br>
     */
    fun getSonNode(path: File): List<Map<String, Any>>? {
        return if (path.isDirectory) {
            val list: MutableList<Map<String, Any>> = ArrayList()
            val files = path.listFiles()
            if (files != null) {
                for (i in files.indices) {
                    val fileInfo: MutableMap<String, Any> = HashMap()
                    fileInfo[FILE_INFO_NAME] = files[i].name
                    if (files[i].isDirectory) {
                        fileInfo[FILE_INFO_ISFOLDER] = true
                        val bFiles = files[i].listFiles()
                        if (bFiles == null) {
                            fileInfo[FILE_INFO_NUM_SONDIRS] = 0
                            fileInfo[FILE_INFO_NUM_SONFILES] = 0
                        } else {
                            var getNumOfDir = 0
                            for (j in bFiles.indices) {
                                if (bFiles[j].isDirectory) {
                                    getNumOfDir++
                                }
                            }
                            fileInfo[FILE_INFO_NUM_SONDIRS] = getNumOfDir
                            fileInfo[FILE_INFO_NUM_SONFILES] = bFiles.size - getNumOfDir
                        }
                        fileInfo[FILE_INFO_TYPE] = FILE_TYPE_FOLDER
                    } else {
                        fileInfo[FILE_INFO_ISFOLDER] = false
                        fileInfo[FILE_INFO_NUM_SONDIRS] = 0
                        fileInfo[FILE_INFO_NUM_SONFILES] = 0
                        fileInfo[FILE_INFO_TYPE] = getFileType(files[i].name)
                    }
                    fileInfo[FILE_INFO_PATH] = files[i].absoluteFile
                    list.add(fileInfo)
                }
                list
            } else {
                null
            }
        } else {
            null
        }
    }

    /**
     * 获取文件pathStr文件夹下的文件列表
     * @see .getSonNode
     * @param pathStr 手机上的文件夹的绝对路径
     * @return pathStr文件夹下的文件列表的信息，信息存储在Map中，Map的key的列表如下：<br></br>
     * FILE_INFO_NAME : String 文件名称 <br></br>
     * FILE_INFO_ISFOLDER: boolean 是否为文件夹  <br></br>
     * FILE_INFO_TYPE: string 文件的后缀 <br></br>
     * FILE_INFO_NUM_SONDIRS : int 子文件夹个数  <br></br>
     * FILE_INFO_NUM_SONFILES: int 子文件个数  <br></br>
     * FILE_INFO_PATH : String 文件的绝对路径 <br></br>
     */
    fun getSonNode(pathStr: String?): List<Map<String, Any>>? {
        val path = File(pathStr)
        return getSonNode(path)
    }

    /**
     * 获取文件path文件或文件夹的兄弟节点文件列表
     * @see .getBrotherNode
     * @param path 手机上的文件夹
     * @return path文件夹下的文件列表的信息，信息存储在Map中，Map的key的列表如下：<br></br>
     * FILE_INFO_NAME : String 文件名称 <br></br>
     * FILE_INFO_ISFOLDER: boolean 是否为文件夹  <br></br>
     * FILE_INFO_TYPE: string 文件的后缀 <br></br>
     * FILE_INFO_NUM_SONDIRS : int 子文件夹个数  <br></br>
     * FILE_INFO_NUM_SONFILES: int 子文件个数  <br></br>
     * FILE_INFO_PATH : String 文件的绝对路径 <br></br>
     */
    fun getBrotherNode(path: File): List<Map<String, Any>>? {
        return if (path.parentFile != null) {
            getSonNode(path.parentFile)
        } else {
            null
        }
    }

    /**
     * 获取文件path文件或文件夹的兄弟节点文件列表
     * @see .getBrotherNode
     * @param path 手机上的文件夹
     * @return path文件夹下的文件列表的信息，信息存储在Map中，Map的key的列表如下：<br></br>
     * FILE_INFO_NAME : String 文件名称 <br></br>
     * FILE_INFO_ISFOLDER: boolean 是否为文件夹  <br></br>
     * FILE_INFO_TYPE: string 文件的后缀 <br></br>
     * FILE_INFO_NUM_SONDIRS : int 子文件夹个数  <br></br>
     * FILE_INFO_NUM_SONFILES: int 子文件个数  <br></br>
     * FILE_INFO_PATH : String 文件的绝对路径 <br></br>
     */
    fun getBrotherNode(pathStr: String?): List<Map<String, Any>>? {
        val path = File(pathStr)
        return getBrotherNode(path)
    }

    /**
     * 获取文件或文件夹的父路径
     * @param File path文件或者文件夹
     * @return String path的父路径
     */
    fun getParentPath(path: File): String? {
        return if (path.parentFile == null) {
            null
        } else {
            path.parent
        }
    }

    /**
     * 获取文件或文件的父路径
     * @param String pathStr文件或者文件夹路径
     * @return String pathStr的父路径
     */
    fun getParentPath(pathStr: String?): String? {
        val path = File(pathStr)
        return if (path.parentFile == null) {
            null
        } else {
            path.parent
        }
    }

    /**
     * 获取sd卡的绝对路径
     * @return String 如果sd卡存在，返回sd卡的绝对路径，否则返回null
     */
    val sDPath: String?
        get() {
            val sdcard = Environment.getExternalStorageState()
            return if (sdcard == Environment.MEDIA_MOUNTED) {
                Environment.getExternalStorageDirectory().absolutePath
            } else {
                null
            }
        }

    /**
     * 获取一个基本的路径，一般应用创建存放应用数据可以用到
     * @return String 如果SD卡存在，返回SD卡的绝对路径，如果SD卡不存在，返回Android数据目录的绝对路径
     */
    val basePath: String
        get() {
            val basePath = sDPath
            return basePath ?: Environment.getDataDirectory().absolutePath
        }

    /**
     * 获取文件path的大小
     * @return String path的大小
     */
    @Throws(IOException::class)
    fun getFileSize(path: File): String? {
        return if (path.exists()) {
            val df = DecimalFormat("#.00")
            var sizeStr = ""
            val fis = FileInputStream(path)
            val size = fis.available().toLong()
            fis.close()
            sizeStr = if (size < 1024) {
                size.toString() + "B"
            } else if (size < 1048576) {
                df.format(size / 1024.toDouble()) + "KB"
            } else if (size < 1073741824) {
                df.format(size / 1048576.toDouble()) + "MB"
            } else {
                df.format(size / 1073741824.toDouble()) + "GB"
            }
            sizeStr
        } else {
            null
        }
    }

    /**
     * 获取文件fpath的大小
     * @return String path的大小
     */
    fun getFileSize(fpath: String?): String {
        val path = File(fpath)
        return if (path.exists()) {
            val df = DecimalFormat("#.00")
            var sizeStr = ""
            var size: Long = 0
            try {
                val fis = FileInputStream(path)
                size = fis.available().toLong()
                fis.close()
            } catch (e: FileNotFoundException) {
                // TODO Auto-generated catch block
                e.printStackTrace()
                return "未知大小"
            } catch (e: IOException) {
                // TODO Auto-generated catch block
                e.printStackTrace()
                return "未知大小"
            }
            sizeStr = if (size < 1024) {
                size.toString() + "B"
            } else if (size < 1048576) {
                df.format(size / 1024.toDouble()) + "KB"
            } else if (size < 1073741824) {
                df.format(size / 1048576.toDouble()) + "MB"
            } else {
                df.format(size / 1073741824.toDouble()) + "GB"
            }
            sizeStr
        } else {
            "未知大小"
        }
    }

    /**
     * 根据后缀获取文件fileName的类型
     * @return String 文件的类型
     */
    fun getFileType(fileName: String): String {
        if (fileName !== "" && fileName.length > 3) {
            val dot = fileName.lastIndexOf(".")
            return if (dot > 0) {
                fileName.substring(dot + 1)
            } else {
                ""
            }
        }
        return ""
    }

//    fun defaultOrder(): Comparator<Map<String, Any>> {
//        val orderBy0 = FILE_INFO_ISFOLDER
//        val orderBy1 = FILE_INFO_TYPE
//        val orderBy2 = FILE_INFO_NAME
//        return Comparator<Map<String?, Any?>> { lhs, rhs -> // TODO Auto-generated method stub
//            val left0 = if (lhs[orderBy0] == true) 0 else 1
//            val right0 = if (rhs[orderBy0] == true) 0 else 1
//            if (left0 == right0) {
//                val left1 = lhs[orderBy1].toString()
//                val right1 = rhs[orderBy1].toString()
//                if (left1.compareTo(right1) == 0) {
//                    val left2 = lhs[orderBy2].toString()
//                    val right2 = rhs[orderBy2].toString()
//                    left2.compareTo(right2)
//                } else {
//                    left1.compareTo(right1)
//                }
//            } else {
//                left0 - right0
//            }
//        }
//    }

//    fun defaultOrder(): Comparator<Map<String?, Any?>?>? {
//        val orderBy0 = FILE_INFO_ISFOLDER
//        val orderBy1 = FILE_INFO_TYPE
//        val orderBy2 = FILE_INFO_NAME
//        return object : Comparator<Map<String?, Any?>?>() {
//            override fun compare(lhs: Map<String?, Any?>, rhs: Map<String?, Any?>): Int {
//                val left0 = if (lhs[orderBy0] == true) 0 else 1
//                val right0 = if (rhs[orderBy0] == true) 0 else 1
//                return if (left0 == right0) {
//                    val left1 = lhs[orderBy1].toString()
//                    val right1 = rhs[orderBy1].toString()
//                    if (left1.compareTo(right1) == 0) {
//                        val left2 = lhs[orderBy2].toString()
//                        val right2 = rhs[orderBy2].toString()
//                        left2.compareTo(right2)
//                    } else {
//                        left1.compareTo(right1)
//                    }
//                } else {
//                    left0 - right0
//                }
//            }
//        }
//    }


    companion object {
        const val FILE_TYPE_FOLDER = "wFl2d"
        const val FILE_INFO_NAME = "fName"
        const val FILE_INFO_ISFOLDER = "fIsDir"
        const val FILE_INFO_TYPE = "fFileType"
        const val FILE_INFO_NUM_SONDIRS = "fSonDirs"
        const val FILE_INFO_NUM_SONFILES = "fSonFiles"
        const val FILE_INFO_PATH = "fPath"
        private var gfu: GetFilesUtils? = null

        /**
         * 获取GetFilesUtils实例
         * @return GetFilesUtils
         */
        @get:Synchronized
        val instance: GetFilesUtils?
            get() {
                if (gfu == null) {
                    gfu = GetFilesUtils()
                }
                return gfu
            }
    }
}