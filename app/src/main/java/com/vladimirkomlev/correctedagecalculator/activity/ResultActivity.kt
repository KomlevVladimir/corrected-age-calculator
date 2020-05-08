package com.vladimirkomlev.correctedagecalculator.activity

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBar
import com.vladimirkomlev.correctedagecalculator.R
import com.vladimirkomlev.correctedagecalculator.utils.spellMonth
import com.vladimirkomlev.correctedagecalculator.utils.spellWeek
import kotlinx.android.synthetic.main.activity_result.*
import kotlinx.android.synthetic.main.toolbar.*
import kotlinx.android.synthetic.main.toolbar.toolbar

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        toolbar_text_view.text = getString(R.string.result_toolbar_title)
        toolbar.setBackgroundColor(resources.getColor(R.color.green))
        setSupportActionBar(toolbar)

        if (supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            supportActionBar!!.setDisplayShowHomeEnabled(true)
        }

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
                R.string.corrected_age_result,
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        this.finish()
        return true
    }
}
