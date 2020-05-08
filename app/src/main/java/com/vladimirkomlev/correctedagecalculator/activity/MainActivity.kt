package com.vladimirkomlev.correctedagecalculator.activity

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.vladimirkomlev.correctedagecalculator.utils.validateDateOfBirth
import com.vladimirkomlev.correctedagecalculator.utils.validateGestAge
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Long.parseLong
import java.text.SimpleDateFormat
import java.util.*
import java.util.Calendar.*
import com.vladimirkomlev.correctedagecalculator.R
import org.joda.time.DateTime
import org.joda.time.Weeks


class MainActivity : AppCompatActivity() {
    private val calendar = getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        date_of_birth_input.apply {
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
                validateGestAge(this, gest_age_input) &&
                validateDateOfBirth(
                    this,
                    date_of_birth_input
                )
            ) {
                val postNatalAge = calculatePostNatalAge(calendar.time).toLong()
                val gestAge = parseLong(gest_age_input.text.toString())

                val intent = Intent(this@MainActivity, ResultActivity::class.java)
                intent.putExtra("post_natal_age", postNatalAge)
                intent.putExtra("gest_age", gestAge)
                startActivity(intent)
            }
        }
    }

    private fun setDate() {
        val sdf = SimpleDateFormat("dd MMMM yyyy", Locale("ru", "RU"))
        date_of_birth_input.setText(sdf.format(calendar.time))
    }

    private val onDateSetListener = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
        calendar[YEAR] = year
        calendar[MONTH] = month
        calendar[DAY_OF_MONTH] = dayOfMonth
        setDate()
    }

    private fun calculatePostNatalAge(date: Date) =
        Weeks.weeksBetween(DateTime(date), DateTime(Date())).weeks
}
