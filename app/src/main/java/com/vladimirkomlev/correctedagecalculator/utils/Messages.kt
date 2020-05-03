package com.vladimirkomlev.correctedagecalculator.utils

import android.content.Context
import android.graphics.Color
import android.widget.TextView
import android.widget.Toast

fun showError(context: Context, message: String) {
    val errorToast = Toast.makeText(context, message, Toast.LENGTH_LONG)
    val errorView = errorToast.view.findViewById<TextView>(android.R.id.message)
    errorView.setTextColor(Color.RED)
    errorToast.show()
}
