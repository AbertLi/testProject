package one.example.com.myapplication3.ui.socket.ende

interface IEncryptionDecryption {
    fun encode(): String                                    //加密
    fun encodeToByteArray(): ByteArray                      //加密
    fun decode(byteArray: ByteArray): String                //解密
    fun decodeToByteArray(byteArray: ByteArray): ByteArray  //解密
}