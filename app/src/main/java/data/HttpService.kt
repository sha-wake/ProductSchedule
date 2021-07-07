package data


import com.example.productschedule.bean.ProLinePlanBean
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface HttpService {
//    @GET("User/GetErpOrgan")
//    fun getZuZhiInfo(@Query("lang") lang: String): Call<ZuzhiReceiveBean>
//
//    @GET("User/GetErpFunc")
//    fun getZtInfo(@Query("lang") lang: String, @Query("funcId") funcId: String): Call<ZtReceiveBean>
//
//    @POST("User/UserLogin")
//    fun login(@Body loginPostBean: LoginPostBean): Call<LoginReceiveBean>
//
//    @POST("Scan/PDAScanLoad")
//    fun jsLoadBarcodes(@Body barcodeJsPostBean: BarcodeJsPostBean): Call<BarcodeJsReceiveBean>
//
//    @POST("Scan/PDAScan")
//    fun jsScanBarcodes(@Body barcodeJsPostBean: BarcodeJsPostBean): Call<BarcodeJsScanReceiveBean>
//
//    @POST("Scan/PDADeleteCode")
//    fun jsDeleteBarcodes(@Body barcodeJsPostBean: BarcodeJsPostBean): Call<BarcodeJsDeleteReceiveBean>
//
//    @POST("Scan/PDABarcodeDetails")
//    fun jsClickBarcodes(@Body barcodeJsPostBean: BarcodeJsPostBean): Call<BarcodeJsClickReceiveBean>
//
//    @POST("Commit/PDACommit")
//    fun jsSubmitBarcodes(@Body barcodeJsPostBean: BarcodeJsPostBean): Call<BarcodeJsSubmitReceiveBean>
//
//    @POST("Scan/PDADeleteAllCode")
//    fun jsClearBarcodes(@Body barcodeJsPostBean: BarcodeJsPostBean): Call<BarcodeJsClearReceiveBean>
//
//    @GET("Warehouse/GetWhList")
//    fun getWhList(
//        @Query(value = "loginId") loginId: String,
//        @Query(value = "menuId") menuId: String,
//        @Query(value = "funcId") funcId: String,
//        @Query(value = "plantId") plantId: String
//    ): Call<WhListReceiveBean>
//
//    @POST("Scan/PDAScanInfo")
//    fun getScanInfo(@Body barcodeJsPostBean: BarcodeJsPostBean): Call<BarcodeJsScanInfoReceiveBean>
//
//    @GET("home/GetCustList")
//    fun getCustList(@Query("partName") partName: String): Call<CustListReceiveBean>
//
//    @POST("Scan/PDABarcodeDetails")
//    fun getBarcodeDetail(@Body barcodeJsPostBean: BarcodeJsPostBean): Call<BarcodeJsDetailReceiveBean>
//
//    @POST("Scan/PDADeleteCodeInPackages")
//    fun deleteCodeInPackages(@Body barcodeJsPostBean: BarcodeJsPostBean): Call<BarcodeJsDeleteReceiveBean>
//
//    @GET("Scan/PDAShowDispatchs")
//    fun getDispatchInfo(@Query(value = "u8AutoId") u8AutoId: Int): Call<DispatchInfoBean>
//
//    // 发货拣货提交测试
//    @POST("Commit/apPickingOutTest")
//    fun apPickingOutTest(@Body barcodeJsPostBean: BarcodeJsPostBean): Call<BarcodeJsSubmitReceiveBean>
//
//    @GET("Menu/GetMenuList")
//    fun getMenuList(@Query(value = "loginId") loginId: String, @Query(value = "funcId") funcId: String, @Query(value = "lang") lang: String): Call<MenuReceiveBean>

    @GET("ProductLinePlan/GetProductLinePlanList")
    fun getProductLinePlanList(@Query(value = "fstId") fstId: String, @Query(value = "funcId") funcId: String,
                     @Query(value = "loginId") loginId: String, @Query(value = "startDate") startDate: String,
                     @Query(value = "endDate") endDate: String, @Query(value = "productType") productType: String): Call<ProLinePlanBean>


}