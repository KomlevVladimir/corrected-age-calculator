package com.vladimirkomlev.correctedagecalculator.utils

import android.content.Context
import android.widget.EditText
import com.vladimirkomlev.correctedagecalculator.R
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.ZoneId
import java.time.temporal.ChronoUnit.YEARS
import java.util.*

val sdf = SimpleDateFormat("dd MMMM yyyy", Locale.US)

fun validateGestAge(context: Context, editText: EditText): Boolean {
    val text = editText.text.toString().trim()
    if (text == "" || Integer.parseInt(text) !in 36 downTo 22) {
        showError(
            context,
            context.getString(R.string.gest_age_error_message)
        )
        return false
    } else {
        return true
    }
}

fun validateDateOfBirth(context: Context, editText: EditText): Boolean {
    val text = editText.text.toString().trim()

    if (text == "" || dateIsNotFuture(text) || moreThanTwoYearsAge(
            text
        )
    ) {
        showError(
            context,
            context.getString(R.string.date_of_birth_error_message)
        )
        return false
    } else {
        return true
    }
}

fun dateIsNotFuture(text: String) = Date().before(sdf.parse(text))

fun moreThanTwoYearsAge(text: String): Boolean {
    val years = YEARS.between(
        sdf.parse(text)!!.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
        LocalDate.now()
    )
    return years >= 2
}