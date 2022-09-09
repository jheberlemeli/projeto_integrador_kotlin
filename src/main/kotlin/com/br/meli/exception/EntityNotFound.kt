package com.br.meli.exception

open class EntityNotFound(message: String, ex: Throwable = RuntimeException(message)) :
    RuntimeException(message, ex)