/*
 * Copyright (c) 2021
 * project: MyEvents
 * by: hudashafoot
 */

package shafoot.h.myevents.data.repositories.date

import shafoot.h.myevents.data.daos.remote.DateRemoteDao
import shafoot.h.myevents.models.BaseResponse
import shafoot.h.myevents.models.ConvertDateResponseModel

class DateRepositoryImpl(private val dateRemoteDao: DateRemoteDao) : DateRepository {

    override suspend fun convertGregorianToHijri(date: String?): BaseResponse<ConvertDateResponseModel> {
        return dateRemoteDao.convertGregorianToHijri(date)
    }

}
