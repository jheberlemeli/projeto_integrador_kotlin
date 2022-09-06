package com.br.meli.controller

import com.br.meli.model.InboundOrder
import com.br.meli.service.InboundOrderService
import io.micronaut.http.annotation.*

@Controller("/inboundOrder")
class InboundOrderController(
    private var inboundOrderService: InboundOrderService
) {

    @Post("/1")
    fun create(@Body inboundOrder: InboundOrder): InboundOrder{
        return inboundOrderService.create(inboundOrder)
    }

    @Get("{id}")
    fun get(@PathVariable id: Int): InboundOrder{
        return inboundOrderService.finById(id)
    }
}