package com.awesomeplaylist.api.service

open class NotFoundException(
        override val message: String?,
        cause: Throwable? = null
) : RuntimeException(cause)

open class AddingSongException(
        override val message: String?,
        cause: Throwable? = null
) : RuntimeException(cause)

open class RemovingSongException(
        override val message: String?,
        cause: Throwable? = null
) : RuntimeException(cause)