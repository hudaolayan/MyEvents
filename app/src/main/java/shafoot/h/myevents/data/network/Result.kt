/*
 * Copyright (c) 2021
 * project: MyEvents
 * by: hudashafoot
 */

package shafoot.h.myevents.data.network


sealed class Result<T> {

    data class Success<T>(val data: T?) : Result<T>()

    data class Error<T>(val error: ResultException) : Result<T>()

    data class Loading<T>(val show: Boolean) : Result<T>()

    fun isSuccess() : Boolean {
        return this is Success
    }
}