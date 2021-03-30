package one.example.com.myapplication3.ui.socket.ende

interface IEncryptionDecryption {
    //加密
    fun encode(str: String): String

    //解密
    fun decode(str: String): String
}