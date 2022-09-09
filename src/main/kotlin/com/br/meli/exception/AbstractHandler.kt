package com.br.meli.exception

import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.hateoas.JsonError
import io.micronaut.http.server.exceptions.ExceptionHandler

abstract class AbstractHandler<T : Throwable> : ExceptionHandler<T, HttpResponse<JsonError>> {

    override fun handle(request: HttpRequest<*>, exception: T): HttpResponse<JsonError> =
        getMessage(exception)
            .let {
                val body = JsonError(it)
                    .path(request.uri.path)

//                logger.error(exception) { it }

                HttpResponse.status<JsonError>(responseCode(exception)).body(body)
            }

    open fun getMessage(exception: T): String? = exception.message

    abstract fun responseCode(exception: T): HttpStatus
}