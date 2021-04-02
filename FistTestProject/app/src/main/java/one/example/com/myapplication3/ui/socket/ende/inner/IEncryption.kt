package one.example.com.myapplication3.ui.socket.ende.inner

import one.example.com.myapplication3.ui.socket.ende.inner.IBaseEnDe

interface IEncryption : IBaseEnDe {
    fun encodeToByteArray(): ByteArray                      //加密
}