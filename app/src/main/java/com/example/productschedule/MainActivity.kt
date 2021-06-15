package com.example.productschedule

//import com.example.productschedule.bean.TeamTime


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bin.david.form.data.column.ArrayColumn
import com.bin.david.form.data.column.Column
import com.bin.david.form.data.table.TableData
import com.example.productschedule.bean.TeamDate

import com.example.productschedule.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        tableCreated()
    }

    private fun tableCreated() {
        val weekNameColumn = Column<String>("日期","teamDate")
        val timeNameColumn = Column<String>("班次","teamTimes")
//        val nameColumn = Column<String>("姓名", "name")
//        val ageColumn = Column<Int>("年龄", "age")
        val list = ArrayList<TeamDate>()
        list.add(TeamDate("das", "dsgdfga"))
        val tableData =  TableData("透明EVA产线表", list, weekNameColumn, timeNameColumn)
        binding.proTable.setTableData(tableData)
        binding.proTable.config.isShowXSequence = false
        binding.proTable.config.minTableWidth = 1000

    }

}