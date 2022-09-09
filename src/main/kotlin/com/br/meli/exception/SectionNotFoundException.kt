package com.br.meli.exception


class SectionNotFoundException(id: Int) : EntityNotFound("Product with id:[$id] not found")