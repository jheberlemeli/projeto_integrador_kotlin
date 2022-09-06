package com.br.meli.controller

import com.br.meli.model.Category
import com.br.meli.model.SellerAd
import com.br.meli.service.SellerAdService
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable

@Controller("/sellerAd")
class SellerAdController(
    private var sellerAdService: SellerAdService
) {

    @Get("/all")
    fun getAll(): List<SellerAd> {
        return sellerAdService.getAll()
    }

    @Get("/{category}")
    fun getByCategory(@PathVariable category: Category): List<SellerAd> {
        return sellerAdService.getByCategory(category)
    }
}