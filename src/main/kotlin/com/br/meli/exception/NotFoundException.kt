package com.br.meli.exception

class NotFoundException(
    override val message: String,
    val errorCode: String
): RuntimeException() {
}