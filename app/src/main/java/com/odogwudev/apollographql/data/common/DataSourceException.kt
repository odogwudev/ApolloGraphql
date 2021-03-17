package com.odogwudev.apollographql.data.common

import com.apollographql.apollo.api.Error

sealed class DataSourceException(
    val messageResource: Any?
) : RuntimeException() {
    class Unexpected(messageResource: Int) : DataSourceException(messageResource)
    class Server(error: Error?) : DataSourceException(error)
}