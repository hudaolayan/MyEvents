/*
 * Copyright (c) 2021
 * project: MyEvents
 * by: hudashafoot
 */

package shafoot.h.myevents.ui.base

import androidx.lifecycle.ViewModel
import shafoot.h.myevents.data.network.RequestErrorHandler
import shafoot.h.myevents.data.network.Result
import java.lang.Exception

abstract class BaseViewModel : ViewModel() {

    suspend fun <T> tryResult(call: suspend () -> T): Result<T> {
        return try {
            Result.Success(data = call())
        } catch (e: Exception) {
            Result.Error(RequestErrorHandler.getRequestError(e))
        }
    }

}