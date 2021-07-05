package data

import com.example.productschedule.bean.IpBean
import com.example.productschedule.utils.SharePreUtils
import com.google.gson.Gson
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object HttpClient {
    private val mClient: OkHttpClient by lazy { newClient() }
    private val ip: IpBean by lazy { getIpBean() }
    private val httpService: HttpService = Retrofit.Builder()
        .baseUrl("http://${ip.address}/${ip.path}/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(mClient)
        .build()
        .create(HttpService::class.java)

    fun getHttpService(): HttpService {
        return httpService
    }

    private fun getIpBean(): IpBean {
        val strIp = SharePreUtils.setinfo.getString(BaseConstant.CUR_IP, "")
        return Gson().fromJson(strIp, IpBean::class.java)
    }

    private fun newClient(): OkHttpClient = OkHttpClient.Builder().apply {
        connectTimeout(30, TimeUnit.SECONDS)// 连接时间：30s超时
        readTimeout(18, TimeUnit.SECONDS)// 读取时间：10s超时
        writeTimeout(18, TimeUnit.SECONDS)// 写入时间：10s超时
    }.build()
}