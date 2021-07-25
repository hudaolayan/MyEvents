/*
 * Copyright (c) 2021
 * project: MyEvents
 * by: hudashafoot
 */

package shafoot.h.myevents.utils

import java.text.SimpleDateFormat
import java.util.*


const val D_M_Y_DATE_FORMAT = "dd-MM-yyyy"
const val E_M_D_Y_DATE_FORMAT = "EEEE MMM dd, yyyy"

fun getTodayDate(): String {
    val dateFormat = SimpleDateFormat(D_M_Y_DATE_FORMAT, Locale.ENGLISH)
    val date = Date()
    return dateFormat.format(date)
}

fun String?.getTimeMillis(format: String? = D_M_Y_DATE_FORMAT): Long {
    val dateFormat = SimpleDateFormat(format, Locale.ENGLISH)
    return dateFormat.parse(this).time
}

fun Long.toStringDate(
    outputFormat: String = E_M_D_Y_DATE_FORMAT
): String {
    val formatter = SimpleDateFormat(outputFormat, Locale.getDefault())
    val date = Date()
    date.time = this
    return formatter.format(date)
}
