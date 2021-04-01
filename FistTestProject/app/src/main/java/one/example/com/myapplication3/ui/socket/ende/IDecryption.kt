package one.example.com.myapplication3.ui.socket.ende

interface IDecryption : IBaseEnDe {
    fun decode(byteArray: ByteArray): String                //解密
    fun decodeToByteArray(byteArray: ByteArray): ByteArray  //解密

    fun getCmdType(): Int
    fun getType(): Int
    fun getTimeStamp(): Int
    fun getNnno(): Int
    fun getLen(): Int
    fun getCrc(): String
    fun getDataArea(): String
}