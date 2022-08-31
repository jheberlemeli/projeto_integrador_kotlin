package com.br.meli.extension

import com.br.meli.controller.dto.ShopOrderDto
import com.br.meli.model.ShopOrder

fun ShopOrder.toDto(): ShopOrderDto{
    return ShopOrderDto(id = this.id, shopOrderItem = this.shopOrderItem!!)
}

