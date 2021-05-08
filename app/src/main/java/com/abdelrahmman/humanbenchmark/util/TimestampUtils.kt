package com.abdelrahmman.humanbenchmark.util

import java.text.SimpleDateFormat
import java.util.*

class TimestampUtils() {

    companion object {

        fun getCurrentTimestamp(): String? {
            return try {
                val simpleDateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH)
                simpleDateFormat.format(Date())
            } catch (e: Exception) {
                null
            }
        }

        fun getMonthFromNumber(monthNumber: String?): String {
            return when (monthNumber) {
                "01" -> {
                    "Jan"
                }
                "02" -> {
                    "Feb"
                }
                "03" -> {
                    "Mar"
                }
                "04" -> {
                    "Apr"
                }
                "05" -> {
                    "May"
                }
                "06" -> {
                    "Jun"
                }
                "07" -> {
                    "Jul"
                }
                "08" -> {
                    "Aug"
                }
                "09" -> {
                    "Sep"
                }
                "10" -> {
                    "Oct"
                }
                "11" -> {
                    "Nov"
                }
                "12" -> {
                    "Dec"
                }
                else -> {
                    "Error"
                }
            }
        }

    }

}