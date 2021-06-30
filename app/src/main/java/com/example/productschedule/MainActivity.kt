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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.startTime.setOnClickListener {
            showDialog()
        }
        binding.endTime.setOnClickListener{
            showDialogTwo()
        }
        binding.proLine.setOnClickListener{
            getSelectDialog();
        }
        tableCreated()
    }

    /**
     * 采用普通的方式 create table, 注解的方式还没试过
     * teamTime、 teamDate  自定义的 bean
     * tableDta 表格数据
     */
    @SuppressLint("SimpleDateFormat")
    private fun tableCreated() {
        val teamTime = mutableListOf<TeamTime>()
        teamTime.add(TeamTime("白"))
        teamTime.add(TeamTime("早"))
        teamTime.add(TeamTime("中"))

        val teamDate = mutableListOf<TeamDate>()
        teamDate.add(TeamDate("6月20日", teamTime))
        teamDate.add(TeamDate("6月21日", teamTime))
        teamDate.add(TeamDate("6月22日", teamTime))
        teamDate.add(TeamDate("6月23日", teamTime))
        teamDate.add(TeamDate("6月24日", teamTime))
        teamDate.add(TeamDate("6月25日", teamTime))
        teamDate.add(TeamDate("6月26日", teamTime))
        teamDate.add(TeamDate("6月27日", teamTime))
        teamDate.add(TeamDate("6月28日", teamTime))
        teamDate.add(TeamDate("6月29日", teamTime))
        teamDate.add(TeamDate("6月30日", teamTime))

        val teamDateColumn = Column<String>("日期", "teamDate")                          //普通行用Column
        val teamTimeColumn = ArrayColumn<String>("班次", "teamTimes.teamTime")           //普通行的子行用ArrayColumn
//        val proMessageColumn = Column<String>("产品信息", "proMessage")
//        val proWeightColumn = Column<String>("克重", "proWeight")
        val tableData = TableData("产线表", teamDate, teamDateColumn, teamTimeColumn)

        binding.proTable.setTableData(tableData)
        binding.proTable.config.isShowTableTitle = true
        binding.proTable.config.isShowXSequence = false                                                          //去除标题行上面的ABCD
        binding.proTable.config.isShowYSequence = true
        binding.proTable.config.minTableWidth = 800                                                              //设定最小间距、列数少适应屏幕
//        binding.proTable.setZoom(true, 2F, 0.7F)                                     //设定缩放比例大小
        binding.proTable.config.isShowTableTitle = false


    }

    /**
     * 根据获取的起止时间来显示多少列
     * @param sDate 开始时间
     * @param eDate 结束时间
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
    @SuppressLint("InflateParams", "ResourceType", "SimpleDateFormat")
    private fun showDialog(){
        val format = SimpleDateFormat("MM月dd日")
        val calendar = Calendar.getInstance(Locale.CHINA)
        val datePickerDialog = DatePickerDialog(this@MainActivity,
            { _: DatePicker, year, month, dayOfMonth -> //修改日历控件的年，月，日
                //这里的year,monthOfYear,dayOfMonth的值与DatePickerDialog控件设置的最新值一致
                calendar[Calendar.YEAR] = year
                calendar[Calendar.MONTH] = month
                calendar[Calendar.DAY_OF_MONTH] = dayOfMonth
                Log.i("date2", format.format(calendar.time))
            }, calendar[Calendar.YEAR], calendar[Calendar.MONTH], calendar[Calendar.DAY_OF_MONTH]
        )
        datePickerDialog.show()
    }
    @SuppressLint("SimpleDateFormat")
    private fun showDialogTwo(){
//        val format = DateFormat.getDateTimeInstance("MM月dd日") //获取日期格式器对象
        val format = SimpleDateFormat("MM月dd日")
        val calendar = Calendar.getInstance(Locale.CHINA)
        val datePickerDialog = DatePickerDialog(this@MainActivity,
            { _: DatePicker, year, month, dayOfMonth -> //修改日历控件的年，月，日
                //这里的year,monthOfYear,dayOfMonth的值与DatePickerDialog控件设置的最新值一致
                calendar[Calendar.YEAR] = year
                calendar[Calendar.MONTH] = month
                calendar[Calendar.DAY_OF_MONTH] = dayOfMonth
                Log.i("date2", format.format(calendar.time))
            }, calendar[Calendar.YEAR], calendar[Calendar.MONTH], calendar[Calendar.DAY_OF_MONTH]
        )
        datePickerDialog.show()
    }

    private fun getSelectDialog() {
        var proType: String = "全部";
        val array2 = arrayOf("全部", "EVA", "白膜")
        val builder = AlertDialog.Builder(this)
        builder.setIcon(R.drawable.ic_search)
        builder.setTitle("选择产品类型")
        builder.setSingleChoiceItems(array2, 1) { dialog, which ->
            proType = array2[which]
            Log.e("TAG", "select ${array2[which]}")
        }
        val dialogClickListener = DialogInterface.OnClickListener { _, which ->
            when (which) {
                DialogInterface.BUTTON_POSITIVE -> {
                    Log.e("TAG", "click yes,$proType")
                }
                DialogInterface.BUTTON_NEGATIVE -> {
                    Log.e("TAG", "click no，$proType")
                }
            }
        }
        builder.setPositiveButton("确定", dialogClickListener)
        builder.setNegativeButton("取消", dialogClickListener)
        builder.create().show();
    }

}