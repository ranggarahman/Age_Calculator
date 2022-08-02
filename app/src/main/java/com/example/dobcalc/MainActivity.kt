package com.example.dobcalc

import android.app.DatePickerDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    /**

    green comment

     */
    private var tvSelectedDate : TextView? = null
    private var tvAgeInMinutes : TextView? = null
    private var tvAgeInHours : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker : Button = findViewById<Button>(R.id.btnDatePicker)
        tvSelectedDate = findViewById<TextView>(R.id.tvSelectedDate)
        tvAgeInMinutes = findViewById(R.id.tvAgeInMinutes)
        tvAgeInHours = findViewById(R.id.tvAgeInHours)

        btnDatePicker.setOnClickListener {
            clickDatePicker()
        }
    }

    private fun clickDatePicker(){

        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val date = myCalendar.get(Calendar.DAY_OF_MONTH)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            val dpd = DatePickerDialog(this,
                DatePickerDialog.OnDateSetListener{ _, selectedYear,
                                                    selectedMonth, selectedDayOfMonth ->
                    Toast.makeText(this,
                "Clicked", Toast.LENGTH_SHORT).show()
                    val selectedDate = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"

                    tvSelectedDate?.text = selectedDate

                    val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH )

                    val theDate = sdf.parse(selectedDate)
                    theDate?.let { val selectedDateInMinutes = theDate.time / 60_000

                        val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                        currentDate?.let {
                            val currentDateInMinutes = currentDate.time / 60_000

                            val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes

                            tvAgeInMinutes?.text  = String.format(Locale.US,"%,d", differenceInMinutes)
                            tvAgeInHours?.text = String.format(Locale.US,"%,d", (differenceInMinutes/60))
                        }

                    }
                                                  },
                year, month, date)

            dpd.datePicker.maxDate = System.currentTimeMillis() - 86_400_000
            dpd.show()
        }

/*        Toast.makeText(this,
            "Choose a Date", Toast.LENGTH_SHORT).show()*/
    }
}