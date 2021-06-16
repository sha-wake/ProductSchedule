package com.example.productschedule

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bin.david.form.data.column.ArrayColumn
import com.bin.david.form.data.column.Column
import com.bin.david.form.data.table.TableData
import com.example.productschedule.bean.TeamDate
import com.example.productschedule.bean.TeamTime

import com.example.productschedule.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        tableCreated()
    }

    /**
     * 采用普通的方式 create table, 注解的方式还没试过
     * teamTime、 teamDate  自定义的 bean
     * tableDta 表格数据
     */
    private fun tableCreated() {
        val teamTime = mutableListOf<TeamTime>()
        teamTime.add(TeamTime("白"))
        teamTime.add(TeamTime("早"))
        teamTime.add(TeamTime("中"))

        val teamDate = mutableListOf<TeamDate>()
        teamDate.add(TeamDate("6月15日", teamTime))
        teamDate.add(TeamDate("6月16日", teamTime))

        val teamDateColumn = Column<String>("日期", "teamDate")                          //普通行用Column
        val teamTimeColumn = ArrayColumn<String>("班次", "teamTimes.teamTime")           //普通行的子行用ArrayColumn
        val tableData = TableData("产线表", teamDate, teamDateColumn, teamTimeColumn)

        binding.proTable.setTableData(tableData)
        binding.proTable.config.isShowTableTitle = true
        binding.proTable.config.isShowXSequence = false                                                          //去除标题行上面的ABCD
        binding.proTable.config.isShowYSequence = true
        binding.proTable.config.minTableWidth = 1000                                                             //设定最小间距、列数少适应屏幕

    }

}