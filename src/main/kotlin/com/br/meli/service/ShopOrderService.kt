package com.br.meli.service

import com.br.meli.model.Buyer
import com.br.meli.model.ShopOrder
import com.br.meli.model.Status
import com.br.meli.repository.BuyerRepo
import com.br.meli.repository.ShopOrderRepo
import jakarta.inject.Singleton
import java.util.*
import java.lang.IllegalArgumentException


@Singleton
class ShopOrderService(
    private var shopOrderRepo: ShopOrderRepo,
    private var buyerRepo: BuyerRepo
) {

    fun verifyBuyerExists(id: Int): Buyer{
        return buyerRepo.findById(id).orElseThrow { IllegalArgumentException("Buyer not exists") }
    }

    fun findById(id: Int): Optional<ShopOrder> {
        return shopOrderRepo.findById(id)
    }

    fun create(shopOrder: ShopOrder): ShopOrder {
        var buyer = verifyBuyerExists(shopOrder.buyer!!.buyerId)
        val shoporder = ShopOrder (id = shopOrder.id, status = shopOrder.status, shopOrderItem = shopOrder.shopOrderItem, buyer = buyer)
        return shopOrderRepo.save(shoporder)
    }


}
