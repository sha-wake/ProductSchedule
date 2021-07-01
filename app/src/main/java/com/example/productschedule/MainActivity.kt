package com.example.productschedule

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.widget.DatePicker
import androidx.appcompat.app.AppCompatActivity
import com.bin.david.form.data.column.ArrayColumn
import com.bin.david.form.data.column.Column
import com.bin.david.form.data.table.TableData
import com.example.productschedule.bean.TeamDate
import com.example.productschedule.bean.TeamTime
import com.example.productschedule.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity(){
    private lateinit var binding: ActivityMainBinding
    private lateinit var format: SimpleDateFormat
//    private lateinit var sTime: String
//    private lateinit var eTime: String
    private lateinit var calBegin: Calendar
    private lateinit var calEnd: Calendar
    private lateinit var dateList: MutableList<String>
    private lateinit var teamTime: MutableList<TeamTime>
    private lateinit var teamDate: MutableList<TeamDate>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        onClick()
        tableCreated()
    }

    private fun onClick(){
        binding.startTime.setOnClickListener {
            showDialog()
        }
        binding.endTime.setOnClickListener{
            showDialogTwo()
        }
        binding.proType.setOnClickListener{
            getSelectDialog();
        }
        binding.tableSetting.setOnClickListener{
            getTableSettingDialog()
        }

    }

    /**
     * 采用普通的方式 create table, 注解的方式还没试过
     * teamTime、 teamDate  自定义的 bean
     * tableDta 表格数据
     */
    @SuppressLint("SimpleDateFormat")
    private fun tableCreated() {
        teamTime = mutableListOf<TeamTime>()
        teamTime.add(TeamTime("白"))
        teamTime.add(TeamTime("早"))
        teamTime.add(TeamTime("中"))

        teamDate = mutableListOf<TeamDate>()
        for (a in 0..9){
            val sdf = SimpleDateFormat("MM月dd日")
            val c = Calendar.getInstance()
            c.time = Date()
            c.add(Calendar.DATE, +a)
            val d = c.time
            val day = sdf.format(d)
            teamDate.add(TeamDate(day, teamTime, "21012FCW12-F406PS-0.55*996*130=214", 30,">4.5"))
        }

//        测试数据
//        teamDate.add(TeamDate("6月20日", teamTime))
//        teamDate.add(TeamDate("6月21日", teamTime))
//        teamDate.add(TeamDate("6月22日", teamTime))
//        teamDate.add(TeamDate("6月23日", teamTime))
//        teamDate.add(TeamDate("6月24日", teamTime))
//        teamDate.add(TeamDate("6月25日", teamTime))
//        teamDate.add(TeamDate("6月26日", teamTime))
//        teamDate.add(TeamDate("6月27日", teamTime))
//        teamDate.add(TeamDate("6月28日", teamTime))
//        teamDate.add(TeamDate("6月29日", teamTime))
//        teamDate.add(TeamDate("6月30日", teamTime))

        val teamDateColumn = Column<String>("日期", "teamDate")                          //普通行用Column
        val teamTimeColumn = ArrayColumn<String>("班次", "teamTimes.teamTime")           //普通行的子行用ArrayColumn
        val proMessageColumn = Column<String>("产品信息", "proMessage")
        val proSpeedColumn = Column<Int>("产线速度", "proSpeed")
        val proWeightColumn = Column<String>("克重", "proWeight")
        val tableData = TableData("产线表", teamDate, teamDateColumn, teamTimeColumn, proMessageColumn, proSpeedColumn, proWeightColumn)

        binding.proTable.setTableData(tableData)
        tableConfig()
    }

    // 表格部分设置，共同部分可调用此项
    // 不同设置自行添加，不放这
    private fun tableConfig() {
        binding.proTable.config.isShowTableTitle = true
        binding.proTable.config.isShowXSequence = false                                                          //去除标题行上面的ABCD
        binding.proTable.config.isShowYSequence = true
        binding.proTable.config.minTableWidth = 800                                                              //设定最小间距、列数少适应屏幕
        binding.proTable.setZoom(true, 2F, 0.65F)                                     //设定缩放比例大小
        binding.proTable.config.isShowTableTitle = false
    }

    /**
     * 选择时间和产品类型之后，改变表格结构，重新生成
     */
    @SuppressLint("SimpleDateFormat")
    private fun tableChanged() {
        format = SimpleDateFormat("MM月dd日")
//        val c = Calendar.getInstance()
//        c.time = Date()
//        c.add(Calendar.DATE, +a)
//        val d = c.time
//        val day = sdf.format(d)
//        val st = sdf.parse(sTime)
//        val et = sdf.parse(eTime)
//        val calBegin = Calendar.getInstance()
        dateList = mutableListOf<String>()
        dateList .add(format.format(calBegin.time))
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
     * @param sDate 开始时间
     * @param eDate 结束时间
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

    // 把获得的两个时间作计较，按天显示一行数据，怎么说？
    @SuppressLint("InflateParams", "ResourceType", "SimpleDateFormat")
    private fun showDialog(){
//        val format = SimpleDateFormat("MM月dd日")
        format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val calendar = Calendar.getInstance(Locale.CHINA)
        val datePickerDialog = DatePickerDialog(this@MainActivity,
            { _: DatePicker, year, month, dayOfMonth -> //修改日历控件的年，月，日
                //这里的year,monthOfYear,dayOfMonth的值与DatePickerDialog控件设置的最新值一致
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
//        val format = DateFormat.getDateTimeInstance("MM月dd日") //获取日期格式器对象
//        val format = SimpleDateFormat("MM月dd日")
        format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val calendar = Calendar.getInstance(Locale.CHINA)
        val datePickerDialog = DatePickerDialog(this@MainActivity,
            { _: DatePicker, year, month, dayOfMonth -> //修改日历控件的年，月，日
                //这里的year,monthOfYear,dayOfMonth的值与DatePickerDialog控件设置的最新值一致
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


    /**
     * 选择不同类型产品，表格会生成不同的产线安排计划
     */
    private fun getSelectDialog() {
        var proType = "全部"
        val arrayType = arrayOf("全部", "白膜", "透明EVA","交联型POE","热塑型POE","共挤胶膜","有色胶膜")
        val builder = AlertDialog.Builder(this)
        builder.setIcon(R.drawable.ic_search)
        builder.setTitle("  选择产品类型")
        builder.setSingleChoiceItems(arrayType, 1) { _, which ->
            proType = arrayType[which]
            //do something
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
     *
     * 飞滚功能，安卓界面的滚动适配
     * 提供表格四个放置方位： 左、右、顶和底
     *
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

}