/*
 * Copyright (c) 2021
 * project: MyEvents
 * by: hudashafoot
 */

package shafoot.h.myevents.data.repositories.event

import shafoot.h.myevents.data.daos.local.EventsLocalDao
import shafoot.h.myevents.models.Event

class EventRepositoryImpl(private val eventsLocalDao: EventsLocalDao) : EventRepository {

    override suspend fun insert(event: Event?) {
        eventsLocalDao.insert(event)
    }

    override suspend fun updateEvent(event: Event?) {
        return eventsLocalDao.updateEvent(event)
    }

    override suspend fun getEvent(id: Int?): Event? {
        return eventsLocalDao.getEvent(id)
    }

    override suspend fun getEvents(): MutableList<Event?> {
        return eventsLocalDao.getEvents()
    }

    override suspend fun deleteEvent(event: Event?) {
        eventsLocalDao.deleteEvent(event)
    }

    override suspend fun delete(list: MutableList<Event?>) {
        return eventsLocalDao.delete(list)
    }

    override suspend fun deleteAll() {
        return eventsLocalDao.deleteAll()
    }

}