package one.example.com.myapplication3.ui.socket.ende

interface IEncryptionDecryption {
    fun encode(): ByteArray

    fun decodeToJsonString(byteArray: ByteArray): String
}