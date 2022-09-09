package com.br.meli.exception


class ShopOrderNotFoundException(id: Int) : EntityNotFound("Product with id:[$id] not found")