package com.br.meli.controller

import com.br.meli.model.BatchStock
import com.br.meli.model.Category
import com.br.meli.model.OrderBy
import com.br.meli.service.BatchStockService
import io.micronaut.http.annotation.*

@Controller ("/batchStock")
class BatchStockController(
    private var batchStockService: BatchStockService,
) {

    @Post
    fun create(@Body batchStock: BatchStock): BatchStock{
        return batchStockService.create(batchStock)
    }

    @Get("/all")
    fun getAll(): List<BatchStock>{
        return batchStockService.getAll()
    }

    @Get("/{productId}")
    fun getProductsInStock(@PathVariable productId: Int): List<BatchStock> {
        return batchStockService.getProductsInStock(productId)
    }

    @Get
    fun getProductsInStockOrdered( @QueryValue productId: Int, @QueryValue orderBy: OrderBy): List<BatchStock> {
        return batchStockService.getProductsInStockOrdered(productId, orderBy)
    }

    @Get("/due-date")
    fun getBatchStocksByDueDate(@QueryValue number_days: Int, @QueryValue section: Long): List<BatchStock> {
        return batchStockService.getBatchStocksByDueDate(number_days, section.toInt())
    }

    @Get("/due-date/list")
    fun getBachStocksFilteredBy(@QueryValue number_days: Int, @QueryValue category: Category, @QueryValue orderType: String): List<BatchStock> {
        return batchStockService.getBatchStocksFilteredBy(number_days, category, orderType!!)
    }
}