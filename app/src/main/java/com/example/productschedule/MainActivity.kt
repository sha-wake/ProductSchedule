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
        tableCreated()                                                                                // ???????????????????????????????????????????????????

//        try{
//            val sdf: DateFormat = SimpleDateFormat("MM???dd???")
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

//            proFirst.columnName = "helle"                                                             // ????????????

//            teamTime = mutableListOf<TeamTime>()
//            teamTime.add(TeamTime("???", "", ""))
//            teamTime.add(TeamTime("???", "", ""))
//            teamTime.add(TeamTime("???", "", ""))

//            for (i in 0..2) {
//                val time = when(testPlan[i].TeamTime) {
//                    "1" -> "???"
//                    "2" -> "???"
//                    else -> "???"
//                }
//                teamTime.add(TeamTime(time, testPlan[i].taskName, testPlan[i].GwString))
//            }
//
//            teamDate = mutableListOf<TeamDate>()
//            for (a in 0..1){
//                val sdf = SimpleDateFormat("M???d???")
//                val c = Calendar.getInstance()
//                c.time = Date()
//                c.add(Calendar.DATE, +a)
//                val d = c.time
//                val day = sdf.format(d)
//                teamDate.add(TeamDate(day, teamTime))
//            }
//            try{
//                val sdf: DateFormat = SimpleDateFormat("M???d???")
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
     * ????????????????????? create table, ???????????????????????????
     * teamTime??? teamDate  ???????????? bean
     * tableDta ????????????
     */
    @SuppressLint("SimpleDateFormat")
    private fun tableCreated() {

        teamTime = mutableListOf<TeamTime>()
//        teamTime.add(TeamTime("???", "", ""))
//        teamTime.add(TeamTime("???", "", ""))
//        teamTime.add(TeamTime("???", "", ""))

        teamDate = mutableListOf<TeamDate>()
        /*for (a in 0..9){
            val sdf = SimpleDateFormat("M???d???")
            val c = Calendar.getInstance()
            c.time = Date()
            c.add(Calendar.DATE, +a)
            val d = c.time
            val day = sdf.format(d)
            teamDate.add(TeamDate(day, teamTime))
        }*/

        teamDateColumn = Column<String>("??????", "teamDate")                          // ????????????Column
        teamTimeColumn = ArrayColumn<String>("??????", "teamTimes.teamTime")           // ?????????????????????ArrayColumn
        val proMessageColumn = ArrayColumn<String>("????????????", "teamTimes.proMessage")
//        val proSpeedColumn = ArrayColumn<Int>("????????????", "proSpeed")
        val proWeightColumn = ArrayColumn<String>("??????", "teamTimes.proWeight")
        proFirst = Column<String>("P4-2#???1040", proMessageColumn, proWeightColumn)
//        val tableData = TableData("?????????", teamDate, teamDateColumn, teamTimeColumn, proMessageColumn, proSpeedColumn, proWeightColumn)
        tableData = TableData("?????????", teamDate, teamDateColumn, teamTimeColumn, proFirst)

        binding.proTable.setTableData(tableData)
//        teamDateColumn.isFixed = true                                                                           // ???????????????????????????????????????
//        teamTimeColumn.isFixed = true
        
        tableConfig()
    }

    /**
     * ????????????????????????????????????????????????
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

    // ????????????????????????????????????????????????
    // ????????????????????????????????????
    private fun tableConfig() {
        binding.proTable.config.isShowTableTitle = true
        binding.proTable.config.isShowXSequence = false                                                         // ??????????????????????????????
        binding.proTable.config.isShowYSequence = true
        binding.proTable.config.minTableWidth = 800                                                             // ??????????????????????????????????????????
        binding.proTable.setZoom(true, 2F, 0.65F)                                   // ????????????????????????
        binding.proTable.config.isShowTableTitle = false
//        binding.proTable.tableData.isShowCount = true                                                         // ???????????????
    }


    // ?????????????????????????????????????????????????????????????????????
    @SuppressLint("SimpleDateFormat")
    private fun tableChanged() {
//        format = SimpleDateFormat("MM???dd???")                                                           // ??????????????????
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

        // ??????????????????????????????????????????????????????????????????
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
     * ?????????????????????????????????????????????
     * ????????????
     */
    /*
    ????????????????????????????????????????????????
    for (a in 1..7){
    val sdf = SimpleDateFormat("MM???dd???")
    val c = Calendar.getInstance()
    c.time = Date()
    c.add(Calendar.DATE, +a)
    val d = c.time
    val day = sdf.format(d)
    Log.d("DATE", day)
    */

    // ????????????????????????????????????????????????????????????
    @SuppressLint("InflateParams", "ResourceType", "SimpleDateFormat")
    private fun showDialog(){
//        val format = SimpleDateFormat("MM???dd???")
        format = SimpleDateFormat("yyyy-MM-dd")
        val calendar = Calendar.getInstance(Locale.CHINA)

        //?????????year,monthOfYear,dayOfMonth?????????DatePickerDialog??????????????????????????????
        val datePickerDialog = DatePickerDialog(this@MainActivity,
            { _: DatePicker, year, month, dayOfMonth ->                                                     // ????????????????????????????????????
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
//        val format = DateFormat.getDateTimeInstance("MM???dd???")                                           // ???????????????????????????
//        val format = SimpleDateFormat("MM???dd???")
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


    // ?????????????????????????????????????????????????????????????????????
    private fun getSelectDialog() {
        var proType: String
        val arrayType = arrayOf("??????", "??????", "??????EVA","?????????POE","?????????POE","????????????","????????????")
        val builder = AlertDialog.Builder(this)
        builder.setIcon(R.drawable.ic_search)
        builder.setTitle("  ??????????????????")
        builder.setSingleChoiceItems(arrayType, 1) { _, which ->
            proType = arrayType[which]
            binding.proType.text = proType                                                                     // ????????????????????????????????????
//            Log.e("TAG", "select ${array2[which]}")
        }

        val dialogClickListener = DialogInterface.OnClickListener { _, which ->
            when (which) {
                DialogInterface.BUTTON_POSITIVE -> {
//                    Log.e("TAG", "click yes,$proType")
                }
                DialogInterface.BUTTON_NEGATIVE -> {
//                    Log.e("TAG", "click no???$proType")
                }
            }
        }

        builder.setPositiveButton("??????", dialogClickListener)
        builder.setNegativeButton("??????", dialogClickListener)
        builder.create().show()
    }

    /**
     * ??????????????????????????????????????????
     * ????????????????????????????????? ?????????????????????
     */
    private fun getTableSettingDialog() {
        val arrayFly = arrayOf("??????", "??????", "??????","??????")
        val builder = AlertDialog.Builder(this)
        builder.setIcon(R.drawable.ic_table)
        builder.setTitle("  ??????")
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
                        .message("????????????")
                        .duration(1000)
                        .iconSize(24)
                        .textSize(12)
                        .build()

                }
                DialogInterface.BUTTON_NEGATIVE -> {
//                    Log.e("TAG", "click no???$proType")
                }
            }
        }

        builder.setPositiveButton("??????", dialogClickListener)
        builder.setNegativeButton("??????", dialogClickListener)
        builder.create().show()
    }

    /**
     * ????????????????????????
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
     * ??????????????????????????????????????????
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
     * ?????? TeamTime ????????????
     * @param list
     */
    private fun lineSort(list: List<LinePlanBackInfo>): List<LinePlanBackInfo> {
        Collections.sort(list, kotlin.Comparator { o1, o2 ->
            return@Comparator o1.TeamTime.compareTo(o2.TeamTime)
        })
        return list
    }

    /**
     * ?????????????????? "2021/7/2 16:00:00" to "7???2???"
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
        return month + "???" + day + "???"
    }

}

