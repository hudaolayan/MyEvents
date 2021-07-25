/*
 * Copyright (c) 2021
 * project: MyEvents
 * by: hudashafoot
 */

package shafoot.h.myevents.data.daos.remote

import retrofit2.http.GET
import retrofit2.http.Query
import shafoot.h.myevents.models.BaseResponse
import shafoot.h.myevents.models.ConvertDateResponseModel

interface DateRemoteDao {

    @GET("gToH")
    suspend fun convertGregorianToHijri(@Query("date") date: String?): BaseResponse<ConvertDateResponseModel>


}