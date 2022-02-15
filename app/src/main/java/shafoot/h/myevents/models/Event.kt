/*
 * Copyright (c) 2021
 * project: MyEvents
 * by: hudashafoot
 */

package shafoot.h.myevents.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "events")
@Parcelize
data class Event(
    @PrimaryKey(autoGenerate = true)
    var id: Int?=null,
    @ColumnInfo(name = "event_name")
    var name: String?=null,
    @ColumnInfo(name = "event_description")
    var description: String?=null,
    @ColumnInfo(name = "Gregorian_date")
    var gregorianDate: String?="",
    @ColumnInfo(name = "Hijri_date")
    var hijriDate: String?="",
    @ColumnInfo(name = "server_datetime")
    var serverDatetime: Long?=0
) : Parcelable
