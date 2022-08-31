package com.br.meli.controller

import com.br.meli.model.InboundOrder
import com.br.meli.service.InboundOrderService
import io.micronaut.http.annotation.*

@Controller("/inboundOrder")
class InboundOrderController(
    private var inboundOrderService: InboundOrderService
) {

    @Post
    fun create(@Body inboundOrder: InboundOrder){
        inboundOrderService.create(inboundOrder)
    }

    @Get("{id}")
    fun get(@PathVariable id: Int): InboundOrder{
        return inboundOrderService.finById(id)
    }
}