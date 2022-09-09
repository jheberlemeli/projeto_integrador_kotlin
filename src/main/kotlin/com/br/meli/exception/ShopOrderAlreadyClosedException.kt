package com.br.meli.exception

open class ShopOrderAlreadyClosedException(message: String, ex: Throwable = RuntimeException(message)) :
    RuntimeException(message, ex)