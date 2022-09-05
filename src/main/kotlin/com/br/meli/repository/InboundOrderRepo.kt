package com.br.meli.repository

import com.br.meli.model.InboundOrder
import io.micronaut.context.annotation.Parameter
import io.micronaut.data.annotation.Query
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository
import java.math.BigInteger

@Repository
interface InboundOrderRepo: CrudRepository<InboundOrder, Int> {

    fun findAllBySectionId(sectionId: Int): List<InboundOrder?>?

    @Query(nativeQuery = true, value = "SELECT * FROM inbound_order io WHERE io.section_id in :ids")
    fun getInboundOrder(@Parameter ids: List<BigInteger>): List<InboundOrder>


}
