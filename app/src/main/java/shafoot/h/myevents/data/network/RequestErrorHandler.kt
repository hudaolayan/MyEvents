/*
 * Copyright (c) 2021
 * project: MyEvents
 * by: hudashafoot
 */

package shafoot.h.myevents.data.network

import retrofit2.HttpException
import shafoot.h.myevents.R
import java.io.IOException
import java.net.SocketTimeoutException

object RequestErrorHandler {

    fun getRequestError(throwable: Throwable): ResultException {
        return when (throwable) {
            is HttpException -> {
                handleHttpException(throwable)
            }
            is IOException -> {
                ResultException.Connection(R.string.error_no_internet_connection)
            }
            is SocketTimeoutException ->  {
                ResultException.Timeout(R.string.error_timeout)
            }
            else -> {
                ResultException.Unexpected(R.string.error_unexpected_message)
            }
        }
    }

    private fun handleHttpException(httpException: HttpException): ResultException {
        return when (httpException.code()) {
            in HTTP_CODE_CLIENT_START..HTTP_CODE_CLIENT_END -> {
                ResultException.Client(R.string.error_client)
            }
            in HTTP_CODE_SERVER_START..HTTP_CODE_SERVER_END -> {
                ResultException.Server(R.string.error_server)
            }
            else -> {
                ResultException.Unexpected(R.string.error_unexpected_message)
            }
        }
    }

    private const val HTTP_CODE_CLIENT_START = 400
    private const val HTTP_CODE_CLIENT_END = 499
    private const val HTTP_CODE_SERVER_START = 500
    private const val HTTP_CODE_SERVER_END = 599
}
