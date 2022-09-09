package com.br.meli.service

import com.br.meli.exception.BadRequestException
import com.br.meli.model.BatchStock
import com.br.meli.model.InboundOrder
import com.br.meli.model.Section
import com.br.meli.repository.InboundOrderRepo
import com.br.meli.repository.SellerAdRepo
import jakarta.inject.Singleton

@Singleton
class InboundOrderService(
    private var inboundOrderRepo: InboundOrderRepo,
    private var sellerAdRepo: SellerAdRepo
) {

    fun isAllTypeProductsValid(batckStock: List<BatchStock>?, sectionCategory: Section?){
        batckStock!!.forEach { it ->
            val sellerAd =
                sellerAdRepo.findById(it.sellerAd.id).orElseThrow { BadRequestException("SellerAd invalido") }
            if (!sellerAd.product!!.category.equals(sectionCategory!!.category)) {
                throw BadRequestException("Produto nao existe")
            }
        }

    }

    fun create(inboundOrder: InboundOrder): InboundOrder {
        isAllTypeProductsValid(inboundOrder.batchStockList, inboundOrder.section)
        return inboundOrderRepo.save(inboundOrder)
    }

    fun finById(id: Int): InboundOrder {
        return inboundOrderRepo.findById(id).orElseThrow { BadRequestException("InboundOrder não cadastrada") }
    }

}
