/*
 * Copyright (c) 2021
 * project: MyEvents
 * by: hudashafoot
 */

package shafoot.h.myevents.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import shafoot.h.myevents.data.daos.local.EventsLocalDao
import shafoot.h.myevents.models.Event


@Database(
    entities = [
        Event::class
    ], version = 1, exportSchema = true
)
abstract class MyDatabase : RoomDatabase() {

    abstract val eventsLocalDao: EventsLocalDao

}