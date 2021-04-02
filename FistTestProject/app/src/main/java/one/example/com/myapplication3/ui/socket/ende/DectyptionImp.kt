package one.example.com.myapplication3.ui.socket.ende

import one.example.com.myapplication3.Logs
import one.example.com.myapplication3.ui.socket.ende.inner.AbstractDectyption
import one.example.com.myapplication3.ui.socket.ende.inner.ParamCache


class DectyptionImp : AbstractDectyption() {
    override fun saveEquipNo(equipNo: String) {
        ParamCache.getInstance().equipNo = equipNo
    }

    override fun getEquipNo(): String {
        return ParamCache.getInstance().equipNo
    }

    override fun detyptionSuc(jsonString: String) {

    }

    override fun detyptionFail(error: String) {
        Logs.eprintln(error)
    }

}