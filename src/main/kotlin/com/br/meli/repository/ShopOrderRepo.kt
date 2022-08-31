package com.br.meli.repository

import com.br.meli.controller.ShopOrderController
import com.br.meli.model.ShopOrder
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository

@Repository
interface ShopOrderRepo: CrudRepository<ShopOrder, Int>  {
}