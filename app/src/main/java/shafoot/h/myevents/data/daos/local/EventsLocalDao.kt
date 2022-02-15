/*
 * Copyright (c) 2021
 * project: MyEvents
 * by: hudashafoot
 */

package shafoot.h.myevents.data.daos.local

import androidx.room.*
import shafoot.h.myevents.models.Event

@Dao
interface EventsLocalDao {

    @Insert
    suspend fun insert(event: Event?)

    @Update
    suspend fun updateEvent(event: Event?)

    @Query("SELECT * FROM events WHERE id =:id ")
    suspend fun getEvent(id: Int?): Event?

    @Query("SELECT * FROM events ORDER BY server_datetime DESC, id DESC")
    suspend fun getEvents(): MutableList<Event?>

    @Delete
    suspend fun deleteEvent(event: Event?)

    @Delete
    suspend fun delete(list: MutableList<Event?>)

    @Query("DELETE FROM events")
    suspend fun deleteAll()

}
