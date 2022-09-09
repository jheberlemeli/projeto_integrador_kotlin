package com.br.meli.exception

class BadRequestException(override val message: String) : RuntimeException(message) {
}