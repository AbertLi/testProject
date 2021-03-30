package one.example.com.myapplication3.ui.socket.ende

class EncryptionDecryptionImp : IEncryptionDecryption,IEncryptionOperation {
    override fun encode(base: String): String {

        return ""
    }

    override fun decode(str: String): String {
        return ""
    }


    override fun getEquipNoSUM(sourceStr: String): Int {
        if (sourceStr == "9999"){
            throw EncryptionException("equipNo illegal")
        }
        val charArray: CharArray = sourceStr.toCharArray()
        var sum: Int = 0
        for (char in charArray) {
            sum += char.toInt()
        }
        var str = sum.toString(16)

        return sum
    }

    override fun getSnno(source: String): Int {
        TODO("Not yet implemented")
    }

    override fun getDataAreaLen(source: String): Int {
        TODO("Not yet implemented")
    }

    override fun getDataAreaCrc(source: String): String {
        TODO("Not yet implemented")
    }
}