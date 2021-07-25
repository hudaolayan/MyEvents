/*
 * Copyright (c) 2021
 * project: MyEvents
 * by: hudashafoot
 */

package shafoot.h.myevents.data.repositories.event

import shafoot.h.myevents.models.Event

class FakeEventRepository : EventRepository {

    private val eventsList = mutableListOf<Event?>()
    override suspend fun insert(event: Event?) {
        eventsList.add(event)
    }

    override suspend fun updateEvent(event: Event?) {
        val itemIndex = eventsList.map { it?.id }.indexOf(event?.id)
        eventsList[itemIndex] = event
    }

    override suspend fun getEvent(id: Int?): Event? {
        val itemIndex = eventsList.map { it?.id }.indexOf(id)
        return eventsList[itemIndex]
    }

    override suspend fun getEvents(): MutableList<Event?> {
        return eventsList
    }

    override suspend fun deleteEvent(event: Event?) {
        eventsList.remove(event)
    }

    override suspend fun delete(list: MutableList<Event?>) {
        eventsList.removeAll(list)
    }

    override suspend fun deleteAll() {
        eventsList.clear()
    }
}