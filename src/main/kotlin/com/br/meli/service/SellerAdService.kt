package com.br.meli.service

import com.br.meli.exception.BadRequestException
import com.br.meli.model.Category
import com.br.meli.model.SellerAd
import com.br.meli.repository.SellerAdRepo
import jakarta.inject.Singleton
import java.util.stream.Collectors

@Singleton
class SellerAdService(
    var sellerAdRepo: SellerAdRepo
) {
    fun getAll(): List<SellerAd> {
        val sellerAd: List<SellerAd> = sellerAdRepo.findAll()

        if (sellerAd.isEmpty())  throw BadRequestException("A lista de produtos esta vazia")

        return sellerAd
    }

    fun getByCategory(category: Category): List<SellerAd> {
        val filter = sellerAdRepo.findAll().stream().filter { it.product!!.category.toString() == "FRESH" }
        filter.count()

        val sellerAd: List<SellerAd> = sellerAdRepo.findAll().stream().filter { it.product!!.category == category }.collect(Collectors.toList())

        if (sellerAd.isEmpty()) throw BadRequestException("Não há produtos nessa categoria")

        return sellerAd
    }


}
