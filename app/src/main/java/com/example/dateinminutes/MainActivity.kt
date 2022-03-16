package com.example.dateinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val butDOB: Button=findViewById(R.id.buttonDOB)
        butDOB.setOnClickListener {
            clickDatePicker()
        }
    }

    private fun clickDatePicker(){
        val myCalender:Calendar= Calendar.getInstance()
        val year=myCalender.get(Calendar.YEAR)
        val month=myCalender.get(Calendar.MONTH)
        val date=myCalender.get(Calendar.DAY_OF_MONTH)

        val tvSelectedDate:TextView =findViewById(R.id.tvSelectedDate)
        val tvAgeInMin:TextView =findViewById(R.id.ageInMin)

        val bdate= DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDate ->

            val selectedDOB ="$selectedDate/${selectedMonth+1}/$selectedYear"
            tvSelectedDate.text=selectedDOB
            val sdf= SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

            val DOB=sdf.parse(selectedDOB)
            DOB?.let {
                val ageMin=DOB.time/60000
                val currentTime=sdf.parse(sdf.format(System.currentTimeMillis()))
                currentTime?.let{
                    val currentTimeInMin=currentTime.time/60000
                    val ageInMin= currentTimeInMin - ageMin
                    tvAgeInMin.text=ageInMin.toString()

                }
            }
        },year,month,date)

        bdate.datePicker.maxDate = System.currentTimeMillis() - 86400000
        bdate.show()

    }
}