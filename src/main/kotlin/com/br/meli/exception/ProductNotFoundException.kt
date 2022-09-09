package com.br.meli.exception


class ProductNotFoundException(id: Int?) : EntityNotFound("Product with id:[$id] not found")