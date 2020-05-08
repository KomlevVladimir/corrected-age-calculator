package com.vladimirkomlev.correctedagecalculator.utils

import android.app.AlertDialog
import android.content.Context
import android.widget.EditText
import com.vladimirkomlev.correctedagecalculator.R
import org.joda.time.DateTime
import org.joda.time.Years
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.ZoneId
import java.time.temporal.ChronoUnit.YEARS
import java.util.*

val sdf = SimpleDateFormat("dd MMMM yyyy", Locale("ru", "RU"))

private fun showAlertDialog(context: Context, message: String) {
    val alertDialog = AlertDialog.Builder(context)
    alertDialog.setTitle("Предупреждение!")
    alertDialog.setMessage(message)
    alertDialog.setPositiveButton(android.R.string.ok) { _, _ -> }
    alertDialog.show()
}

fun validateGestAge(context: Context, editText: EditText): Boolean {
    val text = editText.text.toString().trim()
    return if (text == "" || Integer.parseInt(text) !in 36 downTo 22) {
        showAlertDialog(context, context.getString(R.string.gest_age_error_message))
        false
    } else {
        true
    }
}

fun validateDateOfBirth(context: Context, editText: EditText): Boolean {
    val text = editText.text.toString().trim()

    return if (text == "" || dateIsNotFuture(text) || moreThanTwoYearsAge(
            text
        )
    ) {
        showAlertDialog(context, context.getString(R.string.date_of_birth_error_message))
        false
    } else {
        true
    }
}

fun dateIsNotFuture(text: String) = Date().before(sdf.parse(text))

fun moreThanTwoYearsAge(text: String) =
    Years.yearsBetween(DateTime(sdf.parse(text)), DateTime(Date())).years >= 2
