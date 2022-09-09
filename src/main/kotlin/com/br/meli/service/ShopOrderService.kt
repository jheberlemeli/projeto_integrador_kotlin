package com.br.meli.service

import com.br.meli.exception.*
import com.br.meli.model.*
import com.br.meli.repository.BatchStockRepo
import com.br.meli.repository.BuyerRepo
import com.br.meli.repository.ShopOrderRepo
import io.micronaut.core.type.Argument.listOf
import jakarta.inject.Singleton
import java.util.*
import java.lang.IllegalArgumentException


@Singleton
class ShopOrderService(
    private var shopOrderRepo: ShopOrderRepo,
    private var buyerRepo: BuyerRepo,
    private var batchStockRepo: BatchStockRepo,
) {

    fun verifyBuyerExists(id: Int): Buyer{
        return buyerRepo.findById(id).orElseThrow { BuyerNotFoundException(id) }
    }

    fun findById(id: Int): ShopOrder {
        return shopOrderRepo.findById(id).orElseThrow{ ShopOrderNotFoundException(id) }
    }

//    fun sumShopOrderItems(shopOrderListItems: List<ShopOrderItem>): ShopOrder{
//        var total = shopOrderListItems.stream().mapToDouble { so -> so.quantity!! * so.price!! }.sum()
//        return ShopOrder(totalPrice = total)
//
//    }

    fun create(shopOrder: ShopOrder): ShopOrder {
        var buyer = verifyBuyerExists(shopOrder.buyer!!.buyerId)
        val shoporder = ShopOrder (id = shopOrder.id, status = shopOrder.status, shopOrderItem = shopOrder.shopOrderItem, buyer = buyer, totalPrice = null)
        return shopOrderRepo.save(shoporder)
//        val listShopOrderItem: MutableList<ShopOrderItem> = mutableListOf(shopOrderSaved)
//        return sumShopOrderItems(listShopOrderItem)
    }

    fun closedShopOrder(id: Int): ShopOrder {
        var shopOrder = findById(id)

        if (shopOrder.status!!.equals(Status.CLOSED))
            throw ShopOrderAlreadyClosedException("ShopOrder ja esta fechado")

        shopOrder.shopOrderItem!!.forEach { it ->
            if (it.quantity!! > batchStockRepo.getQuantityProduct(it.quantity))
                throw ProductNotFoundException(shopOrder.id)
        }

        shopOrder.status = Status.CLOSED

        shopOrder.shopOrderItem!!.forEach { it ->
            var quantityToBuy: Int = it.quantity!!

            val batchStockList: List<BatchStock>? = it.sellerAd!!.batchStockId
            for (batchStock in batchStockList!!) {
                var currentQuantity: Int = batchStock.currentQuantity
                if (currentQuantity > quantityToBuy) {
                    currentQuantity -= quantityToBuy
                    batchStock.currentQuantity = currentQuantity
                    batchStockRepo.save(batchStock)
                    break
                } else {
                    quantityToBuy -= currentQuantity
                    currentQuantity = 0
                    batchStock.currentQuantity = currentQuantity
                    batchStockRepo.save(batchStock)
                }
            }
        }

        return shopOrderRepo.save(shopOrder)
    }


}
