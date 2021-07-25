/*
 * Copyright (c) 2021
 * project: MyEvents
 * by: hudashafoot
 */

package shafoot.h.myevents.data.repositories.date

import shafoot.h.myevents.models.BaseResponse
import shafoot.h.myevents.models.ConvertDateResponseModel

interface DateRepository {

    suspend fun convertGregorianToHijri(date: String?): BaseResponse<ConvertDateResponseModel>

}