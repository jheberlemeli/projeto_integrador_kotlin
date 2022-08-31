package com.br.meli.controller

import com.br.meli.model.ShopOrder
import com.br.meli.service.ShopOrderService
import io.micronaut.http.annotation.*
import java.util.*
import javax.transaction.Transactional

@Transactional
@Controller("/shopOrder")
open class ShopOrderController(
    private var shopOrderService: ShopOrderService
) {

    @Get("/{id}")
    open fun get(@PathVariable id: Int): Optional<ShopOrder> {
        return shopOrderService.findById(id)
    }

    @Post
    open fun create(@Body shopOrder: ShopOrder){
        shopOrderService.create(shopOrder)
    }


}