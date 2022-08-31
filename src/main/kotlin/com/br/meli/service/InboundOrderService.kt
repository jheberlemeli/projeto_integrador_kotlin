package com.br.meli.service

import com.br.meli.model.InboundOrder
import com.br.meli.repository.InboundOrderRepo
import jakarta.inject.Singleton

@Singleton
class InboundOrderService(
    private var inboundOrderRepo: InboundOrderRepo
) {
    fun create(inboundOrder: InboundOrder) {
        inboundOrderRepo.save(inboundOrder)
    }

    fun finById(id: Int): InboundOrder {
        return inboundOrderRepo.findById(id).orElseThrow { IllegalArgumentException("InboundOrder não cadastrada") }
    }

}
