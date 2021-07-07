package com.example.productschedule

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.widget.DatePicker
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import com.bin.david.form.data.column.ArrayColumn
import com.bin.david.form.data.column.Column
import com.bin.david.form.data.table.TableData
import com.emreesen.sntoast.SnToast
import com.example.productschedule.bean.LinePlanBackInfo
import com.example.productschedule.bean.ProLinePlanBean
import com.example.productschedule.bean.TeamDate
import com.example.productschedule.bean.TeamTime
import com.example.productschedule.databinding.ActivityMainBinding
import com.example.productschedule.utils.RequestUtil
import data.HttpClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity(){
    private lateinit var binding: ActivityMainBinding
    private lateinit var format: SimpleDateFormat
//    private lateinit var sTime: String
//    private lateinit var eTime: String
    private lateinit var context: Context
    private lateinit var calBegin: Calendar
    private lateinit var calEnd: Calendar
    private lateinit var dateList: MutableList<String>
    private lateinit var teamTime: MutableList<TeamTime>
    private lateinit var teamDate: MutableList<TeamDate>
    private lateinit var tableData: TableData<TeamDate>
    private lateinit var teamDateColumn: Column<String>
    private lateinit var proFirst: Column<String>
    private lateinit var teamTimeColumn: ArrayColumn<String>
    private lateinit var testPlan: List<LinePlanBackInfo>
    private val fstId = "9"
    private val funcId = "1"
    private val loginId = "727"
    private var proType = "15"

    @SuppressLint("SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        context = applicationContext
        onClick()
        tableCreated()                                                                                // 初始化显示当天以及接下来几天的数据

//        try{
//            val sdf: DateFormat = SimpleDateFormat("MM月dd日")
//            val str = "2021-07-06"
//            val df = sdf.parse(str)
//            Log.i("day", df!!.toString())
//
//        } catch (e: Exception){
//            Log.e("error", e.message.toString())
//        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun onClick(){
        binding.startTime.setOnClickListener {
            showDialog()
        }
        binding.endTime.setOnClickListener{
            showDialogTwo()
        }
        binding.proType.setOnClickListener{
//            getSelectDialog()

//            proFirst.columnName = "helle"                                                             // 修改列名

//            teamTime = mutableListOf<TeamTime>()
//            teamTime.add(TeamTime("白", "", ""))
//            teamTime.add(TeamTime("早", "", ""))
//            teamTime.add(TeamTime("中", "", ""))

//            for (i in 0..2) {
//                val time = when(testPlan[i].TeamTime) {
//                    "1" -> "早"
//                    "2" -> "中"
//                    else -> "晚"
//                }
//                teamTime.add(TeamTime(time, testPlan[i].taskName, testPlan[i].GwString))
//            }
//
//            teamDate = mutableListOf<TeamDate>()
//            for (a in 0..1){
//                val sdf = SimpleDateFormat("M月d日")
//                val c = Calendar.getInstance()
//                c.time = Date()
//                c.add(Calendar.DATE, +a)
//                val d = c.time
//                val day = sdf.format(d)
//                teamDate.add(TeamDate(day, teamTime))
//            }
//            try{
//                val sdf: DateFormat = SimpleDateFormat("M月d日")
//                val str = "2021-07-06"
//                val df = sdf.parse(str)
//                Log.i("day", df!!.toString())
//
//            } catch (e: Exception){
//                Log.e("error", e.message.toString())
//            }

//            val day = sdf.format(df!!)
//            Log.i("day", df!!.toString())
//            teamDate.add(TeamDate(testPlan[0].TeamDate, teamTime))
//            binding.proTable.addData(teamDate, true)
//            loadPlans("9", "1", "727", "2021/7/2", "2021/7/3", "15")
            loadPlans(fstId, funcId, loginId, "2021/07/02", "2021/07/02", proType).forEach {
                Log.i("teamTime", it.TeamTime)
            }

//            convertDate("2021/7/2 16:00:00")
        }

//        binding.tableSetting.setOnClickListener{
//            getTableSettingDialog()
//        }
//

//        binding.exportExcel.setOnClickListener{
//
//        }

    }

    /**
     * 采用普通的方式 create table, 注解的方式还没试过
     * teamTime、 teamDate  自定义的 bean
     * tableDta 表格数据
     */
    @SuppressLint("SimpleDateFormat")
    private fun tableCreated() {

        teamTime = mutableListOf<TeamTime>()
//        teamTime.add(TeamTime("白", "", ""))
//        teamTime.add(TeamTime("早", "", ""))
//        teamTime.add(TeamTime("中", "", ""))

        teamDate = mutableListOf<TeamDate>()
        /*for (a in 0..9){
            val sdf = SimpleDateFormat("M月d日")
            val c = Calendar.getInstance()
            c.time = Date()
            c.add(Calendar.DATE, +a)
            val d = c.time
            val day = sdf.format(d)
            teamDate.add(TeamDate(day, teamTime))
        }*/

        teamDateColumn = Column<String>("日期", "teamDate")                          // 普通行用Column
        teamTimeColumn = ArrayColumn<String>("班次", "teamTimes.teamTime")           // 普通行的子行用ArrayColumn
        val proMessageColumn = ArrayColumn<String>("产品信息", "teamTimes.proMessage")
//        val proSpeedColumn = ArrayColumn<Int>("产线速度", "proSpeed")
        val proWeightColumn = ArrayColumn<String>("克重", "teamTimes.proWeight")
        proFirst = Column<String>("P4-2#，1040", proMessageColumn, proWeightColumn)
//        val tableData = TableData("产线表", teamDate, teamDateColumn, teamTimeColumn, proMessageColumn, proSpeedColumn, proWeightColumn)
        tableData = TableData("产线表", teamDate, teamDateColumn, teamTimeColumn, proFirst)

        binding.proTable.setTableData(tableData)
//        teamDateColumn.isFixed = true                                                                           // 固定某列，考虑是否增加取消
//        teamTimeColumn.isFixed = true
        
        tableConfig()
    }

    /**
     * 测试数据，增加某天的排产计划成功
     */
    /*
    testPlan = listOf(LinePlanBackInfo("21012FCW12-F406PS-0.55*996*130=214", ">4.5", "#66FFCC","2","2021/7/6 8:00:00"),
    LinePlanBackInfo("21012FCW12-F406PS-0.55*996*130=214", ">4.5", "#66FFCC","1","2021/7/6 16:00:00"),
    LinePlanBackInfo("21012FCW12-F406PS-0.55*996*130=214", ">4.5", "#66FFCC","3","2021/7/6 16:00:00"))

    lineSort(testPlan)
    testPlan.forEach {
            println(it.TeamTime)
        Log.i("testPlan", it.TeamTime)
    }
    */

    // 表格部分设置，共同部分可调用此项
    // 不同设置自行添加，不放这
    private fun tableConfig() {
        binding.proTable.config.isShowTableTitle = true
        binding.proTable.config.isShowXSequence = false                                                         // 去除标题行上面的序号
        binding.proTable.config.isShowYSequence = true
        binding.proTable.config.minTableWidth = 800                                                             // 设定最小间距、列数少适应屏幕
        binding.proTable.setZoom(true, 2F, 0.65F)                                   // 设定缩放比例大小
        binding.proTable.config.isShowTableTitle = false
//        binding.proTable.tableData.isShowCount = true                                                         // 显示统计行
    }


    // 选择时间和产品类型之后，改变表格结构，重新生成
    @SuppressLint("SimpleDateFormat")
    private fun tableChanged() {
//        format = SimpleDateFormat("MM月dd日")                                                           // 生成固定格式
//        val c = Calendar.getInstance()
//        c.time = Date()
//        c.add(Calendar.DATE, +a)
//        val d = c.time
//        val day = sdf.format(d)
//        val st = sdf.parse(sTime)
//        val et = sdf.parse(eTime)
//        val calBegin = Calendar.getInstance()

        format = SimpleDateFormat("yyyy/MM/dd")
        dateList = mutableListOf<String>()
        dateList.add(format.format(calBegin.time))

        // 循环遍历，把选定的两个时间段的时间放到列表里
        while (calEnd.time.after(calBegin.time)){
            calBegin.add(Calendar.DAY_OF_MONTH, 1)
            dateList.add(format.format(calBegin.time))
        }

        dateList.removeAt(dateList.size - 1)
//        dateList.forEach {
//            Log.i("list", it)
//        }

    }

    /**
     * 根据获取的起止时间来显示多少列
     * 待整合中
     */
    /*
    获取接下来一周的时间，上一周同理
    for (a in 1..7){
    val sdf = SimpleDateFormat("MM月dd日")
    val c = Calendar.getInstance()
    c.time = Date()
    c.add(Calendar.DATE, +a)
    val d = c.time
    val day = sdf.format(d)
    Log.d("DATE", day)
    */

    // 把获得的两个时间作计较，按天显示一行数据
    @SuppressLint("InflateParams", "ResourceType", "SimpleDateFormat")
    private fun showDialog(){
//        val format = SimpleDateFormat("MM月dd日")
        format = SimpleDateFormat("yyyy-MM-dd")
        val calendar = Calendar.getInstance(Locale.CHINA)

        //这里的year,monthOfYear,dayOfMonth的值与DatePickerDialog控件设置的最新值一致
        val datePickerDialog = DatePickerDialog(this@MainActivity,
            { _: DatePicker, year, month, dayOfMonth ->                                                     // 修改日历控件的年，月，日
                calendar[Calendar.YEAR] = year
                calendar[Calendar.MONTH] = month
                calendar[Calendar.DAY_OF_MONTH] = dayOfMonth
//                Log.i("date2", format.format(calendar.time))
//                sTime = format.format(calendar.time)
                calBegin = Calendar.getInstance()
                calBegin.time = calendar.time
            }, calendar[Calendar.YEAR], calendar[Calendar.MONTH], calendar[Calendar.DAY_OF_MONTH]
        )
        datePickerDialog.show()
    }

    @SuppressLint("SimpleDateFormat")
    private fun showDialogTwo(){
//        val format = DateFormat.getDateTimeInstance("MM月dd日")                                           // 获取日期格式器对象
//        val format = SimpleDateFormat("MM月dd日")
        format = SimpleDateFormat("yyyy/MM/dd")
        val calendar = Calendar.getInstance(Locale.CHINA)
        val datePickerDialog = DatePickerDialog(this@MainActivity,
            { _: DatePicker, year, month, dayOfMonth ->
                calendar[Calendar.YEAR] = year
                calendar[Calendar.MONTH] = month
                calendar[Calendar.DAY_OF_MONTH] = dayOfMonth
//                Log.i("date2", format.format(calendar.time))
//                eTime = format.format(calendar.time)
                calEnd = Calendar.getInstance()
                calEnd.time = calendar.time
                tableChanged()
            }, calendar[Calendar.YEAR], calendar[Calendar.MONTH], calendar[Calendar.DAY_OF_MONTH]
        )
        datePickerDialog.show()

    }


    // 选择不同类型产品，表格会生成不同的产线安排计划
    private fun getSelectDialog() {
        var proType: String
        val arrayType = arrayOf("全部", "白膜", "透明EVA","交联型POE","热塑型POE","共挤胶膜","有色胶膜")
        val builder = AlertDialog.Builder(this)
        builder.setIcon(R.drawable.ic_search)
        builder.setTitle("  选择产品类型")
        builder.setSingleChoiceItems(arrayType, 1) { _, which ->
            proType = arrayType[which]
            binding.proType.text = proType                                                                     // 按钮会显示选择的产品类型
//            Log.e("TAG", "select ${array2[which]}")
        }

        val dialogClickListener = DialogInterface.OnClickListener { _, which ->
            when (which) {
                DialogInterface.BUTTON_POSITIVE -> {
//                    Log.e("TAG", "click yes,$proType")
                }
                DialogInterface.BUTTON_NEGATIVE -> {
//                    Log.e("TAG", "click no，$proType")
                }
            }
        }

        builder.setPositiveButton("确定", dialogClickListener)
        builder.setNegativeButton("取消", dialogClickListener)
        builder.create().show()
    }

    /**
     * 飞滚功能，安卓界面的滚动适配
     * 提供表格四个放置方位： 左、右、顶和底
     */
    private fun getTableSettingDialog() {
        val arrayFly = arrayOf("置左", "置右", "置顶","置底")
        val builder = AlertDialog.Builder(this)
        builder.setIcon(R.drawable.ic_table)
        builder.setTitle("  飞滚")
        builder.setSingleChoiceItems(arrayFly, 1) { _, which ->
            when (which){
               0 -> {
                   binding.proTable.matrixHelper.flingLeft(200)
               }
               1 -> {
                   binding.proTable.matrixHelper.flingRight(200)
               }
               2 -> {
                   binding.proTable.matrixHelper.flingTop(200)
               }
               3 -> {
                   binding.proTable.matrixHelper.flingBottom(200)
               }
            }
        }

        val dialogClickListener = DialogInterface.OnClickListener { _, which ->
            when (which) {
                DialogInterface.BUTTON_POSITIVE -> {
                    binding.proTable.invalidate()
                    SnToast.Custom()
                        .context(this@MainActivity)
                        .backgroundColor(R.color.purple_500)
                        .textColor(R.color.white)
                        .icon(R.drawable.ic_info)
                        .message("设置成功")
                        .duration(1000)
                        .iconSize(24)
                        .textSize(12)
                        .build()

                }
                DialogInterface.BUTTON_NEGATIVE -> {
//                    Log.e("TAG", "click no，$proType")
                }
            }
        }

        builder.setPositiveButton("确定", dialogClickListener)
        builder.setNegativeButton("取消", dialogClickListener)
        builder.create().show()
    }

    /**
     * 加载排班计划数据
     * @param fstId
     * @param funcId
     * @param loginId
     * @param startDate
     * @param endDate
     * @param productType
     */
    private fun loadPlans(fstId: String, funcId: String, loginId: String, startDate: String, endDate: String, productType: String): List<LinePlanBackInfo>{
        var result: ProLinePlanBean?
        runBlocking {
            withContext(Dispatchers.IO){
                result = RequestUtil.request(context) {
                    HttpClient.getHttpService().getProductLinePlanList(fstId, funcId, loginId, startDate, endDate, productType).execute()
                }
            }
        }
        return convertPlans(result)
    }

    /**
     * 返回处理过的，得到想要的数据
     */
    private fun convertPlans(line: ProLinePlanBean?): List<LinePlanBackInfo>{
        val list = mutableListOf<LinePlanBackInfo>()
        if (line == null)
            return list
        val liList= line.data
        for (index in liList.linePlans.indices) {
            list.add(LinePlanBackInfo(liList.linePlans[index].taskName, liList.linePlans[index].GwString,
                liList.linePlans[index].itemColor, liList.linePlans[index].TeamTime, liList.linePlans[index].TeamDate))
        }

        return lineSort(list)

    }

    /**
     * 根据 TeamTime 升序排序
     * @param list
     */
    private fun lineSort(list: List<LinePlanBackInfo>): List<LinePlanBackInfo> {
        Collections.sort(list, kotlin.Comparator { o1, o2 ->
            return@Comparator o1.TeamTime.compareTo(o2.TeamTime)
        })
        return list
    }

    /**
     * 日期格式转换 "2021/7/2 16:00:00" to "7月2日"
     * @param string
     */
    private fun convertDate(string: String): String {
        if (string.isEmpty()){
            return string
        }
        val s = string.split(" ")[0]
        val month = s.split("/")[1]
        val day = s.split("/")[2]

//        Log.i("da", date)
        return month + "月" + day + "日"
    }

}

