/*
 * Copyright (c) 2021
 * project: MyEvents
 * by: hudashafoot
 */

package shafoot.h.myevents.data.network

sealed class ResultException(
    val messageResource: Int,
    message: String = ""
) : RuntimeException(message) {

    class Connection(messageResource: Int) : ResultException(messageResource)

    class Unexpected(messageResource: Int) : ResultException(messageResource)

    class Timeout(messageResource: Int) : ResultException(messageResource)

    class Client(messageResource: Int) : ResultException(messageResource)

    class Server(messageResource: Int) : ResultException(messageResource)
}