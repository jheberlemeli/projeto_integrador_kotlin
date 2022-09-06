package com.br.meli.service

import com.br.meli.model.SellerAd
import com.br.meli.repository.SellerAdRepo
import jakarta.inject.Singleton

@Singleton
class SellerAdService(
    var sellerAdRepo: SellerAdRepo
) {
    fun getAll(): List<SellerAd> {
        return sellerAdRepo.findAll()
    }


}
