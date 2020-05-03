package com.vladimirkomlev.correctedagecalculator.activity

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.vladimirkomlev.correctedagecalculator.R
import com.vladimirkomlev.correctedagecalculator.utils.validateDateOfBirth
import com.vladimirkomlev.correctedagecalculator.utils.validateGestAge
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Long.parseLong
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDate.*
import java.time.ZoneId
import java.time.temporal.ChronoUnit.WEEKS
import java.util.*
import java.util.Calendar.*

class MainActivity : AppCompatActivity() {
    private val calendar = getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        date_of_birth.apply {
            isFocusableInTouchMode = false
            isFocusable = false
            setOnClickListener {
                DatePickerDialog(
                    this@MainActivity,
                    onDateSetListener,
                    calendar[YEAR],
                    calendar[MONTH],
                    calendar[DAY_OF_MONTH]
                    ).show()
            }
        }

        btn_caclulate.setOnClickListener {
            if (
                validateGestAge(this, gest_age) &&
                validateDateOfBirth(
                    this,
                    date_of_birth
                )
            ) {
                val postNatalAge = calculatePostNatalAge(getDate())
                val gestAge = parseLong(gest_age.text.toString())

                val intent = Intent(this@MainActivity, ResultActivity::class.java)
                intent.putExtra("post_natal_age", postNatalAge)
                intent.putExtra("gest_age", gestAge)
                startActivity(intent)
            }
        }

    }

    private fun setDate() {
        val sdf = SimpleDateFormat("dd MMMM yyyy", Locale.US)
        date_of_birth.setText(sdf.format(calendar.time))
    }

    private fun getDate(): LocalDate = calendar.time.toInstant().atZone(ZoneId.systemDefault())
        .toLocalDate()


    private val onDateSetListener = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
        calendar[YEAR] = year
        calendar[MONTH] = month
        calendar[DAY_OF_MONTH] = dayOfMonth
        setDate()
    }

    fun calculatePostNatalAge(date: LocalDate) = WEEKS.between(date, now())
}
