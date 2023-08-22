package com.isaiahmcnealy.agecalculator

import android.app.DatePickerDialog
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private var tv_selectedDate: TextView? = null //=
    private var tv_ageInMinutes: TextView? = null //=


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv_ageInMinutes = findViewById(R.id.tv_minutes)
        tv_selectedDate = findViewById(R.id.tv_dateSelected)

        val btnDatePicker: Button = findViewById(R.id.btn_calculateAgeInMinutes)

        btnDatePicker.setOnClickListener {
            val myCalender = Calendar.getInstance()
            val year = myCalender.get(Calendar.YEAR)
            val month = myCalender.get(Calendar.MONTH)
            val date = myCalender.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(
                    this,
                    DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                        pickDate(this, year, month, dayOfMonth)

                        // set textview to the selected date
                        val selectedDate: String  = "$dayOfMonth/${month+1}/$year"
                        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                        val date = sdf.parse(selectedDate)

                        date?.let {
                            // set textview to the calculated age in minutes
                            val selectedDateInMinutes = date.time / 60000
                            val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                            currentDate?.let {
                                val currentDateInMinutes = currentDate.time / 60000
                                val differenceInMinutes = selectedDateInMinutes - currentDateInMinutes
                                tv_selectedDate?.text = date.toString()
                                tv_ageInMinutes?.text = differenceInMinutes.toString()
                            }
                        }
                    },
                    year,
                    month,
                    date
                )
                datePickerDialog.datePicker.maxDate = System.currentTimeMillis() - 86400000
                datePickerDialog.show()
        }
    }
}

fun pickDate(context: Context, year: Int, month: Int, date: Int){
    Toast.makeText(context,"year: $year, month: ${month+1}, date: $date", Toast.LENGTH_SHORT).show()
    Log.d("BUTTON","button pressed")
}


fun updateDateSelectedTextView(year: Int, month: Int, dayOfMonth: Int)  {

}