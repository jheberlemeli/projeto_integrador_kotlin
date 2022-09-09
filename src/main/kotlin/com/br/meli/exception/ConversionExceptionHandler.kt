package com.br.meli.exception

import com.fasterxml.jackson.databind.JsonMappingException
import io.micronaut.context.annotation.Primary
import io.micronaut.context.annotation.Replaces
import io.micronaut.core.convert.exceptions.ConversionErrorException
import io.micronaut.http.HttpStatus
import io.micronaut.http.server.exceptions.ConversionErrorHandler
import jakarta.inject.Singleton

@Primary
@Singleton
@Replaces(ConversionErrorHandler::class)
class ConversionExceptionHandler : AbstractHandler<ConversionErrorException>() {

    override fun responseCode(exception: ConversionErrorException) = HttpStatus.BAD_REQUEST

    override fun getMessage(exception: ConversionErrorException): String? {
        return when (val cause = exception.conversionError.cause) {
            is JsonMappingException -> extractMessage(cause.path)
            else -> exception.message
        }
    }

    private fun extractMessage(paths: List<JsonMappingException.Reference>) =
        paths.stream().map { it.fieldName }
            .reduce { one, two: String -> "$one.$two" }
            .let { "$it must not be null" }
}