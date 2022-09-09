package com.br.meli.exception

import io.micronaut.http.HttpStatus
import io.micronaut.http.HttpStatus.CONFLICT
import io.micronaut.http.HttpStatus.INTERNAL_SERVER_ERROR
import io.micronaut.http.HttpStatus.NOT_FOUND
import io.micronaut.http.HttpStatus.NO_CONTENT
import jakarta.inject.Singleton

@Singleton
class GeneralExceptionAbstractHandler : AbstractHandler<Throwable>() {

    override fun responseCode(exception: Throwable): HttpStatus {
        return when (exception) {
            is ProductNotFoundException -> NO_CONTENT
            is EntityNotFound -> NOT_FOUND
            is EntityAlreadyExistsException -> CONFLICT
            is BadRequestException -> CONFLICT
            else -> INTERNAL_SERVER_ERROR
        }
    }
}