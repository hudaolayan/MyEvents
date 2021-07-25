/*
 * Copyright (c) 2021
 * project: MyEvents
 * by: hudashafoot
 */

package shafoot.h.myevents.models


import com.squareup.moshi.Json

data class Date(
    @Json(name = "date")
    val date: String?="",
    @Json(name = "format")
    val format: String?="",
    @Json(name = "day")
    val day: String?="",
    @Json(name = "weekday")
    val weekday: Weekday?=null,
    @Json(name = "month")
    val month: Month?=null,
    @Json(name = "year")
    val year: String?=""
)