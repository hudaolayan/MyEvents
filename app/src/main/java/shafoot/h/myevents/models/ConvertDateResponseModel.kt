/*
 * Copyright (c) 2021
 * project: MyEvents
 * by: hudashafoot
 */

package shafoot.h.myevents.models


import com.squareup.moshi.Json

data class ConvertDateResponseModel(
    @Json(name = "hijri")
    val hijriDate: Date?,
    @Json(name = "gregorian")
    val gregorianDate: Date?
)
