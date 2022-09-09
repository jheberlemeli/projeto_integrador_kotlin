package com.br.meli.exception


class BuyerNotFoundException(id: Int) : EntityNotFound("Buyer with id:[$id] not found")