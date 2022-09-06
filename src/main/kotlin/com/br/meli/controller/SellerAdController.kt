package com.br.meli.controller

import com.br.meli.model.SellerAd
import com.br.meli.service.SellerAdService
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get

@Controller("/sellerAd")
class SellerAdController(
    private var sellerAdService: SellerAdService
) {

    @Get("/all")
    fun getAll(): List<SellerAd> {
        return sellerAdService.getAll()
    }
}