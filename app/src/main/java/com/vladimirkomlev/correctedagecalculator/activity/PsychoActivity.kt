package com.vladimirkomlev.correctedagecalculator.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.vladimirkomlev.correctedagecalculator.R
import kotlinx.android.synthetic.main.activity_psycho.*

class PsychoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_psycho)

        val summaryAge = intent.extras!!["summary_age"] as Long
        val correctedAge = intent.extras!!["corrected_age"] as Long

        when {
            summaryAge <= 40 || correctedAge < 1 -> psycho.text = getString(R.string.psycho_zero_month)
            correctedAge == 1L -> psycho.text = getString(R.string.psycho_first_month)
            correctedAge == 2L -> psycho.text = getString(R.string.psycho_second_month)
            correctedAge == 3L -> psycho.text = getString(R.string.psycho_third_month)
            correctedAge == 4L -> psycho.text = getString(R.string.psycho_fourth_month)
            correctedAge == 5L -> psycho.text = getString(R.string.psycho_fifth_month)
            correctedAge == 6L -> psycho.text = getString(R.string.psycho_sixth_month)
            correctedAge == 7L -> psycho.text = getString(R.string.psycho_seventh_month)
            correctedAge == 8L -> psycho.text = getString(R.string.psycho_eighth_month)
            correctedAge == 9L -> psycho.text = getString(R.string.psycho_ninth_month)
            correctedAge == 10L -> psycho.text = getString(R.string.psycho_tenth_month)
            correctedAge == 11L -> psycho.text = getString(R.string.psycho_eleventh_month)
            correctedAge == 12L -> psycho.text = getString(R.string.psycho_twelfth_month)
            correctedAge in 13..16 -> psycho.text = getString(R.string.psycho_thirteenth_to_sixteenth_month)
            correctedAge in 17..20 -> psycho.text = getString(R.string.psycho_seventeenth_to_twentieth_month)
            correctedAge in 21..24 -> psycho.text = getString(R.string.psycho_twenty_first_to_twenty_fourth)
        }

        btn_back_to_result.setOnClickListener {
            finish()
        }

        btn_back_to_calculator.setOnClickListener {
            val intent = Intent(this@PsychoActivity, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
        }
    }
}
