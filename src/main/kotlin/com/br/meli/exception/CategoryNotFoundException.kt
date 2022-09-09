package com.br.meli.exception


class CategoryNotFoundException(name: String) : EntityNotFound("Category with name:[$name] not found")