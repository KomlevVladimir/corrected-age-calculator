package com.vladimirkomlev.correctedagecalculator.utils

fun spellMonth(months: Long) =
    when (months) {
        in 2..4 -> "месяца"
        1L -> "месяц"
        else -> "месяцев"
    }

fun spellWeek(weeks: Long) =
    when {
        (weeks % 10 == 1L && weeks != 11L)  -> "неделю"
        weeks in 11..20 || weeks % 10 in 5..9 || weeks % 10 == 0L -> "недель"
        else -> "недели"
    }
