package com.br.meli.exception

open class EntityAlreadyExistsException(message: String, ex: Throwable = RuntimeException(message)) :
    RuntimeException(message, ex)