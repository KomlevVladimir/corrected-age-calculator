package com.vladimirkomlev.correctedagecalculator.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.vladimirkomlev.correctedagecalculator.R
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val postNatalAge = intent.extras!!["post_natal_age"] as Long
        val gestAge = intent.extras!!["gest_age"] as Long

        val summaryAge = postNatalAge + gestAge

        val correctedAgeInWeeks = (postNatalAge + gestAge) - 40
        val correctedAgeInMonths = correctedAgeInWeeks / 4

        if (summaryAge <= 40) {
            result.text = getString(
                R.string.post_conceptual_age,
                summaryAge,
                spellWeek(summaryAge)
            )
        } else {
            result.text = getString(
                R.string.corrected_age,
                correctedAgeInWeeks,
                spellWeek(correctedAgeInWeeks),
                correctedAgeInMonths,
                spellMonth(correctedAgeInMonths)
            )
        }

        btn_back.setOnClickListener {
            finish()
        }

        btn_psycho.setOnClickListener {
            val intent = Intent(this@ResultActivity, PsychoActivity::class.java)
            intent.putExtra("summary_age", summaryAge)
            intent.putExtra("corrected_age", correctedAgeInMonths)
            startActivity(intent)
        }

        btn_physical.setOnClickListener {
            val intent = Intent(this@ResultActivity, PhysicalActivity::class.java)
            intent.putExtra("summary_age", summaryAge)
            intent.putExtra("corrected_age", correctedAgeInMonths)
            startActivity(intent)
        }
    }

    private fun spellMonth(months: Long) =
        when {
            months in 2..4 -> "месяца"
            months == 1L -> "месяц"
            else -> "месяцев"
        }

    private fun spellWeek(weeks: Long) =
        when {
            (weeks % 10 == 1L && weeks != 11L)  -> "неделю"
            weeks in 11..20 || weeks % 10 in 5..9 || weeks % 10 == 0L -> "недель"
            else -> "недели"
        }
}
