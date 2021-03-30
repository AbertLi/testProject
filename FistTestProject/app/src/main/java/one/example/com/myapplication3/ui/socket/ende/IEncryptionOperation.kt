package one.example.com.myapplication3.ui.socket.ende

interface IEncryptionOperation {
    fun getEquipNoSUM(source: String): Int        //获取equipNo每一字符相加之和
    fun getSnno(source: String): Int              //获取每次调用加一的snno
    fun getDataAreaLen(source: String): Int        //dataArea的字符长度
    fun getDataAreaCrc(source: String): String    //对dataArea进行crc加密
}