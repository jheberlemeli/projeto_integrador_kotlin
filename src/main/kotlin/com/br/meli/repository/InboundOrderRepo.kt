package com.br.meli.repository

import com.br.meli.model.InboundOrder
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository

@Repository
interface InboundOrderRepo: CrudRepository<InboundOrder, Int> {

}
