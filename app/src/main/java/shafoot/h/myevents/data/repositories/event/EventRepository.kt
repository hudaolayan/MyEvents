/*
 * Copyright (c) 2021
 * project: MyEvents
 * by: hudashafoot
 */

package shafoot.h.myevents.data.repositories.event

import shafoot.h.myevents.models.Event

interface EventRepository {

    suspend fun insert(event: Event?)

    suspend fun updateEvent(event: Event?)

    suspend fun getEvent(id: Int?): Event?

    suspend fun getEvents(): MutableList<Event?>

    suspend fun deleteEvent(event: Event?)

    suspend fun delete(list: MutableList<Event?>)

    suspend fun deleteAll()
}