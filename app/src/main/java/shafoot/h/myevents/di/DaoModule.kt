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
import retrofit2.Retrofit
import shafoot.h.myevents.data.daos.local.EventsLocalDao
import shafoot.h.myevents.data.daos.remote.DateRemoteDao
import shafoot.h.myevents.data.database.MyDatabase
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DaoModule {

    @Provides
    fun provideDateRemoteDao(retrofit: Retrofit): DateRemoteDao =
        retrofit.create(DateRemoteDao::class.java)

    @Provides
    @Singleton
    fun provideEventsLocalDao(database: MyDatabase): EventsLocalDao = database.eventsLocalDao

}
