package com.br.meli.controller

import com.br.meli.model.BatchStock
import com.br.meli.model.Category
import com.br.meli.service.BatchStockService
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post

@Controller("/batchStock")
class BatchStockController(
    var batchStockService: BatchStockService,
) {

    @Post
    fun create(@Body batchStock: BatchStock): BatchStock{
        return batchStockService.create(batchStock)
    }

    @Get
    fun getProductsInStock(productId: Int): List<BatchStock> {
        return batchStockService.getProductsInStock(productId)
    }

    @Get
    fun getBatchStocksByDueDate(number_days: Int, section: Long): List<BatchStock> {
        return batchStockService.getBatchStocksByDueDate(number_days, section.toInt())
    }

    @Get
    fun getBachStocksFilteredBy(number_days: Int, category: Category, orderType: String?,
    ): List<BatchStock> {
        return batchStockService.getBatchStocksFilteredBy(number_days, category, orderType!!)
    }
}