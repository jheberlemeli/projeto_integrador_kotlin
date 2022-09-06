package com.br.meli.service

import com.br.meli.exception.BadRequestException
import com.br.meli.model.*
import com.br.meli.repository.BatchStockRepo
import com.br.meli.repository.InboundOrderRepo
import com.br.meli.repository.SectionRepo
import jakarta.inject.Singleton
import java.math.BigInteger
import java.time.LocalDate
import java.util.*
import java.util.function.Consumer
import java.util.function.Predicate
import java.util.stream.Collectors

@Singleton
class BatchStockService (
    private var batchStockRepository: BatchStockRepo,

    private var inboundOrderRepository: InboundOrderRepo,

    private var sectionRepository: SectionRepo,
){

    private fun filterBatchStockCurrentQuantity(batchStockList: List<BatchStock>): List<BatchStock> {
        return batchStockList.stream().filter(Predicate<BatchStock> { bs: BatchStock ->
            val batchStockCurrentQuantity: Int = bs.currentQuantity
            batchStockCurrentQuantity != 0
        }).collect(Collectors.toList())
    }

    private fun filterBatchStocksByDueDate(batchStocks: List<BatchStock>, numberOfDays: Int): List<BatchStock> {
        val actualDate = LocalDate.now()
        val lastDateToDue = actualDate.plusDays(numberOfDays.toLong())
        return batchStocks.stream().filter(Predicate<BatchStock> { bs: BatchStock ->
            val batchStockDueDate: LocalDate = bs.dueDate
            batchStockDueDate.isAfter(actualDate) && batchStockDueDate.isBefore(lastDateToDue)
        }).collect(Collectors.toList())
    }


    private fun orderBy(batchStockListFilterByDueDate: List<BatchStock>, orderType: String): List<BatchStock> {
        return batchStockListFilterByDueDate.stream().sorted(Comparator<BatchStock> { b1: BatchStock, b2: BatchStock ->
            if (orderType.equals("asc")) return@Comparator b1.dueDate.compareTo(b2.dueDate)
            else if (orderType.equals("desc")) return@Comparator b2.dueDate.compareTo(b1.dueDate)
            else throw BadRequestException("Invalid order type")
        }).collect(
            Collectors.toList())
    }


    fun getBatchStocksByDueDate(numberOfDays: Int, sectionId: Int): List<BatchStock> {
        val sectionSelected: Section =
            sectionRepository!!.findById(sectionId).orElseThrow { BadRequestException("Section ID invalid!") }
        val sectionCategory: Category = sectionSelected.category!!

        val batchStockList: MutableList<BatchStock> = ArrayList<BatchStock>()

        val inboundOrderBySectionList: List<InboundOrder> = inboundOrderRepository!!.findAllBySectionId(sectionId) as List<InboundOrder>
        inboundOrderBySectionList.forEach(Consumer<InboundOrder> { io: InboundOrder -> batchStockList.addAll(io.batchStockList!!) })

        val batchStockListFilterCurrentQuantity: List<BatchStock> = filterBatchStockCurrentQuantity(batchStockList)

        return filterBatchStocksByDueDate(batchStockListFilterCurrentQuantity, numberOfDays)

    }


    fun getBatchStocksFilteredBy(
        numberOfDays: Int,
        category: Category?,
        orderType: String
    ): List<BatchStock> {
        val sectionsIdsByCategory: List<BigInteger> = sectionRepository!!.getSectionsIdsByCategory(category)
        val inboundOrdersByAllSections: List<InboundOrder> =
            inboundOrderRepository!!.getInboundOrder(sectionsIdsByCategory)
        val batchStockList: MutableList<BatchStock> = ArrayList<BatchStock>()
        inboundOrdersByAllSections.forEach { io: InboundOrder -> batchStockList.addAll(io.batchStockList!!) }
        val batchStockListFilterCurrentQuantity: List<BatchStock> = filterBatchStockCurrentQuantity(batchStockList)
        val batchStockListFilterByDueDate: List<BatchStock> =
            filterBatchStocksByDueDate(batchStockListFilterCurrentQuantity, numberOfDays)
        return orderBy(batchStockListFilterByDueDate, orderType)

    }


    fun getProductsInStock(productId: Int): List<BatchStock> {
        val listDtoByCategory: List<BatchStock> = batchStockRepository.findAll().stream().map { BatchStock(it.id, it.sellerAd, it.currentQuantity, it.dueDate, it.inboundOrder) }
            .filter { it -> it.sellerAd.product!!.id === productId }.collect(Collectors.toList())

        if (listDtoByCategory.isEmpty()) {
            throw BadRequestException("Não existem produtos em estoque.")
        }
        return listDtoByCategory
    }


    fun getProductsInStockOrdered(productId: Int, orderBy: OrderBy?): List<BatchStock> {
        val listDtoByCategoryOrdered: List<BatchStock> =
            batchStockRepository!!.findAll().stream().map { BatchStock(it.id, it.sellerAd, it.currentQuantity, it.dueDate, it.inboundOrder) }
                .filter { it -> it.sellerAd.id === productId }.collect(Collectors.toList())
        return when (orderBy) {
            OrderBy.L -> listDtoByCategoryOrdered.stream()
                .sorted(Comparator.comparingInt(BatchStock::id)).collect(
                Collectors.toList())
            OrderBy.Q -> listDtoByCategoryOrdered.stream()
                .sorted(Comparator.comparingInt(BatchStock::currentQuantity)).collect(
                Collectors.toList())
            OrderBy.V -> listDtoByCategoryOrdered.stream()
                .sorted(Comparator.comparing(BatchStock::dueDate)).collect(
                Collectors.toList())
            else -> throw IllegalArgumentException("Categoria inválida.")
        }
    }

    fun create(batchStock: BatchStock): BatchStock {
        return batchStockRepository.save(batchStock)
    }

    fun getAll(): List<BatchStock> {
        return batchStockRepository.findAll()
    }
}