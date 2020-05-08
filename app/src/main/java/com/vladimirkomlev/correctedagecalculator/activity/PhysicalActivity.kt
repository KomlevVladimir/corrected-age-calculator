package com.vladimirkomlev.correctedagecalculator.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.vladimirkomlev.correctedagecalculator.R
import kotlinx.android.synthetic.main.activity_physical.*
import kotlinx.android.synthetic.main.toolbar.*
import kotlinx.android.synthetic.main.toolbar.toolbar

class PhysicalActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_physical)

        toolbar_text_view.text = getString(R.string.psycho_toolbar_title)
        toolbar.setBackgroundColor(resources.getColor(R.color.physical))
        setSupportActionBar(toolbar)

        if (supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            supportActionBar!!.setDisplayShowHomeEnabled(true)
        }

        val summaryAge = intent.extras!!["summary_age"] as Long
        val correctedAge = intent.extras!!["corrected_age"] as Long

        when {
            summaryAge <= 40 || correctedAge < 1 -> physical_description.text = getString(R.string.physical_zero_month)
            correctedAge == 1L -> physical_description.text = getString(R.string.physical_first_month)
            correctedAge == 2L -> physical_description.text = getString(R.string.physical_second_month)
            correctedAge == 3L -> physical_description.text = getString(R.string.physical_third_month)
            correctedAge == 4L -> physical_description.text = getString(R.string.physical_fourth_month)
            correctedAge == 5L -> physical_description.text = getString(R.string.physical_fifth_month)
            correctedAge == 6L -> physical_description.text = getString(R.string.physical_sixth_month)
            correctedAge == 7L -> physical_description.text = getString(R.string.physical_seventh_month)
            correctedAge == 8L -> physical_description.text = getString(R.string.physical_eighth_month)
            correctedAge == 9L -> physical_description.text = getString(R.string.physical_ninth_month)
            correctedAge == 10L -> physical_description.text = getString(R.string.physical_tenth_month)
            correctedAge == 11L -> physical_description.text = getString(R.string.physical_eleventh_month)
            correctedAge == 12L -> physical_description.text = getString(R.string.physical_twelfth_month)
            correctedAge in 13..16 -> physical_description.text = getString(R.string.physical_thirteenth_to_sixteenth_month)
            correctedAge in 17..20 -> physical_description.text = getString(R.string.physical_seventeenth_to_twentieth_month_month)
            correctedAge in 21..24 -> physical_description.text = getString(R.string.physical_twenty_first_to_twenty_fourth_month)
        }

        btn_back_to_result.setOnClickListener {
            finish()
        }

        btn_back_to_calculator.setOnClickListener {
            val intent = Intent(this@PhysicalActivity, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        this.finish()
        return true
    }
}
