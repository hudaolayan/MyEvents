/*
 * Copyright (c) 2021
 * project: MyEvents
 * by: hudashafoot
 */

package shafoot.h.myevents.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import shafoot.h.myevents.data.daos.local.EventsLocalDao
import shafoot.h.myevents.data.daos.remote.DateRemoteDao
import shafoot.h.myevents.data.repositories.date.DateRepository
import shafoot.h.myevents.data.repositories.date.DateRepositoryImpl
import shafoot.h.myevents.data.repositories.event.EventRepository
import shafoot.h.myevents.data.repositories.event.EventRepositoryImpl

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Provides
    fun provideDateRepository(dateRemoteDao: DateRemoteDao): DateRepository {
        return DateRepositoryImpl(
            dateRemoteDao
        )
    }

    @Provides
    fun provideEventRepository(eventsLocalDao: EventsLocalDao): EventRepository {
        return EventRepositoryImpl(
            eventsLocalDao
        )
    }

}
