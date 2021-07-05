package data

import com.example.productschedule.bean.IpBean
import com.example.productschedule.utils.SharePreUtils
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object IpDatas {
    fun getIpDatas(): MutableList<IpBean> {
        if (isFirstStart()) {
            val list:MutableList<IpBean> = mutableListOf()
            list.add(IpBean("192.168.11.243", "PDAServer"))
            list.add(IpBean("192.168.11.249", "PDAServer"))
            SharePreUtils.setinfo.edit().putString(BaseConstant.IP_DATAS, Gson().toJson(list)).apply()
            return list
        } else {
            return Gson().fromJson<MutableList<IpBean>>(SharePreUtils.setinfo.getString(BaseConstant.IP_DATAS, ""), object :TypeToken<List<IpBean>>(){}.type)
        }
    }
    // 判断是否第一次获取IP数据
    private fun isFirstStart(): Boolean {
        val bol = SharePreUtils.setinfo.getBoolean(BaseConstant.FIRST_GET_IP_DATAS, true)
        if (bol) {
            SharePreUtils.setinfo.edit().putBoolean(BaseConstant.FIRST_GET_IP_DATAS, false).apply()
            return true
        } else {
            return false
        }
    }

}